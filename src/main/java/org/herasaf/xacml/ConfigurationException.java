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

package org.herasaf.xacml;

/**
 * This exception is thrown when a configuration fails.
 * 
 * @author Florian Huonder 
 * @version 1.0
 */
public class ConfigurationException extends RuntimeException {
	private static final long serialVersionUID = 8213802064654257318L;

	/**
	 * Constructs a new exception with null as its detail message.
	 */
	public ConfigurationException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message.
	 */
	public ConfigurationException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause.
	 */
	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message
	 * of (cause==null ? null : cause.toString()) (which typically contains the
	 * class and detail message of cause).
	 */
	public ConfigurationException(Throwable cause) {
		super(cause);
	}
}