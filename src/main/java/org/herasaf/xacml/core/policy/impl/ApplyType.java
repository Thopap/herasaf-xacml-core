/*
 * Copyright 2008 - 2013 HERAS-AF (www.herasaf.org)
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

package org.herasaf.xacml.core.policy.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.herasaf.xacml.core.ProcessingException;
import org.herasaf.xacml.core.SyntaxException;
import org.herasaf.xacml.core.context.EvaluationContext;
import org.herasaf.xacml.core.context.impl.RequestType;
import org.herasaf.xacml.core.converter.FunctionsJAXBTypeAdapter;
import org.herasaf.xacml.core.function.Function;
import org.herasaf.xacml.core.policy.MissingAttributeException;

/**
 * <p>
 * Java class for ApplyType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;ApplyType&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}ExpressionType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Expression&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name=&quot;FunctionId&quot; use=&quot;required&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}anyURI&quot; /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * See: <a href=
 * "http://www.oasis-open.org/committees/tc_home.php?wg_abbrev=xacml#XACML20">
 * OASIS eXtensible Access Control Markup Langugage (XACML) 2.0, Errata, 29 January 2008</a> page 63, for further information.
 * 
 * @author <i>generated</i>
 * @author Sacha Dolski
 */
@XmlRootElement(name = "Apply", namespace="urn:oasis:names:tc:xacml:2.0:policy:schema:os")
@XmlType(name = "ApplyType", propOrder = { "expressions" })
public class ApplyType extends ExpressionType {
	private static final long serialVersionUID = 1L;
	@XmlElementRef(name = "Expression", namespace = "urn:oasis:names:tc:xacml:2.0:policy:schema:os", type = JAXBElement.class)
	private List<JAXBElement<?>> expressions;
	@XmlAttribute(name = "FunctionId", required = true)
	@XmlJavaTypeAdapter(FunctionsJAXBTypeAdapter.class)
	@XmlSchemaType(name = "anyURI")
	private Function function;

	/**
	 * Gets the value of the expressions property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the expressions property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getExpressions().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link JAXBElement }{@code <}{@link ResourceAttributeDesignatorType }
	 * {@code >} {@link JAXBElement }{@code <}{@link AttributeSelectorType }
	 * {@code >} {@link JAXBElement }{@code <}{@link VariableReferenceType }
	 * {@code >} {@link JAXBElement }{@code <}
	 * {@link EnvironmentAttributeDesignatorType }{@code >} {@link JAXBElement }
	 * {@code <}{@link AttributeValueType }{@code >} {@link JAXBElement }{@code <}
	 * {@link SubjectAttributeDesignatorType }{@code >} {@link JAXBElement }
	 * {@code <}{@link FunctionType }{@code >} {@link JAXBElement }{@code <}
	 * {@link ExpressionType }{@code >} {@link JAXBElement }{@code <}
	 * {@link ApplyType }{@code >} {@link JAXBElement }{@code <}
	 * {@link ActionAttributeDesignatorType }{@code >}
	 * 
	 * 
	 */
	public List<JAXBElement<?>> getExpressions() {
		if (expressions == null) {
			expressions = new ArrayList<JAXBElement<?>>();
		}
		return this.expressions;
	}

	/**
	 * Gets the value of the function property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Function getFunction() {
		return function;
	}

	/**
	 * Sets the value of the function property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFunction(Function value) {
		this.function = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.herasaf.xacml.core.policy.impl.ExpressionType#handle(org.herasaf.
	 * xacml.core.context.impl.RequestType, java.util.Map)
	 */
	@Override
	public Object handle(RequestType request, EvaluationContext evaluationContext) throws MissingAttributeException,
			SyntaxException, ProcessingException {
		try {
			List<Object> params = new ArrayList<Object>();

			for (int i = 0; i < getExpressions().size(); i++) {
				JAXBElement<?> jaxbElem = getExpressions().get(i);
				if (jaxbElem.getValue() instanceof ApplyType) {
					params.add(((ApplyType) jaxbElem.getValue()).handle(request, evaluationContext));
					continue;
				}
				params.add(((ExpressionType) jaxbElem.getValue()).handle(request, evaluationContext));
			}
			return getFunction().handle(params.toArray());
		} catch (ClassCastException e) {
			throw new SyntaxException(e);
		} catch (NullPointerException e) {
			throw new SyntaxException(e);
		}
	}
}