package org.codehaus.jet.hypothesis.rejection.estimators;

import org.codehaus.jet.hypothesis.rejection.ResponseSurfaceEvaluator;

/**
 * Estimator of p-value for unit roots and cointegration tests, as detailed in 
 * <a href="http://qed.econ.queensu.ca/jae/1996-v11.6/mackinnon">
 * James G. MacKinnon, "Numerical Distribution Functions for Unit Root and Cointegration Tests", 
 * Journal of Applied Econometrics, Vol. 11, No. 6, 1996, 601-618
 * </a> and 
 * <a href="http://qed.econ.queensu.ca/faculty/mackinnon/ecmtest">
 * Neil R. Ericsson and James G. MacKinnon (2002) "Distributions of Error  Correction Tests for Cointegration", 
 * Econometrics Journal, 5, 2002, 285-318
 * </a>
 *
 * @author Mauro Talevi
 */
public class PValueEstimator extends AbstractRejectionValueEstimator {


    /**
     * @param np
     * @param threshold
     */
    public PValueEstimator(int np, double threshold) {
        super(np, threshold);
    }
    /**
     * @param responseSurfaceEvaluator
     * @param np
     * @param threshold
     */
    public PValueEstimator(ResponseSurfaceEvaluator responseSurfaceEvaluator, int np, double threshold) {
        super(responseSurfaceEvaluator, np, threshold);
    }

    public double estimateValue(double[] probs, double[] norm, double[] weights, double[][]beta,
            int sampleSize, int[] params, double level){
        validateParams(sampleSize, level);
        double[] criticalValues = toCriticalValues(beta, sampleSize, params);
        return estimateValue(probs, norm, weights, criticalValues, level);
    }
    
    public double estimateValue(double[] probs, double[] norm, double[] weights, double[] criticalValues,
            double level){
        int min = calculateMinimizingIndex(criticalValues, level, 0.0d);
        int np = getNp();
        int nvar = 4;
        double[] gamma = glsRegression(
                toYSample(norm, min, np),
                toXSample(criticalValues, min, np, nvar),
                toCovariance(probs, weights, min, np));
        double threshold = getThreshold();
        if ( tTest(gamma, nvar, threshold) ){
            return cumulativeNormal(powerSeries(gamma, level, nvar));
        } else {
            nvar = 3;
            gamma = glsRegression(
                    toYSample(norm, min, np),
                    toXSample(criticalValues, min, np, nvar),
                    toCovariance(probs, weights, min, np));
            return cumulativeNormal(powerSeries(gamma, level, nvar));
        }
    }

}