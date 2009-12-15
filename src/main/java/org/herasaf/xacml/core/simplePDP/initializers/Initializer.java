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
package org.herasaf.xacml.core.simplePDP.initializers;

/**
 * TODO REVIEW Ren�.
 * 
 * An initializer that shall be executed by the PDP factory must implement this
 * interface.
 * 
 * @author Florian Huonder
 * @author Ren� Eggenschwiler
 */
public interface Initializer {
	/**
	 * TODO REVIEW Ren�.
	 * 
	 * Within this method the initialization must be done.
	 */
	void run();

	/**
	 * TODO REVIEW Ren�.
	 * 
	 * Here various custom properties may be set to the initializer.
	 * 
	 * @param id
	 *            The id of the property.
	 * @param value
	 *            The value of the property.
	 */
	void setProperty(String id, Object value);
}
