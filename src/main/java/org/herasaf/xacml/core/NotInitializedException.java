/*
 * Copyright 2009 HERAS-AF (www.herasaf.org)
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
package org.herasaf.xacml.core;

/**
 * This {@link RuntimeException} is thrown when a component needed for
 * evaluation is not properly initialized.
 * 
 * E.g. if the JAXB engine is not configured as expected.
 * 
 * @author Florian Huonder
 * @author Ren� Eggenschwiler
 */
public class NotInitializedException extends RuntimeException {
	private static final long serialVersionUID = 4169969483147086614L;

	public NotInitializedException() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public NotInitializedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * {@inheritDoc}
	 */
	public NotInitializedException(String message) {
		super(message);
	}

	/**
	 * {@inheritDoc}
	 */
	public NotInitializedException(Throwable cause) {
		super(cause);
	}
}