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

package org.herasaf.xacml.core.combiningAlgorithm.rule.impl;

import java.util.List;

import org.herasaf.xacml.core.combiningAlgorithm.policy.PolicyCombiningAlgorithm;
import org.herasaf.xacml.core.combiningAlgorithm.rule.RuleUnorderedCombiningAlgorithm;
import org.herasaf.xacml.core.context.RequestInformation;
import org.herasaf.xacml.core.context.impl.DecisionType;
import org.herasaf.xacml.core.context.impl.RequestType;
import org.herasaf.xacml.core.policy.impl.RuleType;
import org.springframework.stereotype.Component;

/**
 * <p>
 * The implementation of the {@link PolicyCombiningAlgorithm} with the
 * First-Applicable strategy.
 * </p>
 * <p>
 * The Implementation of the First-Applicable implementation oriented at the
 * sample implementation in the XACML 2.0 specification.
 * </p>
 *
 * <p>
 * See: <a
 * href="http://www.oasis-open.org/committees/tc_home.php?wg_abbrev=xacml#XACML20">
 * OASIS eXtensible Access Control Markup Langugage (XACML) 2.0, Errata 29 June
 * 2006</a> page 137, for further information.
 * </p>
 *
 * @author Stefan Oberholzer
 * @version 1.0
 */
@Component
public class RuleFirstApplicableAlgorithm extends
		RuleUnorderedCombiningAlgorithm {
	private static final long serialVersionUID = -5712159891343195803L;
	// XACML Name of the Combining Algorithm
	private static final String COMBALGOID = "urn:oasis:names:tc:xacml:1.0:rule-combining-algorithm:first-applicable";

//	/**
//	 * Initializes the {@link RuleFirstApplicableAlgorithm} with the given
//	 * {@link TargetMatcher}.
//	 *
//	 * @param targetMatcher
//	 *            The {@link TargetMatcher} to place in the
//	 *            {@link RuleFirstApplicableAlgorithm}
//	 */
//	public RuleFirstApplicableAlgorithm(TargetMatcher targetMatcher) {
//		super(targetMatcher);
//	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return COMBALGOID;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.herasaf.core.combiningAlgorithm.rule.RuleUnorderedCombiningAlgorithm#evaluateRuleList(org.herasaf.core.context.impl.RequestType,
	 *      java.util.List, org.herasaf.core.dataTypes.RequestInformation,
	 *      java.util.Map)
	 */
	@Override
	protected DecisionType evaluateRuleList(RequestType request,
			List<RuleType> rules, RequestInformation requestInfo) {

		for (int i = 0; i < rules.size(); i++) {
			RuleType rule = rules.get(i);
			/*
			 * keeps the actual state and missing attributes of this combining
			 * process.
			 */
			requestInfo.resetStatus();
			DecisionType decision = this.evaluateRule(request, rule,
					requestInfo);
			switch (decision) {
			case DENY:
				return decision;
			case  INDETERMINATE:
				return decision;
			case PERMIT:
				return decision;
			}
		}
		return DecisionType.NOT_APPLICABLE;

	}
}