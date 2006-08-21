package org.codehaus.jet.regression.estimators;

import static java.lang.Math.E;

/**
 * 
 * @author Mauro Talevi
 */
public class HannanQuinnInformationCriterionEstimatorTest extends AbstractInformationCriterionEstimatorTestCase {

    public void testCanCalculateIC(){
        assertEquals(0, estimator.calculateIC(0, 100, 1.0), 1e-6);
        assertEquals(1.0305435925161581, estimator.calculateIC(1, 100, E), 1e-6);
    }
    
    protected AbstractInformationCriterionEstimator createInformationCriterionEstimator() {
        return new HannanQuinnInformationCriterionEstimator();
    }

}
