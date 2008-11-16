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

package org.herasaf.xacml.core.dataTypeAttribute.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.herasaf.xacml.SyntaxException;
import org.herasaf.xacml.core.dataTypeAttribute.DataTypeAttribute;

/**
 * <p>
 * A DataType is used as an attribute in the JAXB Objects representing the XACML
 * elements. To get the correct Value of the AttributeElement, the convertTo
 * method should be used to convert the value to the Object. The Name of this
 * datatype is http://www.w3.org/2001/XMLSchema#anyURI. <br/>
 *
 * See: <A HREF="http://www.w3.org/TR/xmlschema-2/#anyURI"
 * target="_blank">http://www.w3.org/TR/xmlschema-2/#anyURI</A> for further
 * information.
 * </p>
 *
 * @author Stefan Oberholzer
 * @version 1.0
 */
public class AnyURIDataTypeAttribute implements DataTypeAttribute<URI> {
	private static final long serialVersionUID = 2572419057278770505L;
	private static final String ID = "http://www.w3.org/2001/XMLSchema#anyURI";

	/*
	 * (non-Javadoc)
	 *
	 * @see org.herasaf.core.dataTypeAttribute.DataTypeAttribute#convertTo(java.lang.String)
	 */
	public URI convertTo(String jaxbRepresentation) throws SyntaxException {
		try {
			return new URI(jaxbRepresentation.trim());
		} catch (URISyntaxException e) {
			throw new SyntaxException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ID;
	}
}