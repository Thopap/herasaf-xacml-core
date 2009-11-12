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

import org.herasaf.xacml.core.combiningAlgorithm.rule.RuleUnorderedCombiningAlgorithm;
import org.herasaf.xacml.core.context.RequestInformation;
import org.herasaf.xacml.core.context.impl.DecisionType;
import org.herasaf.xacml.core.context.impl.RequestType;
import org.herasaf.xacml.core.policy.impl.RuleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * TODO JAVADOC
 * 
 * <p>
 * The implementation of the policy combining algorithm with the
 * First-Applicable strategy.
 * </p>
 * <p>
 * The Implementation of the First-Applicable implementation oriented at the
 * sample implementation in the XACML 2.0 specification.
 * </p>
 * 
 * <p>
 * See: <a href=
 * "http://www.oasis-open.org/committees/tc_home.php?wg_abbrev=xacml#XACML20">
 * OASIS eXtensible Access Control Markup Langugage (XACML) 2.0, Errata 29 June
 * 2006</a> page 137, for further information.
 * </p>
 * 
 * @author Florian Huonder
 * @author Stefan Oberholzer
 * @author Ren� Eggenschwiler
 * @version 1.0
 */
public class RuleFirstApplicableAlgorithm extends RuleUnorderedCombiningAlgorithm {
	// XACML Name of the Combining Algorithm
	private static final String COMBALGOID = "urn:oasis:names:tc:xacml:1.0:rule-combining-algorithm:first-applicable";
	private final Logger logger = LoggerFactory.getLogger(RuleFirstApplicableAlgorithm.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCombiningAlgorithmId() {
		return COMBALGOID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.herasaf.core.combiningAlgorithm.rule.RuleUnorderedCombiningAlgorithm
	 * #evaluateRuleList(org.herasaf.core.context.impl.RequestType,
	 * java.util.List, org.herasaf.core.dataTypes.RequestInformation,
	 * java.util.Map)
	 */
	@Override
	public DecisionType evaluateRuleList(RequestType request, List<RuleType> rules, RequestInformation requestInfo) {

		for (int i = 0; i < rules.size(); i++) {
			RuleType rule = rules.get(i);
			/*
			 * keeps the actual state and missing attributes of this combining
			 * process.
			 */
			requestInfo.resetStatus();

			if (logger.isDebugEnabled()) {
				MDC.put(MDC_RULE_ID, rule.getRuleId());
				logger.debug("Starting evaluation of: {}", rule.getRuleId());
			}

			DecisionType decision = this.evaluateRule(request, rule, requestInfo);

			if (logger.isDebugEnabled()) {
				MDC.put(MDC_RULE_ID, rule.getRuleId());
				logger.debug("Evaluation of {} was: {}", rule.getRuleId(), decision.toString());
				MDC.remove(MDC_RULE_ID);
			}

			switch (decision) {
			// default case is not required here.
			case DENY:
			case INDETERMINATE:
			case PERMIT:
				return decision;
			}
		}
		return DecisionType.NOT_APPLICABLE;

	}
}