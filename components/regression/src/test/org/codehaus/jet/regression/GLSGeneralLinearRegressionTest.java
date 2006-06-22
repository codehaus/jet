package org.codehaus.jet.regression;

import junit.framework.TestCase;

/**
 * 
 * @author Mauro Talevi
 */
public class GLSGeneralLinearRegressionTest extends TestCase {

    private double[] y;
    private double[][] x;
    private double[][] omega;
    private GeneralLinearRegression regression;

    public void setUp(){
        y = new double[]{11.0, 12.0, 13.0, 14.0, 15.0, 16.0};
        x = new double[6][];
        x[0] = new double[]{0, 0, 0, 0, 0};
        x[1] = new double[]{2.0, 0, 0, 0, 0};
        x[2] = new double[]{0, 3.0, 0, 0, 0};
        x[3] = new double[]{0, 0, 4.0, 0, 0};
        x[4] = new double[]{0, 0, 0, 5.0, 0};
        x[5] = new double[]{0, 0, 0, 0, 6.0};
        omega = new double[6][];
        omega[0] = new double[]{1.0, 0, 0, 0, 0, 0};
        omega[1] = new double[]{0, 2.0, 0, 0, 0, 0};
        omega[2] = new double[]{0, 0, 3.0, 0, 0, 0};
        omega[3] = new double[]{0, 0, 0, 4.0, 0, 0};
        omega[4] = new double[]{0, 0, 0, 0, 5.0, 0};
        omega[5] = new double[]{0, 0, 0, 0, 0, 6.0};
        regression = createRegression(y, x, omega);
    }
    
    public void testCanCalculateBeta(){
        double[] beta = regression.getBeta();        
        assertEquals(x[0].length, beta.length);
    }

    public void testCanCalculateResiduals(){
        double[] e = regression.getResiduals();
        assertEquals(y.length, e.length);
    }
    
    public void testCanCalculateBetaVariance(){
        double[][] variance = regression.getBetaVariance();
        assertEquals(x[0].length, variance.length);
    }

    public void testCanCalculateYVariance(){
        double variance = regression.getYVariance();
        assertTrue(variance > 0.0);
    }
    
    private GeneralLinearRegression createRegression(double[] y, double[][] x, double[][] omega) {
        GeneralLinearRegression regression = new GLSGeneralLinearRegression();
        regression.addData(y, x, omega);
        return regression;
    }
    
}
