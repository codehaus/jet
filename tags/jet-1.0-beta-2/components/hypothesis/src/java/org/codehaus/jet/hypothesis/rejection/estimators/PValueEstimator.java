package org.codehaus.jet.hypothesis.rejection.estimators;

import org.codehaus.jet.hypothesis.rejection.ResponseSurfaceEvaluator;

/**
 * Estimator of p-value for unit roots and cointegration tests.
 * 
 * @author Mauro Talevi
 * @see RejectionValueEstimator
 */
public class PValueEstimator extends AbstractRejectionValueEstimator {

    /**
     * Creates a PValueEstimator
     * @param numberOfPoints the number of points used in the regression
     * @param threshold the threshold used for the t-test
     */
    public PValueEstimator(int numberOfPoints, double threshold) {
        super(numberOfPoints, threshold);
    }
        
    /**
     * Creates a PValueEstimator
     *
     * @param responseSurfaceEvaluator the ResponseSurfaceEvaluator
     * @param numberOfPoints the number of points used in the regression
     * @param threshold the threshold used for the t-test
     */
    public PValueEstimator(ResponseSurfaceEvaluator responseSurfaceEvaluator, int numberOfPoints, double threshold) {
        super(responseSurfaceEvaluator, numberOfPoints, threshold);
    }
    
    public double estimateValue(double[] norms, double[] probs, double[] weights, double[][]beta,
            int sampleSize, int[] params, double level){
        validateParams(sampleSize, level);
        double[] criticalValues = toCriticalValues(beta, sampleSize, params);
        return estimateAsymptoticValue(norms, probs, weights, criticalValues, level);
    }
    
    public double estimateAsymptoticValue(double[] norms, double[] probs, double[] weights, double[] criticalValues,
            double level){
        int min = calculateMinimizingIndex(criticalValues, level, 0.0d);
        int np = getNumberOfPoints();
        int nvar = 4;
        double[] gamma = glsRegression(
                toYSample(norms, min, np),
                toXSample(criticalValues, min, np, nvar),
                toCovariance(probs, weights, min, np));
        double threshold = getThreshold();
        if ( tTest(gamma, nvar, threshold) ){
            return cumulativeNormal(powerSeries(gamma, level, nvar));
        } else {
            nvar = 3;
            gamma = glsRegression(
                    toYSample(norms, min, np),
                    toXSample(criticalValues, min, np, nvar),
                    toCovariance(probs, weights, min, np));
            return cumulativeNormal(powerSeries(gamma, level, nvar));
        }
    }

}