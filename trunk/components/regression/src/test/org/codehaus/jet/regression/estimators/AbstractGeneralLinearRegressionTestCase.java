package org.codehaus.jet.regression.estimators;

import junit.framework.TestCase;

import org.codehaus.jet.regression.GeneralLinearRegression;

/**
 * 
 * @author Mauro Talevi
 */
public abstract class AbstractGeneralLinearRegressionTestCase extends TestCase {

    private GeneralLinearRegression regression;

    public void setUp(){
        regression = createRegression();
    }

    protected abstract GeneralLinearRegression createRegression();
    
    protected abstract int getNumberOfRegressors();
    
    protected abstract int getSampleSize();

    public void testCanCalculateBeta(){
        double[] beta = regression.getBeta();        
        assertEquals(getNumberOfRegressors(), beta.length);
    }

    public void testCanCalculateResiduals(){
        double[] e = regression.getResiduals();
        assertEquals(getSampleSize(), e.length);
    }
    
    public void testCanCalculateBetaVariance(){
        double[][] variance = regression.getBetaVariance();
        assertEquals(getNumberOfRegressors(), variance.length);
    }

    public void testCanCalculateYVariance(){
        double variance = regression.getYVariance();
        assertTrue(variance > 0.0);
    }
    

}
