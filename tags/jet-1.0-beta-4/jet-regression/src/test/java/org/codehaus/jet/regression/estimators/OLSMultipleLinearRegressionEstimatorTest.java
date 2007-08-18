package org.codehaus.jet.regression.estimators;

import org.codehaus.jet.regression.MultipleLinearRegressionEstimator;
import org.junit.Before;


/**
 * 
 * @author Mauro Talevi
 */
public class OLSMultipleLinearRegressionEstimatorTest extends AbstractMultipleLinearRegressionEstimatorTest {

    private double[] y;
    private double[][] x;
    
    @Before
    public void setUp(){
        y = new double[]{11.0, 12.0, 13.0, 14.0, 15.0, 16.0};
        x = new double[6][];
        x[0] = new double[]{1.0, 0, 0, 0, 0, 0};
        x[1] = new double[]{1.0, 2.0, 0, 0, 0, 0};
        x[2] = new double[]{1.0, 0, 3.0, 0, 0, 0};
        x[3] = new double[]{1.0, 0, 0, 4.0, 0, 0};
        x[4] = new double[]{1.0, 0, 0, 0, 5.0, 0};
        x[5] = new double[]{1.0, 0, 0, 0, 0, 6.0};
        super.setUp();
    }

    protected MultipleLinearRegressionEstimator createRegressionEstimator() {
        MultipleLinearRegressionEstimator regression = new OLSMultipleLinearRegressionEstimator();
        regression.addData(y, x, null);
        return regression;
    }

    protected int getNumberOfRegressors() {
        return x[0].length;
    }

    protected int getSampleSize() {
        return y.length;
    }

}
