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

package org.herasaf.xacml.core.dataTypeAttribute.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.herasaf.xacml.core.SyntaxException;

/**
 * This data type represents a http://www.w3.org/2001/XMLSchema#anyURI. See: <A
 * HREF="http://www.w3.org/TR/xmlschema-2/#anyURI"
 * target="_blank">http://www.w3.org/TR/xmlschema-2/#anyURI</A> for further
 * information.
 * 
 * @author Stefan Oberholzer
 */
public class AnyURIDataTypeAttribute extends AbstractDataTypeAttribute<URI> {
	public static final String ID = "http://www.w3.org/2001/XMLSchema#anyURI";
	private static final long serialVersionUID = -5182797446805115749L;

	/** {@inheritDoc} */
	public URI convertTo(String jaxbRepresentation) throws SyntaxException {
		try {
			return new URI(jaxbRepresentation.trim());
		} catch (URISyntaxException e) {
			throw new SyntaxException(e);
		}
	}

	/** {@inheritDoc} */
	public String getDatatypeURI() {
		return ID;
	}
}