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

package org.herasaf.xacml.core.policy.test;

import static org.testng.Assert.assertEquals;

import org.herasaf.xacml.core.context.impl.RequestType;
import org.herasaf.xacml.core.dataTypeAttribute.impl.StringDataTypeAttribute;
import org.herasaf.xacml.core.policy.ExpressionProcessingException;
import org.herasaf.xacml.core.policy.impl.AttributeValueType;
import org.herasaf.xacml.core.policy.requestinformationfactory.RequestInformationFactoryMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@ContextConfiguration(locations = { "classpath:context/ApplicationContext.xml" })
public class TestAttributeValueType extends AbstractTestNGSpringContextTests{
	AttributeValueType attrVal;
	@Autowired
	private RequestInformationFactoryMock requestInformationFactory;	
	@BeforeMethod
	public void beforeMethod() {
		attrVal = new AttributeValueType();
	}

	@Test(enabled = true)
	public void testHandle() throws Exception {
		attrVal.setDataType(new StringDataTypeAttribute());
		attrVal.getContent().add("test");

		assertEquals("test", (String) attrVal.handle(new RequestType(),
				requestInformationFactory.createRequestInformation(null, null)));
	}

	@Test(enabled = true, expectedExceptions = ExpressionProcessingException.class)
	public void testHandleException() throws Exception {
		attrVal.setDataType(new StringDataTypeAttribute());
		attrVal.getContent().add("test");
		attrVal.getContent().add("test2");

		attrVal.handle(new RequestType(), requestInformationFactory.createRequestInformation(null, null));
	}

	@Test(enabled = true, expectedExceptions = ExpressionProcessingException.class)
	public void testHandleExceptionWrongType() throws Exception {
		attrVal.setDataType(new StringDataTypeAttribute());
		attrVal.getContent().add(new Integer("1"));
		attrVal.handle(new RequestType(), requestInformationFactory.createRequestInformation(null, null));
	}
}
