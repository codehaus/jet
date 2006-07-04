package org.codehaus.jet.hypothesis.rejection.estimators;

import org.codehaus.jet.hypothesis.rejection.ResponseSurfaceEvaluator;



/**
 * Estimator of critical value for unit roots and cointegration tests.
 * 
 * @author Mauro Talevi
 * @see RejectionValueEstimator
 */
public class CriticalValueEstimator extends AbstractRejectionValueEstimator {
   
    
    /**
     * Creates a CriticalValueEstimator
     * @param numberOfPoints the number of points used in the regression
     * @param threshold the threshold used for the t-test
     */
    public CriticalValueEstimator(int numberOfPoints, double threshold) {
        super(numberOfPoints, threshold);
    }
        
    /**
     * Creates a CriticalValueEstimator
     *
     * @param responseSurfaceEvaluator the ResponseSurfaceEvaluator
     * @param numberOfPoints the number of points used in the regression
     * @param threshold the threshold used for the t-test
     */
    public CriticalValueEstimator(ResponseSurfaceEvaluator responseSurfaceEvaluator, int numberOfPoints, double threshold) {
        super(responseSurfaceEvaluator, numberOfPoints, threshold);
    }
    
    public double estimateValue(double[] norms, double[] probs, double[] weights, double[][]beta,
            int sampleSize, int[] params, double level){
        validateParams(sampleSize, level);
        int min = calculateMinimizingIndex(probs, level, 1e-6);
        int np = getNumberOfPoints();
        int nvar = 4;
        double[] gamma = glsRegression(
                toYSample(beta, min, np, sampleSize, params),
                toXSample(norms, min, np, nvar),
                toCovariance(probs, weights, min, np));
        double threshold = getThreshold();
        if ( tTest(gamma, nvar, threshold) ){
            return powerSeries(gamma, inverseCumulativeNormal(level), nvar);            
        } else {
            nvar = 3;
            gamma = glsRegression(
                    toYSample(beta, min, np, sampleSize, params),
                    toXSample(norms, min, np, nvar),
                    toCovariance(probs, weights, min, np));
            return powerSeries(gamma, inverseCumulativeNormal(level), nvar);            
        }
    }

    public double estimateAsymptoticValue(double[] norms, double[] probs, double[] weights, double[] criticalValues,
            double level){
        int min = calculateMinimizingIndex(probs, level, 1e-6);
        int np = getNumberOfPoints();
        int nvar = 4;        
        double[] gamma = glsRegression(
                toYSample(criticalValues, min, np),
                toXSample(norms, min, np, nvar),
                toCovariance(probs, weights, min, np));
        double threshold = getThreshold();
        if ( tTest(gamma, nvar, threshold) ){
            return powerSeries(gamma, inverseCumulativeNormal(level), nvar);            
        } else {
            nvar = 3;
            gamma = glsRegression(
                    toYSample(criticalValues, min, np),
                    toXSample(norms, min, np, nvar),
                    toCovariance(probs, weights, min, np));
            return powerSeries(gamma, inverseCumulativeNormal(level), nvar);            
        }
    }
    
    protected double[] toYSample(double[][] beta, int min, int np, int sampleSize, int[] params){
        if ( isMiddle(min, np) ){
            return toMiddleYSample(beta, min, np, sampleSize, params);
        } else {
            if ( isLower(min,np) ){
                return toTailYSample(beta, min, getTailPoints(min, np, false), sampleSize, params, false);
            } else {
                return toTailYSample(beta, min, getTailPoints(min, np, true), sampleSize, params, true);
            }
        }
    }
     
    protected double[] toMiddleYSample(double[][] beta, int min, int np, int sampleSize, int[] params) {
        double[] y = new double[np];
        for (int i = 0; i < y.length; i++) {
            y[i] = responseSurfaceEvaluator.evaluate(extractMiddleColumnPoints(beta, i, min, np), sampleSize, params);
        }
        return y;
    }

    protected double[] toTailYSample(double[][] beta, int min, int np, int sampleSize, int[] params, boolean upper) {
        double[] y = new double[np];
        for (int i = 0; i < y.length; i++) {
            y[i] = responseSurfaceEvaluator.evaluate(extractTailColumnPoints(beta, i, np, false), sampleSize, params);
        }
        return y;
    }
    
}
