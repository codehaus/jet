package org.codehaus.jet.regression.estimators;



/**
 * 
 * @author Mauro Talevi
 */
public class NoopInformationCriterionEstimatorTest extends AbstractInformationCriterionEstimatorTestCase {


    public void testCanCalculateRegressands(){
        double[] regressands = estimator.toRegressands(y, p);
        assertEquals(T-p, regressands.length);
    }

    public void testCanCalculateRegressors(){
        double[][] regressors = estimator.toRegressors(y, p);
        assertEquals(T-p, regressors.length);
        for (int i = 0; i < regressors.length; i++) {
            assertEquals(p, regressors[i].length);
        }
    }
    
    public void testCanMinimiseIC(){
        int min =  estimator.minimiseIC(p, p+5);       
        assertEquals(p, min);
    }
    
    protected AbstractInformationCriterionEstimator createInformationCriterionEstimator() {
        return new NoopInformationCriterionEstimator();
    }
    
    static class NoopInformationCriterionEstimator extends AbstractInformationCriterionEstimator {

        protected NoopInformationCriterionEstimator() {
            super(new GLSMultipleLinearRegressionEstimator());
        }

        public double estimateIC(int lag) {
            return lag;
        }

        protected double calculateIC(int p, int t, double var) {
            return 0;
        }
        
    }
}
