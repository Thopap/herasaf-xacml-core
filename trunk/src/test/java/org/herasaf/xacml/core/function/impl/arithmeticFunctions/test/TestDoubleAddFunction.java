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

package org.herasaf.xacml.core.function.impl.arithmeticFunctions.test;

import static org.testng.Assert.assertEquals;

import org.herasaf.xacml.core.function.Function;
import org.herasaf.xacml.core.function.impl.arithmeticFunctions.DoubleAddFunction;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * This test tests if the DoubleAdd function works properly. 
 *  
 * @author Florian Huonder
 */
public class TestDoubleAddFunction {
	private Function ia;

	/**
	 * Creates tests that add 2 double values. The third column is the expected result of the addition.
	 * 
	 * @return The created test cases.
	 */
	@DataProvider(name="data2Args")
	public Object[][] createData2Args(){
		return new Object[][]{
			new Object[] { new Double("1.0"), new Double("1.0"), new Double("2.0")},
			new Object[] { new Double("0.5"), new Double("0.5"), new Double("1.0")},
			new Object[] { new Double("-1.0"), new Double("-3.6"), new Double("-4.6")},
			new Object[] { new Double("-1.2"), new Double("3.9"), new Double("2.7")},
			new Object[] { new Double("1.5"), new Double("4.5"), new Double("6")},
			new Object[] { new Double("0.01"), new Double("1.95"), new Double("1.96")},
		};
	}

	/**
	 * Creates tests that add 3 double values. The fourth column is the expected result of the addition.
	 * 
	 * @return The created test cases.
	 */
	@DataProvider(name="data3Args")
	public Object[][] createData3Args(){
		return new Object[][]{
				new Object[] { new Double("1.0"), new Double("1.0"), new Double("1.15"), new Double("3.15")},
				new Object[] { new Double("0.5"), new Double("0.5"), new Double("1.5"), new Double("2.5")},
				new Object[] { new Double("-1.0"), new Double("-3.6"), new Double("-1.3"), new Double("-5.9")},
				new Object[] { new Double("-1.2"), new Double("3.9"), new Double("12.0"), new Double("14.7")},
				new Object[] { new Double("1.5"), new Double("4.5"), new Double("1.0"), new Double("7")},
				new Object[] { new Double("0.01"), new Double("1.95"), new Double("0.001"), new Double("1.961")},
		};
	}

	/**
	 * Initializes the function.
	 */
	@BeforeMethod
	public void init(){
		ia = new DoubleAddFunction();
	}

	/**
	 * Tests all test cases with 2 double values.
	 * 
	 * @param i1 The first double value.
	 * @param i2 The second double value.
	 * @param result The expected result.
	 * @throws Exception If an error occurs.
	 */
	@Test(dataProvider="data2Args")
	public void testAdd2Args(Double i1, Double i2, Double result) throws Exception {
		assertEquals(((Double)ia.handle(i1, i2)).longValue(), result.longValue());
	}

	/**
	 * Tests all test cases with 2 double values.
	 * 
	 * @param i1 The first double value.
	 * @param i2 The second double value.
	 * @param i3 The third double value.
	 * @param result The expected result.
	 * @throws Exception If an error occurs.
	 */
	@Test(dataProvider="data3Args")
	public void testAdd3Args(Double i1, Double i2, Double i3, Double result) throws Exception {
		assertEquals(((Double)ia.handle(i1, i2, i3)).longValue(), result.longValue());
	}

	/**
	 * Tests if the function returns the right ID.
	 */
	@Test
	public void testID(){
		assertEquals(ia.toString(), "urn:oasis:names:tc:xacml:1.0:function:double-add");
	}
}