package org.codehaus.jet.regression.estimators;


/**
 * 
 * @author Mauro Talevi
 */
public class AkaikeInformationCriterionEstimatorTest extends AbstractInformationCriterionEstimatorTestCase {
  
    protected AbstractInformationCriterionEstimator createInformationCriterionEstimator() {
        return new AkaikeInformationCriterionEstimator();
    }

}
