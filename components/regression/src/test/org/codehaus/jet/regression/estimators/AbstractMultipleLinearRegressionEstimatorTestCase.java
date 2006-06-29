package org.codehaus.jet.regression.estimators;

import junit.framework.TestCase;

import org.codehaus.jet.regression.MultipleLinearRegressionEstimator;

/**
 * 
 * @author Mauro Talevi
 */
public abstract class AbstractMultipleLinearRegressionEstimatorTestCase extends TestCase {

    private MultipleLinearRegressionEstimator regression;

    public void setUp(){
        regression = createRegressionEstimator();
    }

    protected abstract MultipleLinearRegressionEstimator createRegressionEstimator();
    
    protected abstract int getNumberOfRegressors();
    
    protected abstract int getSampleSize();

    public void testCanEstimateRegressionParameters(){
        double[] beta = regression.estimateRegressionParameters();        
        assertEquals(getNumberOfRegressors(), beta.length);
    }

    public void testCanEstimateResiduals(){
        double[] e = regression.estimateResiduals();
        assertEquals(getSampleSize(), e.length);
    }
    
    public void testCanEstimateRegressionParametersVariance(){
        double[][] variance = regression.estimateRegressionParametersVariance();
        assertEquals(getNumberOfRegressors(), variance.length);
    }

    public void testCanEstimateRegressandVariance(){
        double variance = regression.estimateRegressandVariance();
        assertTrue(variance > 0.0);
    }
    

}
