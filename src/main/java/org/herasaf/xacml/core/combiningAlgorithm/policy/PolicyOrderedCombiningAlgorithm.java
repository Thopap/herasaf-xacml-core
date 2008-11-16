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

package org.herasaf.xacml.core.combiningAlgorithm.policy;

import java.util.List;

import org.herasaf.xacml.core.combiningAlgorithm.AbstractCombiningAlgorithm;
import org.herasaf.xacml.core.context.RequestInformation;
import org.herasaf.xacml.core.context.StatusCode;
import org.herasaf.xacml.core.context.impl.DecisionType;
import org.herasaf.xacml.core.context.impl.RequestType;
import org.herasaf.xacml.core.policy.Evaluatable;
import org.herasaf.xacml.core.policy.impl.PolicySetType;

/**
 * Abstract class {@link PolicyCombiningAlgorithm} implementation that evaluate
 * the included Evaluatables ordered.
 *
 * @author Stefan Oberholzer
 * @version 1.0
 *
 */
public abstract class PolicyOrderedCombiningAlgorithm extends
		AbstractCombiningAlgorithm implements PolicyCombiningAlgorithm {
	private static final long serialVersionUID = 738279366961769108L;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.herasaf.core.combiningAlgorithm.CombiningAlgorithm#evaluate(org.herasaf.core.context.impl.RequestType,
	 *      org.herasaf.core.policy.impl.Evaluatable,
	 *      org.herasaf.core.dataTypes.RequestInformation)
	 */
	public DecisionType evaluate(RequestType request,
			Evaluatable evals, RequestInformation requestInfo) {
		DecisionType decision = matchTarget(request,
				evals.getTarget(), requestInfo);

		if (decision != DecisionType.PERMIT) {
			return decision;
		}
		try {
			DecisionType dec = this
					.evaluateEvaluatableList(request, ((PolicySetType) evals)
							.getOrderedEvaluatables(requestInfo), requestInfo);
			/*
			 * The evaluateEvaluatableList method may set the targetMatched
			 * value to false. So it has to be set to true to go sure that it is
			 * true.
			 */
			requestInfo.setTargetMatched(true);
			return dec;
		} catch (ClassCastException e) {
			/*
			 * If an error occures, indeterminate has to be returned. See: OASIS
			 * eXtensible Access Control Markup Langugage (XACML) 2.0, Errata 29
			 * June 2006</a> page 86, chapter "Syntax and type errors" for
			 * further information.
			 */
			requestInfo.updateStatusCode(StatusCode.SYNTAX_ERROR);
			return DecisionType.INDETERMINATE;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.herasaf.core.combiningAlgorithm.policy.PolicyCombiningAlgorithm#evaluate(org.herasaf.core.context.impl.RequestType,
	 *      java.util.List)
	 */
	public abstract DecisionType evaluateEvaluatableList(
			RequestType request, List<Evaluatable> possiblePolicies,
			RequestInformation requestInfos);

}