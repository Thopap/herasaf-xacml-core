/*
 * Copyright 2008-2010 HERAS-AF (www.herasaf.org)
 * Holistic Enterprise-Ready Application Security Architecture Framework
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.herasaf.xacml.core.context;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;

import org.herasaf.xacml.core.NotInitializedException;
import org.herasaf.xacml.core.SyntaxException;
import org.herasaf.xacml.core.context.impl.ObjectFactory;
import org.herasaf.xacml.core.context.impl.RequestType;
import org.herasaf.xacml.core.context.transformable.ActionTransformable;
import org.herasaf.xacml.core.context.transformable.EnvironmentTransformable;
import org.herasaf.xacml.core.context.transformable.ResourceTransformable;
import org.herasaf.xacml.core.context.transformable.SubjectTransformable;
import org.herasaf.xacml.core.utils.DefaultValidationEventHandler;
import org.herasaf.xacml.core.utils.JAXBMarshallerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 * Factory to create a {@link RequestCtx}. Provides various unmarshal methods to
 * create a {@link RequestCtx}. Because the {@link Unmarshaller} from JAXB <b>is
 * not</b> thread safe it must be created in each unmarshal-method. This class
 * fully relies on the underlying JAXB implementation.
 * 
 * @author Florian Huonder
 */
