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

package org.herasaf.xacml.core.combiningAlgorithm.policy.impl;

import java.util.ArrayList;
import java.util.List;

import org.herasaf.xacml.core.combiningAlgorithm.policy.PolicyCombiningAlgorithm;
import org.herasaf.xacml.core.combiningAlgorithm.policy.PolicyOrderedCombiningAlgorithm;
import org.herasaf.xacml.core.context.RequestInformation;
import org.herasaf.xacml.core.context.StatusCode;
import org.herasaf.xacml.core.context.impl.DecisionType;
import org.herasaf.xacml.core.context.impl.MissingAttributeDetailType;
import org.herasaf.xacml.core.context.impl.RequestType;
import org.herasaf.xacml.core.policy.Evaluatable;
import org.herasaf.xacml.core.policy.impl.EffectType;
import org.herasaf.xacml.core.policy.impl.ObligationType;

/**
 * <p>
 * The implementation of the {@link PolicyCombiningAlgorithm} with the
 * Ordered-Permit-Overrides strategy.
 * </p>
 * <p>
 * The Implementation of the Ordered-Permit-override implementation oriented at
 * the sample implementation in the XACML 2.0 specification.
 * </p>
 *
 * <p>
 * See: <a
 * href="http://www.oasis-open.org/committees/tc_home.php?wg_abbrev=xacml#XACML20">
 * OASIS eXtensible Access Control Markup Langugage (XACML) 2.0, Errata 29 June
 * 2006</a> pages 135-137, for further information.
 * </p>
 *
 * @author Stefan Oberholzer
 * @version 1.0
 */
public class PolicyOrderedPermitOverridesAlgorithm extends
		PolicyOrderedCombiningAlgorithm {

	private static final long serialVersionUID = -7925751199361284560L;
	// XACML Name of the Combining Algorithm
	private static final String COMBALGOID = "urn:oasis:names:tc:xacml:1.1:policy-combining-algorithm:ordered-permit-overrides";


	/*
	 * (non-Javadoc)
	 *
	 * @see org.herasaf.core.combiningAlgorithm.policy.PolicyCombiningAlgorithm#evaluate(org.herasaf.core.context.impl.RequestType,
	 *      java.util.List)
	 */
	@Override
	public DecisionType evaluateEvaluatableList(RequestType request,
			List<Evaluatable> possiblePolicies, RequestInformation requestInfo) {
		boolean atLeastOneError = false;
		boolean atLeastOneDeny = false;
		boolean atLeastOnePermit = false;
		/*
		 * keeps the actual state and missing attributes of this combining
		 * process.
		 */
		List<MissingAttributeDetailType> missingAttributes = new ArrayList<MissingAttributeDetailType>();
		List<StatusCode> statusCodes = new ArrayList<StatusCode>();
		List<ObligationType> obligationsOfApplicableEvals = new ArrayList<ObligationType>();
		
		for (int i = 0; i < possiblePolicies.size(); i++) {
			Evaluatable eval = possiblePolicies.get(i);
			
			if(atLeastOnePermit && respectAbandonedEvaluatables && !eval.hasObligations()){
				/* 
				 * If a decision is already made (decisionPermit == true) and the abandoned Obligations must be taken into account
				 * (respectAbandonedEvaluatables == true) and the evaluatable to evaluate (and its sub evaluatables) do not have 
				 * Obligations,
				 * then this iteration can be skipped.
				 */
				break;
			}
			
			DecisionType decision;
			try {
				// Resets the status to go sure, that the returned statuscode is
				// the one of the evaluation.
				requestInfo.resetStatus();
				decision = eval.getCombiningAlg().evaluate(request, eval,
						requestInfo);
				if(decision == DecisionType.PERMIT || decision == DecisionType.DENY){
					obligationsOfApplicableEvals.addAll(eval.getContainedObligations(EffectType.fromValue(decision.toString())));
					obligationsOfApplicableEvals.addAll(requestInfo.getObligations().getObligations());
				}
			} catch (NullPointerException e) {
				/*
				 * If an error occures or a reference returnes null, the answer
				 * has to be treated as indeterminate. See: OASIS eXtensible
				 * Access Control Markup Langugage (XACML) 2.0, Errata 29 June
				 * 2006</a> page 86 and page 136 for further information.
				 */
				requestInfo.updateStatusCode(StatusCode.SYNTAX_ERROR);
				decision = DecisionType.INDETERMINATE;
			}
			switch (decision) {
			case PERMIT:
				if(!respectAbandonedEvaluatables){
					requestInfo.clearObligations();
					requestInfo.addObligations(obligationsOfApplicableEvals, EffectType.PERMIT);
					/*
					* If the result is permit, the statuscode is always ok.
					 */
					requestInfo.resetStatus();
					return DecisionType.PERMIT;
				}
				else {
					atLeastOnePermit = true;
				}
				break;
			case DENY:
				/*
				 * If the decision of the evaluatable is deny, the status has to
				 * be saved.
				 */
				missingAttributes.addAll(requestInfo.getMissingAttributes());
				statusCodes.add(requestInfo.getStatusCode());
				atLeastOneDeny = true;
				break;
			case INDETERMINATE:
				/*
				 * If the decision of the evaluatable is Indeterminate, the
				 * status has to be saved.
				 */
				missingAttributes.addAll(requestInfo.getMissingAttributes());
				statusCodes.add(requestInfo.getStatusCode());
				atLeastOneError = true;
				break;
			case NOT_APPLICABLE:
				break;
			}
			requestInfo.clearObligations();
		}
		if(atLeastOnePermit){
			/*
			 * If the result is permit, the statuscode is always ok.
			 */
			requestInfo.resetStatus();
			requestInfo.addObligations(obligationsOfApplicableEvals, EffectType.PERMIT);
			return DecisionType.PERMIT;
		}
		else if (atLeastOneDeny) {
			// The obligationsOfApplicableEvals may not be revised because it can only contain DENY-Obligations.
			requestInfo.addObligations(obligationsOfApplicableEvals, EffectType.DENY);
			requestInfo.setMissingAttributes(missingAttributes);
//			requestInfo.updateStatusCode(statusCodes);
			requestInfo.resetStatus();
			return DecisionType.DENY;
		}
		else if (atLeastOneError) {
			requestInfo.setMissingAttributes(missingAttributes);
			requestInfo.updateStatusCode(statusCodes);
			return DecisionType.INDETERMINATE;
		}
		requestInfo.clearObligations();
		return DecisionType.NOT_APPLICABLE;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getCombiningAlgorithmId() {
		return COMBALGOID;
	}
}