package org.codehaus.jet.regression.estimators;


/**
 * 
 * @author Mauro Talevi
 */
public class SchwarzInformationCriterionEstimatorTest extends AbstractInformationCriterionEstimatorTestCase {
    
    protected AbstractInformationCriterionEstimator createInformationCriterionEstimator() {
        return new SchwarzInformationCriterionEstimator();
    }

}