public final class RequestCtxFactory {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RequestCtxFactory.class);
	private static JAXBContext CONTEXT;
	private static JAXBMarshallerConfiguration CONFIGURATION;
	private static final ObjectFactory OBJECT_FACTORY;

	/**
	 * Initializes the object factory.
	 */
	static {
		OBJECT_FACTORY = new ObjectFactory();
	}

	public static void setJAXBContext(JAXBContext policyContext) {
		CONTEXT = policyContext;
	}

	public static void setJAXBMarshallerConfiguration(
			JAXBMarshallerConfiguration jmc) {
		CONFIGURATION = jmc;
	}

	/**
	 * A utility class must not be instantiated.
	 */
	private RequestCtxFactory() {

	}

	/**
	 * To simply create a {@link RequestCtx} without having the request
	 * attributes in any kind of XML format (file, input stream). This method is
	 * intended to be used by a PEP to simply create {@link RequestCtx}s that
	 * then can be evaluated.
	 * 
	 * @param subjectTransformable
	 *            Contains the subject attributes.
	 * @param resourceTransformable
	 *            Contains the resource attributes.
	 * @param actionTransformable
	 *            Contains the action attributes.
	 * @param environmentTransformable
	 *            Contains the environment attributes
	 * @return The created {@link RequestCtx}.
	 */
	public static RequestCtx create(SubjectTransformable subjectTransformable,
			ResourceTransformable resourceTransformable,
			ActionTransformable actionTransformable,
			EnvironmentTransformable environmentTransformable) {
		RequestType req = OBJECT_FACTORY.createRequestType();
		req.getSubjects().addAll(subjectTransformable.transform());
		req.getResources().addAll(resourceTransformable.transform());
		req.setAction(actionTransformable.transform());
		req.setEnvironment(environmentTransformable.transform());
		return new RequestCtx(req);
	}

	/**
	 * This method creates a new JAXB unmarshaller. For each request a new
	 * unmarshaller is created due to the fact that JAXB is not thread-safe.
	 * 
	 * @return The newly created JAXB unmarshaller.
	 * @throws JAXBException
	 * @throws PropertyException
	 */
	private static Unmarshaller createUnmarshaller() throws JAXBException,
			PropertyException {
		Unmarshaller unmarshaller = CONTEXT.createUnmarshaller();

		if (CONFIGURATION.isValidateParsing()) {
			if (CONFIGURATION.getSchema() == null) {
				LOGGER.error("Schema not initialized.");
				throw new NotInitializedException("Schema not initialized");
			}
			unmarshaller.setSchema(CONFIGURATION.getSchema());
		}
		if (CONFIGURATION.getValidationEventHandler() == null) {
			unmarshaller.setEventHandler(new DefaultValidationEventHandler());
		} else {
			unmarshaller.setEventHandler(CONFIGURATION
					.getValidationEventHandler());
		}
		return unmarshaller;
	}

	/**
	 * Creates an {@link RequestCtx} from the given {@link File}.
	 * 
	 * @param file
	 *            The {@link File} from which the {@link RequestCtx} is created.
	 * @return The created {@link RequestCtx}.
	 * @throws SyntaxException
	 *             In case the XML representation contains a syntax error.
	 */
	@SuppressWarnings("unchecked")
	public static RequestCtx unmarshal(File file) throws SyntaxException {
		Unmarshaller unmarshaller;
		try {
			unmarshaller = createUnmarshaller();
		} catch (PropertyException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		}

		try {
			RequestType rt = ((JAXBElement<RequestType>) unmarshaller
					.unmarshal(file)).getValue();
			return new RequestCtx(rt);
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the file.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		} catch (ClassCastException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the file.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		}
	}

	/**
	 * Creates an {@link RequestCtx} from the given {@link InputStream}.
	 * 
	 * @param inputStream
	 *            The {@link InputStream} from which the {@link RequestCtx} is
	 *            created.
	 * @return The created {@link RequestCtx}.
	 * @throws SyntaxException
	 *             In case the XML representation contains a syntax error.
	 */
	@SuppressWarnings("unchecked")
	public static RequestCtx unmarshal(InputStream inputStream)
			throws SyntaxException {
		Unmarshaller unmarshaller;
		try {
			unmarshaller = createUnmarshaller();
		} catch (PropertyException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		}

		try {
			RequestType rt = ((JAXBElement<RequestType>) unmarshaller
					.unmarshal(inputStream)).getValue();
			return new RequestCtx(rt);
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the input stream.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		} catch (ClassCastException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the input stream.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		}
	}

	/**
	 * Creates an {@link RequestCtx} from the given {@link Reader}.
	 * 
	 * @param reader
	 *            The {@link Reader} from which the {@link RequestCtx} is
	 *            created.
	 * @return The created {@link RequestCtx}.
	 * @throws SyntaxException
	 *             In case the XML representation contains a syntax error.
	 */
	@SuppressWarnings("unchecked")
	public static RequestCtx unmarshal(Reader reader) throws SyntaxException {
		Unmarshaller unmarshaller;
		try {
			unmarshaller = createUnmarshaller();
		} catch (PropertyException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		}

		try {
			RequestType rt = ((JAXBElement<RequestType>) unmarshaller
					.unmarshal(reader)).getValue();
			return new RequestCtx(rt);
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the reader.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		} catch (ClassCastException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the reader.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		}
	}

	/**
	 * Creates an {@link RequestCtx} from the given {@link URL}.
	 * 
	 * @param url
	 *            The {@link URL} from which the {@link RequestCtx} is created.
	 * @return The created {@link RequestCtx}.
	 * @throws SyntaxException
	 *             In case the XML representation contains a syntax error.
	 */
	@SuppressWarnings("unchecked")
	public static RequestCtx unmarshal(URL url) throws SyntaxException {
		Unmarshaller unmarshaller;
		try {
			unmarshaller = createUnmarshaller();
		} catch (PropertyException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		}

		try {
			RequestType rt = ((JAXBElement<RequestType>) unmarshaller
					.unmarshal(url)).getValue();
			return new RequestCtx(rt);
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the url.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		} catch (ClassCastException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the url.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		}
	}

	/**
	 * Creates an {@link RequestCtx} from the given {@link InputSource}.
	 * 
	 * @param inputSource
	 *            The {@link InputSource} from which the {@link RequestCtx} is
	 *            created.
	 * @return The created {@link RequestCtx}.
	 * @throws SyntaxException
	 *             In case the XML representation contains a syntax error.
	 */
	@SuppressWarnings("unchecked")
	public static RequestCtx unmarshal(InputSource inputSource)
			throws SyntaxException {
		Unmarshaller unmarshaller;
		try {
			unmarshaller = createUnmarshaller();
		} catch (PropertyException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		}

		try {
			RequestType rt = ((JAXBElement<RequestType>) unmarshaller
					.unmarshal(inputSource)).getValue();
			return new RequestCtx(rt);
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the input source.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		} catch (ClassCastException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the input source.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		}
	}

	/**
	 * Creates an {@link RequestCtx} from the given {@link Node}.
	 * 
	 * @param node
	 *            The {@link Node} from which the {@link RequestCtx} is created.
	 * @return The created {@link RequestCtx}.
	 * @throws SyntaxException
	 *             In case the XML representation contains a syntax error.
	 */
	@SuppressWarnings("unchecked")
	public static RequestCtx unmarshal(Node node) throws SyntaxException {
		Unmarshaller unmarshaller;
		try {
			unmarshaller = createUnmarshaller();
		} catch (PropertyException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		}

		try {
			RequestType rt = ((JAXBElement<RequestType>) unmarshaller
					.unmarshal(node)).getValue();
			return new RequestCtx(rt);
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the node.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		} catch (ClassCastException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the node.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		}
	}

	/**
	 * Creates an {@link RequestCtx} from the given {@link Source}.
	 * 
	 * @param source
	 *            The {@link Node} from which the {@link RequestCtx} is created.
	 * @return The created {@link RequestCtx}.
	 * @throws SyntaxException
	 *             In case the XML representation contains a syntax error.
	 */
	@SuppressWarnings("unchecked")
	public static RequestCtx unmarshal(Source source) throws SyntaxException {
		Unmarshaller unmarshaller;
		try {
			unmarshaller = createUnmarshaller();
		} catch (PropertyException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		}

		try {
			RequestType rt = ((JAXBElement<RequestType>) unmarshaller
					.unmarshal(source)).getValue();
			return new RequestCtx(rt);
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the source.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		} catch (ClassCastException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the source.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		}
	}

	/**
	 * Creates an {@link RequestCtx} from the given {@link XMLStreamReader}.
	 * 
	 * @param xmlStreamReader
	 *            The {@link XMLStreamReader} from which the {@link RequestCtx}
	 *            is created.
	 * @return The created {@link RequestCtx}.
	 * @throws SyntaxException
	 *             In case the XML representation contains a syntax error.
	 */
	@SuppressWarnings("unchecked")
	public static RequestCtx unmarshal(XMLStreamReader xmlStreamReader)
			throws SyntaxException {
		Unmarshaller unmarshaller;
		try {
			unmarshaller = createUnmarshaller();
		} catch (PropertyException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		}

		try {
			RequestType rt = ((JAXBElement<RequestType>) unmarshaller
					.unmarshal(xmlStreamReader)).getValue();
			return new RequestCtx(rt);
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the xml stream reader.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		} catch (ClassCastException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the xml stream reader.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		}
	}

	/**
	 * Creates an {@link RequestCtx} from the given {@link XMLEventReader}.
	 * 
	 * @param xmlEventReader
	 *            The {@link XMLEventReader} from which the {@link RequestCtx}
	 *            is created.
	 * @return The created {@link RequestCtx}.
	 * @throws SyntaxException
	 *             In case the XML representation contains a syntax error.
	 */
	@SuppressWarnings("unchecked")
	public static RequestCtx unmarshal(XMLEventReader xmlEventReader)
			throws SyntaxException {
		Unmarshaller unmarshaller;
		try {
			unmarshaller = createUnmarshaller();
		} catch (PropertyException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to create a JAXB Unmarshaller.", e);
			LOGGER.error(se.getMessage());
			throw se;
		}

		try {
			RequestType rt = ((JAXBElement<RequestType>) unmarshaller
					.unmarshal(xmlEventReader)).getValue();
			return new RequestCtx(rt);
		} catch (JAXBException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the xml event reader.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		} catch (ClassCastException e) {
			SyntaxException se = new SyntaxException(
					"Unable to unmarshal the xml event reader.");
			LOGGER.error(se.getMessage(), e);
			throw se;
		}
	}
}