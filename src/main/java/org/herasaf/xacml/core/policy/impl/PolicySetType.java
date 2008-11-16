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

package org.herasaf.xacml.core.policy.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.herasaf.xacml.core.combiningAlgorithm.policy.PolicyCombiningAlgorithm;
import org.herasaf.xacml.core.context.RequestInformation;
import org.herasaf.xacml.core.converter.URNToPolicyCombiningAlgorithmConverter;
import org.herasaf.xacml.core.policy.Evaluatable;
import org.herasaf.xacml.core.policy.EvaluatableID;

/**
 * <p>
 * Java class for PolicySetType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name=&quot;PolicySetType&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Description&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element ref=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}PolicySetDefaults&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element ref=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Target&quot;/&gt;
 *         &lt;choice maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;&gt;
 *           &lt;element ref=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}PolicySet&quot;/&gt;
 *           &lt;element ref=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Policy&quot;/&gt;
 *           &lt;element ref=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}PolicySetIdReference&quot;/&gt;
 *           &lt;element ref=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}PolicyIdReference&quot;/&gt;
 *           &lt;element ref=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}CombinerParameters&quot;/&gt;
 *           &lt;element ref=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}PolicyCombinerParameters&quot;/&gt;
 *           &lt;element ref=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}PolicySetCombinerParameters&quot;/&gt;
 *         &lt;/choice&gt;
 *         &lt;element ref=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Obligations&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name=&quot;PolicySetId&quot; use=&quot;required&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}anyURI&quot; /&gt;
 *       &lt;attribute name=&quot;Version&quot; type=&quot;{urn:oasis:names:tc:xacml:2.0:policy:schema:os}VersionType&quot; default=&quot;1.0&quot; /&gt;
 *       &lt;attribute name=&quot;PolicyCombiningAlgId&quot; use=&quot;required&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}anyURI&quot; /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 * See:	<a href="http://www.oasis-open.org/committees/tc_home.php?wg_abbrev=xacml#XACML20">
 * OASIS eXtensible Access Control Markup Langugage (XACML) 2.0, Errata 29 June 2006</a> page 58, for further information.
 * 
 * @version 1.0
 * @author <i>generated</i>
 * @author Stefan Oberholzer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolicySetType", propOrder = { "description",
		"policySetDefaults", "target", "additionalInformation", "obligations" })
public class PolicySetType implements Evaluatable, Serializable {

	private final static long serialVersionUID = 632768732L;
	@XmlElement(name = "Description")
	protected String description;
	@XmlElement(name = "PolicySetDefaults")
	protected DefaultsType policySetDefaults;
	@XmlElement(name = "Target", required = true)
	protected TargetType target;
	@XmlElementRefs( {
			@XmlElementRef(name = "PolicyIdReference", namespace = "urn:oasis:names:tc:xacml:2.0:policy:schema:os", type = JAXBElement.class),
			@XmlElementRef(name = "PolicySetIdReference", namespace = "urn:oasis:names:tc:xacml:2.0:policy:schema:os", type = JAXBElement.class),
			@XmlElementRef(name = "PolicySetCombinerParameters", namespace = "urn:oasis:names:tc:xacml:2.0:policy:schema:os", type = JAXBElement.class),
			@XmlElementRef(name = "CombinerParameters", namespace = "urn:oasis:names:tc:xacml:2.0:policy:schema:os", type = JAXBElement.class),
			@XmlElementRef(name = "PolicySet", namespace = "urn:oasis:names:tc:xacml:2.0:policy:schema:os", type = JAXBElement.class),
			@XmlElementRef(name = "PolicyCombinerParameters", namespace = "urn:oasis:names:tc:xacml:2.0:policy:schema:os", type = JAXBElement.class),
			@XmlElementRef(name = "Policy", namespace = "urn:oasis:names:tc:xacml:2.0:policy:schema:os", type = JAXBElement.class) })
	protected List<JAXBElement<?>> additionalInformation;
	@XmlElement(name = "Obligations")
	protected ObligationsType obligations;
	@XmlAttribute(name = "PolicySetId", required = true)
	@XmlSchemaType(name = "anyURI")
	protected String policySetId;
	@XmlAttribute(name = "Version")
	protected String version;
	@XmlAttribute(name = "PolicyCombiningAlgId", required = true)
	@XmlJavaTypeAdapter(URNToPolicyCombiningAlgorithmConverter.class)
	@XmlSchemaType(name = "anyURI")
	protected PolicyCombiningAlgorithm policyCombiningAlg;

	/**
	 * Gets the value of the description property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the description property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setDescription(String value) {
		this.description = value;
	}

	/**
	 * Gets the value of the policySetDefaults property.
	 *
	 * @return possible object is {@link DefaultsType }
	 *
	 */
	public DefaultsType getPolicySetDefaults() {
		return policySetDefaults;
	}

	/**
	 * Sets the value of the policySetDefaults property.
	 *
	 * @param value
	 *            allowed object is {@link DefaultsType }
	 *
	 */
	public void setPolicySetDefaults(DefaultsType value) {
		this.policySetDefaults = value;
	}

	/**
	 * Gets the value of the target property.
	 *
	 * @return possible object is {@link TargetType }
	 *
	 */
	public TargetType getTarget() {
		return target;
	}

	/**
	 * Sets the value of the target property.
	 *
	 * @param value
	 *            allowed object is {@link TargetType }
	 *
	 */
	public void setTarget(TargetType value) {
		this.target = value;
	}

	/**
	 * Gets the value of the additionalInformation property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the additionalInformation property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getAdditionalInformation().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link JAXBElement }{@code <}{@link IdReferenceType }{@code >}
	 * {@link JAXBElement }{@code <}{@link IdReferenceType }{@code >}
	 * {@link JAXBElement }{@code <}{@link PolicySetCombinerParametersType }{@code >}
	 * {@link JAXBElement }{@code <}{@link CombinerParametersType }{@code >}
	 * {@link JAXBElement }{@code <}{@link PolicySetType }{@code >}
	 * {@link JAXBElement }{@code <}{@link PolicyCombinerParametersType }{@code >}
	 * {@link JAXBElement }{@code <}{@link PolicyType }{@code >}
	 *
	 *
	 */
	public List<JAXBElement<?>> getAdditionalInformation() {
		if (additionalInformation == null) {
			additionalInformation = new ArrayList<JAXBElement<?>>();
		}
		return this.additionalInformation;
	}

	/**
	 * Gets the value of the obligations property.
	 *
	 * @return possible object is {@link ObligationsType }
	 *
	 */
	public ObligationsType getObligations() {
		return obligations;
	}

	/**
	 * Sets the value of the obligations property.
	 *
	 * @param value
	 *            allowed object is {@link ObligationsType }
	 *
	 */
	public void setObligations(ObligationsType value) {
		this.obligations = value;
	}

	/**
	 * Gets the value of the policySetId property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getPolicySetId() {
		return policySetId;
	}

	/**
	 * Sets the value of the policySetId property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setPolicySetId(String value) {
		this.policySetId = value;
	}

	/**
	 * Gets the value of the version property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getVersion() {
		if (version == null) {
			return "1.0";
		}
		return version;
	}

	/**
	 * Sets the value of the version property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setVersion(String value) {
		this.version = value;
	}

	/**
	 * Gets the value of the policyCombiningAlg property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public PolicyCombiningAlgorithm getCombiningAlg() {
		return policyCombiningAlg;
	}

	/**
	 * Sets the value of the policyCombiningAlg property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCombiningAlg(PolicyCombiningAlgorithm value) {
		this.policyCombiningAlg = value;
	}

	/**
	 * Returns an ordered {@link List} of the containing {@link Evaluatable}s.
	 * This {@link List} may contain null-values in case that a remote reference was not resolvable.
	 * 
	 * @param reqInfo The {@link RequestInformation} is provided for setting the references.
	 * @return A {@link List} of ordered {@link Evaluatable}s.
	 */
	public List<Evaluatable> getOrderedEvaluatables(
			RequestInformation reqInfo) {
		List<Evaluatable> evals = new ArrayList<Evaluatable>();
		// No foreach iterator to ensure thread safety.
		for (int i = 0; i < additionalInformation.size(); i++) {
			JAXBElement<?> jaxbElem = additionalInformation.get(i);
			if (jaxbElem.getValue() instanceof Evaluatable) {
				evals.add((Evaluatable) jaxbElem.getValue());
			} else if (jaxbElem.getValue() instanceof IdReferenceType) {
				evals
						.add(reqInfo
								.getRemotePolicy(((IdReferenceType) jaxbElem
										.getValue()).getValue()));
			}
		}
		return evals;
	}

	/**
	 * Returns an unordered {@link List} of the containing {@link Evaluatable}s.
	 * This {@link List} may contain null-values in case that a remote reference was not resolvable.
	 * 
	 * @param reqInfo The {@link RequestInformation} is provided for setting the references.
	 * @return A {@link List} of {@link Evaluatable}s.
	 */
	public List<Evaluatable> getUnorderedEvaluatables(
			RequestInformation reqInfo) {
		return getOrderedEvaluatables(reqInfo);
	}

	/*
	 * (non-Javadoc)
	 * @see org.herasaf.xacml.core.policy.impl.Evaluatable#getId()
	 */
	public EvaluatableID getId() {
		return new EvaluatableIDImpl(getPolicySetId());
	}
}