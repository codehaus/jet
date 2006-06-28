package org.codehaus.jet.regression.estimators;


/**
 * 
 * @author Mauro Talevi
 */
public class HannanQuinnInformationCriterionEstimatorTest extends AbstractInformationCriterionEstimatorTestCase {

    protected AbstractInformationCriterionEstimator createInformationCriterionEstimator() {
        return new HannanQuinnInformationCriterionEstimator();
    }

}
