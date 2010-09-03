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

package org.herasaf.xacml.core.simplePDP;

import org.herasaf.xacml.core.api.PIP;
import org.herasaf.xacml.core.api.PolicyRetrievalPoint;
import org.herasaf.xacml.core.combiningAlgorithm.policy.PolicyCombiningAlgorithm;
import org.herasaf.xacml.core.combiningAlgorithm.policy.impl.PolicyOnlyOneApplicableAlgorithm;
import org.herasaf.xacml.core.context.ResponseCtxFactory;
import org.herasaf.xacml.core.context.StatusCodeComparator;
import org.herasaf.xacml.core.targetMatcher.TargetMatcher;
import org.herasaf.xacml.core.targetMatcher.impl.TargetMatcherImpl;

/**
 * This class represents a configuration container for configuring a
 * {@link SimplePDP}.<br />
 * The configuration is customizable and default values can be overridden.
 * Please read field level javadoc for explanation of default values.
 * 
 * @author Florian Huonder
 * @author René Eggenschwiler
 */
public class SimplePDPConfiguration {

	/**
	 * The root {@link PolicyCombiningAlgorithm} to be used in the
	 * {@link SimplePDP} on which this {@link SimplePDPConfiguration} will be
	 * applied. <br>
	 * <b>Default value is:</b> a new instance of
	 * {@link PolicyOnlyOneApplicableAlgorithm}
	 */
	private PolicyCombiningAlgorithm rootCombiningAlgorithm = new PolicyOnlyOneApplicableAlgorithm();

	/**
	 * The {@link PolicyRetrievalPoint} to be used in the {@link SimplePDP} on
	 * which this {@link SimplePDPConfiguration} will be applied. <br>
	 * <b>Default value is:</b> a new instance of
	 * {@link MapBasedSimplePolicyRepository}
	 */
	private PolicyRetrievalPoint policyRetrievalPoint = new MapBasedSimplePolicyRepository();

	/**
	 * The {@link ResponseCtxFactory} {@code class} to be used in the
	 * {@link SimplePDP} on which this {@link SimplePDPConfiguration} will be
	 * applied. <br>
	 * <b>Default value is:</b> {@code} Class<{@link ResponseCtxFactory}>
	 * {@code}
	 */
	private Class<? extends ResponseCtxFactory> responseCtxFactory = ResponseCtxFactory.class;

	/**
	 * The {@link PIP} to be used in the {@link SimplePDP} on which this
	 * {@link SimplePDPConfiguration} will be applied. <br>
	 * <b>Default value is:</b> {@code}null{@code}
	 */
	private PIP pip = null;

	/**
	 * The flag indicating if the {@link SimplePDP}, on which this
	 * {@link SimplePDPConfiguration} will be applied, should respect abandoned
	 * obligations<br>
	 * <b>Default value is:</b> {@code}false{@code}
	 */
	private boolean respectAbandonedEvaluatables = false;

	/**
	 * The {@link TargetMatcher} implementation to be used in the
	 * {@link SimplePDP} on which this {@link SimplePDPConfiguration} will be
	 * applied. <br>
	 * <b>Default value is:</b> {@link TargetMatcherImpl}
	 */
	private TargetMatcher targetMatcher = new TargetMatcherImpl();

	/**
	 * The {@link StatusCodeComparator} to be used in the {@link SimplePDP} on
	 * which this {@link SimplePDPConfiguration} will be applied. <br>
	 * <b>Default value is:</b> {@link StatusCodeComparator}
	 */
	private StatusCodeComparator statusCodeComparator = new StatusCodeComparator();

	/**
	 * @return the configured root {@link PolicyCombiningAlgorithm}
	 */
	public PolicyCombiningAlgorithm getRootCombiningAlgorithm() {
		return rootCombiningAlgorithm;
	}

	/**
	 * @param rootCombiningAlgorithm
	 *            the rootCombiningAlgorithm to be applied in the configuration.
	 *            If the setter is not called explicitly the default
	 *            configuration will be used.
	 */
	public void setRootCombiningAlgorithm(
			PolicyCombiningAlgorithm rootCombiningAlgorithm) {
		this.rootCombiningAlgorithm = rootCombiningAlgorithm;
	}

	/**
	 * @return the configured {@link PolicyRetrievalPoint}
	 */
	public PolicyRetrievalPoint getPolicyRetrievalPoint() {
		return policyRetrievalPoint;
	}

	/**
	 * @param policyRetrievalPoint
	 *            to be applied in the configuration. If the setter is not
	 *            called explicitly the default configuration will be used.
	 */
	public void setPolicyRetrievalPoint(
			PolicyRetrievalPoint policyRetrievalPoint) {
		this.policyRetrievalPoint = policyRetrievalPoint;
	}

	/**
	 * @return the configured {@code}Class for {@link ResponseCtxFactory}
	 *         {@code}
	 */
	public Class<? extends ResponseCtxFactory> getResponseCtxFactory() {
		return responseCtxFactory;
	}

	/**
	 * @param responseCtxFactory
	 *            the responseCtxFactory to be applied in the configuration. If
	 *            the setter is not called explicitly the default configuration
	 *            will be used.
	 */
	public void setResponseCtxFactory(
			Class<? extends ResponseCtxFactory> responseCtxFactory) {
		this.responseCtxFactory = responseCtxFactory;
	}

	/**
	 * @return the configured {@link PIP}
	 */
	public PIP getPip() {
		return pip;
	}

	/**
	 * @param pip
	 *            the pip to set
	 */
	public void setPip(PIP pip) {
		this.pip = pip;
	}

	/**
	 * @return the configured boolean for respectAbandonedObligations behaviour
	 */
	public boolean isRespectAbandonedEvaluatables() {
		return respectAbandonedEvaluatables;
	}

	/**
	 * @param respectAbandonedEvaluatables
	 *            the respectAbandonedEvaluatables to be applied in the
	 *            configuration. If the setter is not called explicitly the
	 *            default configuration will be used.
	 */
	public void setRespectAbandonedEvaluatables(
			boolean respectAbandonedEvaluatables) {
		this.respectAbandonedEvaluatables = respectAbandonedEvaluatables;
	}

	/**
	 * @return the configured {@link TargetMatcher}
	 */
	public TargetMatcher getTargetMatcher() {
		return targetMatcher;
	}

	/**
	 * @param targetMatcher
	 *            the targetMatcher to be applied in the configuration. If the
	 *            setter is not called explicitly the default configuration will
	 *            be used.
	 */
	public void setTargetMatcher(TargetMatcher targetMatcher) {
		this.targetMatcher = targetMatcher;
	}

	/**
	 * @return the configured {@link StatusCodeComparator}
	 */
	public StatusCodeComparator getStatusCodeComparator() {
		return statusCodeComparator;
	}

	/**
	 * @param statusCodeComparator
	 *            the statusCodeComparator to be applied in the configuration.
	 *            If the setter is not called explicitly the default
	 *            configuration will be used.
	 */
	public void setStatusCodeComparator(
			StatusCodeComparator statusCodeComparator) {
		this.statusCodeComparator = statusCodeComparator;
	}

}
