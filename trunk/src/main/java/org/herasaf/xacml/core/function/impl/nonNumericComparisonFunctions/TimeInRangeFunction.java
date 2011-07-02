/*
 * Copyright 2008 - 2011 HERAS-AF (www.herasaf.org)
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

package org.herasaf.xacml.core.function.impl.nonNumericComparisonFunctions;

import org.herasaf.xacml.core.function.AbstractFunction;
import org.herasaf.xacml.core.function.FunctionProcessingException;
import org.herasaf.xacml.core.types.Time;

/**
 * <p>
 * The implementation of the
 * urn:oasis:names:tc:xacml:1.0:function:time-less-than function.
 * </p>
 * <p>
 * See: Apendix A.3 of the <a href=
 * "http://www.oasis-open.org/committees/tc_home.php?wg_abbrev=xacml#XACML20">
 * OASIS eXtensible Access Control Markup Langugage (XACML) 2.0, Errata, 29 January 2008</a> page 118, for further information.
 * </p>
 * 
 * @author Stefan Oberholzer
 */
public class TimeInRangeFunction extends AbstractFunction {

	/** XACML function ID. */
	public static final String ID = "urn:oasis:names:tc:xacml:2.0:function:time-in-range";

	private static final int VALID_LENGTH = 3;
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Returns true if the first argument of type
	 * http://www.w3.org/2001/XMLSchema#time falls in range of the second and
	 * the third argument (of type http://www.w3.org/2001/XMLSchema#time)
	 * inclusively.<br>
	 * * <br>
	 * <code style="color:red"> <b>Important Hint:</b><br>
	 * The OASIS eXtensible Access Control Markup Langugage (XACML) 2.0,
	 * Errata 29 January 2008
	 * <a href="http://www.oasis-open.org/committees/tc_home.php?wg_abbrev=xacml#XACML20">XACML 2.0</a>
	 * function urn:oasis:names:tc:xacml:1.0:function:time-in-range
	 * must provide an implicit time zone if no one is set.
	 * This MUST is not considered in this implementation of the function.</code>
	 */
	// FIXME Time zone awareness (see HERASAFXACMLCORE-28).
	// The OASIS eXtensible Access Control Markup Langugage (XACML) 2.0,
	// Errata 29 January 2008
	// (http://www.oasis-open.org/committees/tc_home.php?wg_abbrev=xacml#XACML20)
	// function urn:oasis:names:tc:xacml:2.0:function:time-in-range is
	// aware of the time zone and is able
	// to set the default time zone of the context handler if no one is
	// provided. This MUST is not considered in this
	// implementation of the function and must be fixed.
	public Object handle(Object... args) throws FunctionProcessingException {
		try {
			if (args.length != VALID_LENGTH) {
				throw new FunctionProcessingException("Invalid number of parameters");
			}

			if (((Time) args[0]).compareTo((Time) args[1]) < 0) {
				return false;
			}
			if (((Time) args[0]).compareTo((Time) args[1]) > 0) {
				return false;
			}
			return true;

		} catch (ClassCastException e) {
			throw new FunctionProcessingException(e);
		} catch (FunctionProcessingException e) {
			throw e;
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
