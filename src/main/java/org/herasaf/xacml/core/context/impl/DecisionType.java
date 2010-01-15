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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.5-b01-fcs
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2007.10.03 at 07:56:30 AM CEST
//

package org.herasaf.xacml.core.context.impl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DecisionType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="DecisionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Permit"/>
 *     &lt;enumeration value="Deny"/>
 *     &lt;enumeration value="Indeterminate"/>
 *     &lt;enumeration value="NotApplicable"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 * See: <a href=
 * "http://www.oasis-open.org/committees/tc_home.php?wg_abbrev=xacml#XACML20">
 * OASIS eXtensible Access Control Markup Langugage (XACML) 2.0, Errata 29 June
 * 2006</a> page 728, for further information.
 * 
 * @version 1.0
 * @author <i>generated</i>
 */
@XmlType(name = "DecisionType")
@XmlEnum
public enum DecisionType {

	@XmlEnumValue("Permit")
	PERMIT("Permit"), @XmlEnumValue("Deny")
	DENY("Deny"), @XmlEnumValue("Indeterminate")
	INDETERMINATE("Indeterminate"), @XmlEnumValue("NotApplicable")
	NOT_APPLICABLE("NotApplicable");
	private final String value;

	/**
	 * Sole constructor. Programmers cannot invoke this constructor. It is for
	 * use by code emitted by the compiler in response to enum type
	 * declarations.
	 * 
	 * @param v
	 *            The value to set.
	 */
	DecisionType(String v) {
		value = v;
	}

	/**
	 * Returns the current value.
	 * 
	 * @return The current {@link DecisionType}.
	 */
	public String value() {
		return value;
	}

	/**
	 * Returns the enum-type from the given {@link String} value.
	 * 
	 * @param v
	 *            The {@link String} value.
	 * @return The {@link DecisionType} matching the {@link String} value.
	 */
	public static DecisionType fromValue(String v) {
		for (int i = 0; i < DecisionType.values().length; i++) {
			DecisionType c = DecisionType.values()[i];
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
}
