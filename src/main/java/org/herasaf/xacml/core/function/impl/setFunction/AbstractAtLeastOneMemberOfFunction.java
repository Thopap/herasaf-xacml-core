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

package org.herasaf.xacml.core.function.impl.setFunction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.herasaf.xacml.core.function.Function;
import org.herasaf.xacml.core.function.FunctionProcessingException;

/**
 * <p>
 * The implementation of the
 * urn:oasis:names:tc:xacml:1.0:function:<u>type</u>-at-least-one-member-of
 * function.
 * </p>
 * <p>
 * See: Apendix A.3 of the <a href=
 * "http://www.oasis-open.org/committees/tc_home.php?wg_abbrev=xacml#XACML20">
 * OASIS eXtensible Access Control Markup Langugage (XACML) 2.0, Errata 29 June
 * 2006</a> page 105, for further information.
 * </p>
 * 
 * @param <T> TODO JAVADOC.
 * @author Stefan Oberholzer
 * @version 1.0
 */
public abstract class AbstractAtLeastOneMemberOfFunction<T> implements Function {
	private static final long serialVersionUID = -367894928512846701L;

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Returns true if at least one element of the first argument is contained
	 * in the second argument.
	 */
	@SuppressWarnings("unchecked")
	public Object handle(Object... args) throws FunctionProcessingException {
		try {
			if (args.length != 2) {
				throw new FunctionProcessingException("Invalid number of parameters.");
			}
			Set<T> arg1 = new HashSet<T>((List<T>) args[1]);
			for (Object str : ((List<T>) args[0])) {
				if (arg1.contains(str)) {
					return true;
				}
			}
			return false;
		} catch (ClassCastException e) {
			throw new FunctionProcessingException("The arguments were of the wrong datatype.");
		} catch (FunctionProcessingException e) {
			throw e;
		} catch (Exception e) {
			throw new FunctionProcessingException(e);
		}
	}
}