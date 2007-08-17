package org.codehaus.jet.regression.estimators;

import static java.lang.Math.E;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author Mauro Talevi
 */
public class HannanQuinnInformationCriterionEstimatorTest extends AbstractInformationCriterionEstimatorTest {

    @Test
    public void canCalculateIC(){
        assertEquals(0, estimator.calculateIC(0, 100, 1.0), 1e-6);
        assertEquals(1.0305435925161581, estimator.calculateIC(1, 100, E), 1e-6);
    }
    
    protected AbstractInformationCriterionEstimator createInformationCriterionEstimator() {
        return new HannanQuinnInformationCriterionEstimator();
    }

}
