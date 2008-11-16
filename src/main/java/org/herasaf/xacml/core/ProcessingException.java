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

package org.herasaf.xacml.core;

/**
 * This exception is thrown if an error while processing a function or
 * expression.
 *
 * @author Florian Huonder
 * @version 1.0
 */
public abstract class ProcessingException extends Exception {
	private static final long serialVersionUID = -7535706787860940155L;

	/**
	 * Constructs a new exception with null as its detail message.
	 */
	public ProcessingException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message.
	 */
	public ProcessingException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause.
	 */
	public ProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message
	 * of (cause==null ? null : cause.toString()) (which typically contains the
	 * class and detail message of cause).
	 */
	public ProcessingException(Throwable cause) {
		super(cause);
	}
}