package org.codehaus.jet.regression.estimators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.codehaus.jet.regression.MultipleLinearRegressionEstimator;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauro Talevi
 */
public abstract class AbstractMultipleLinearRegressionEstimatorTest {

    private MultipleLinearRegressionEstimator regression;

    @Before
    public void setUp(){
        regression = createRegressionEstimator();
    }

    protected abstract MultipleLinearRegressionEstimator createRegressionEstimator();
    
    protected abstract int getNumberOfRegressors();
    
    protected abstract int getSampleSize();

    @Test
    public void canEstimateRegressionParameters(){
        double[] beta = regression.estimateRegressionParameters();        
        assertEquals(getNumberOfRegressors(), beta.length);
    }

    @Test
    public void canEstimateResiduals(){
        double[] e = regression.estimateResiduals();
        assertEquals(getSampleSize(), e.length);
    }
    
    @Test
    public void canEstimateRegressionParametersVariance(){
        double[][] variance = regression.estimateRegressionParametersVariance();
        assertEquals(getNumberOfRegressors(), variance.length);
    }

    @Test
    public void canEstimateRegressandVariance(){
        double variance = regression.estimateRegressandVariance();
        assertTrue(variance > 0.0);
    }   

}
