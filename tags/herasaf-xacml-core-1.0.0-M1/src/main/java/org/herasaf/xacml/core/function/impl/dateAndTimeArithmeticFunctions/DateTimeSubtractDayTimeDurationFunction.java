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

package org.herasaf.xacml.core.function.impl.dateAndTimeArithmeticFunctions;

import org.herasaf.xacml.core.function.AbstractFunction;
import org.herasaf.xacml.core.function.FunctionProcessingException;
import org.herasaf.xacml.core.types.DateTime;
import org.herasaf.xacml.core.types.DayTimeDuration;

/**
 * <p>
 * The implementation of the
 * urn:oasis:names:tc:xacml:1.0:function:dateTime-subtract-dayTimeDuration
 * function.
 * </p>
 * <p>
 * See Apendix A.3 of the <a href=
 * "http://www.oasis-open.org/committees/tc_home.php?wg_abbrev=xacml#XACML20">
 * OASIS eXtensible Access Control Markup Langugage (XACML) 2.0, Errata 29 June
 * 2006</a> page 111, for further information.
 * </p>
 * 
 * @author Stefan Oberholzer
 * @version 1.0
 */
public class DateTimeSubtractDayTimeDurationFunction extends AbstractFunction {

	private static final long serialVersionUID = -8657888777873703249L;
	private static final String ID = "urn:oasis:names:tc:xacml:1.0:function:dateTime-subtract-dayTimeDuration";

	/**
	 * {@inheritDoc}Takes a {@link DateTime} value as first parameter and a
	 * {@link DayTimeDuration} as second parameter. The return value is the
	 * result of adding the {@link DayTimeDuration} to the {@link DateTime}
	 * duration as {@link DateTime}.
	 */
	public Object handle(Object... args) throws FunctionProcessingException {
		try {
			if (args.length != 2) {
				throw new FunctionProcessingException(
						"Invalid number of parameters");
			}
			((DateTime) args[0]).subtract((DayTimeDuration) args[1]);
			return args[0];
		} catch (ClassCastException e) {
			throw new FunctionProcessingException(e);
		} catch (Exception e) {
			throw new FunctionProcessingException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getFunctionId() {
		return ID;
	}
}