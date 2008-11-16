/*
 * Copyright 2008 HERAS-AF (www.herasaf.org)
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
import java.io.OutputStream;
import java.io.Writer;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;

import org.herasaf.xacml.WritingException;
import org.herasaf.xacml.core.context.impl.ObjectFactory;
import org.herasaf.xacml.core.context.impl.RequestType;
import org.herasaf.xacml.core.utils.ContextAndPolicy;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;

/**
 * Encapsulates a {@link RequestType}. Provides various marshalling methods for
 * the {@link RequestType}. Because the {@link Marshaller} from JAXB <b>is not</b>
 * thread safe it must be newly created in each marshal-method. This class fully
 * relies on the underlying JAXB implementation.
 *
 * @author Florian Huonder
 * @version 1.0
 */
public class RequestCtx {
	private static final ContextAndPolicy.JAXBProfile REQUESTCTX = ContextAndPolicy.JAXBProfile.REQUEST_CTX;
	private RequestType request;
	private static ObjectFactory objectFactory;

	/**
	 * Initializes the Object factory.
	 */
	static {
		objectFactory = new ObjectFactory();
	}

	/**
	 * Creates a new {@link RequestCtx} from the given {@link RequestType}.
	 *
	 * @param request
	 *            The {@link RequestType} which will be placed into this
	 *            {@link RequestCtx}.
	 */
	public RequestCtx(RequestType request) {
		this.request = request;
	}

	/**
	 * Returns the containing {@link RequestType}.
	 *
	 * @return The {@link RequestType} contained in this object.
	 */
	public RequestType getRequest() {
		return request;
	}

	/**
	 * Marshals the contained Request into SAX2 events.
	 *
	 * @param ch
	 *            XML will be sent to this handler as SAX2 events.
	 * @throws WritingException -
	 *             If any unexpected problem occurs during the marshalling.
	 */
	public void marshal(ContentHandler ch) throws WritingException {
		try {
			ContextAndPolicy.getMarshaller(REQUESTCTX).marshal(
					objectFactory.createRequest(request), ch);
		} catch (JAXBException e) {
			throw new WritingException(e);
		}
	}

	/**
	 * Marshals the contained Request to the file.
	 *
	 * @param file
	 *            XML written into this file.
	 * @throws WritingException -
	 *             If any unexpected problem occurs during the marshalling.
	 */
	public void marshal(File file) throws WritingException {
		try {
			ContextAndPolicy.getMarshaller(REQUESTCTX).marshal(
					objectFactory.createRequest(request), file);
		} catch (JAXBException e) {
			throw new WritingException(e);
		}
	}

	/**
	 * <p>
	 * Marshal the contained Request into the specified
	 * javax.xml.transform.Result.
	 * </p>
	 * <p>
	 * At least DOMResult, SAXResult and StreamResult are supported. If more
	 * results are supported, depends on the JAXBImplementation included in this
	 * Module.
	 * </p>
	 *
	 * @param result
	 *            XML will be sent to this Result .
	 * @throws WritingException -
	 *             If any unexpected problem occurs during the marshalling.
	 */
	public void marshal(Result result) throws WritingException {
		try {
			ContextAndPolicy.getMarshaller(REQUESTCTX).marshal(
					objectFactory.createRequest(request), result);
		} catch (JAXBException e) {
			throw new WritingException(e);
		}
	}

	/**
	 * <p>
	 * Marshal the contained Request into an output stream.
	 * </p>
	 *
	 * @param out
	 *            XML will be added to this stream .
	 * @throws WritingException -
	 *             If any unexpected problem occurs during the marshalling.
	 */
	public void marshal(OutputStream out) throws WritingException {
		try {
			ContextAndPolicy.getMarshaller(REQUESTCTX).marshal(
					objectFactory.createRequest(request), out);
		} catch (JAXBException e) {
			throw new WritingException(e);
		}
	}

	/**
	 * <p>
	 * Marshal the contained Request into a Writer.
	 * </p>
	 *
	 * @param writer
	 *            XML will be sent to this writer.
	 * @throws WritingException -
	 *             If any unexpected problem occurs during the marshalling.
	 */
	public void marshal(Writer writer) throws WritingException {
		try {
			ContextAndPolicy.getMarshaller(REQUESTCTX).marshal(
					objectFactory.createRequest(request), writer);
		} catch (JAXBException e) {
			throw new WritingException(e);
		}
	}

	/**
	 * Marshals the contained Request into a DOM tree.
	 *
	 * @param node
	 *            DOM nodes will be added as children of this node. This
	 *            parameter must be a Node that accepts children (Document,
	 *            DocumentFragment, or Element)
	 *
	 * @throws WritingException -
	 *             If any unexpected problem occurs during the marshalling.
	 */
	public void marshal(Node node) throws WritingException {
		try {
			ContextAndPolicy.getMarshaller(REQUESTCTX).marshal(
					objectFactory.createRequest(request), node);
		} catch (JAXBException e) {
			throw new WritingException(e);
		}
	}

	/**
	 * Marshal the contained Request into a XMLStreamWriter.
	 *
	 * @param xmlStreamWriter
	 *            XML will be sent to this writer.
	 *
	 * @throws WritingException -
	 *             If any unexpected problem occurs during the marshalling.
	 */
	public void marshal(XMLStreamWriter xmlStreamWriter)
			throws WritingException {
		try {
			ContextAndPolicy.getMarshaller(REQUESTCTX).marshal(
					objectFactory.createRequest(request), xmlStreamWriter);
		} catch (JAXBException e) {
			throw new WritingException(e);
		}
	}

	/**
	 * Marshal the contained Request into a XMLEventWriter.
	 *
	 * @param xmlEventWriter
	 *            XML will be sent to this writer.
	 *
	 * @throws WritingException -
	 *             If any unexpected problem occurs during the marshalling.
	 */
	public void marshal(XMLEventWriter xmlEventWriter) throws WritingException {
		try {
			ContextAndPolicy.getMarshaller(REQUESTCTX).marshal(
					objectFactory.createRequest(request), xmlEventWriter);
		} catch (JAXBException e) {
			throw new WritingException(e);
		}
	}
}