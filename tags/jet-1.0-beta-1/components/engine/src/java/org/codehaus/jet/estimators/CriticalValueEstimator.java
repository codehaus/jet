package org.codehaus.jet.estimators;



/**
 * Estimator of critical value for unit roots and cointegration tests, as detailed in 
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
public class CriticalValueEstimator extends AbstractStatisticValueEstimator {
   
    
    /**
     * @param np
     * @param threshold
     */
    public CriticalValueEstimator(int np, double threshold) {
        super(np, threshold);
    }
        
    /**
     * @param responseSurfaceEvaluator
     * @param np
     * @param threshold
     */
    public CriticalValueEstimator(ResponseSurfaceEvaluator responseSurfaceEvaluator, int np, double threshold) {
        super(responseSurfaceEvaluator, np, threshold);
    }
    
    public double estimateValue(double[] probs, double[] norm, double[] weights, double[][]beta,
            int sampleSize, int[] params, double level){
        validateParams(sampleSize, level);
        int min = calculateMinimizingIndex(probs, level, 1e-6);
        int np = getNp();
        int nvar = 4;
        double[] gamma = glsRegression(
                toYSample(beta, min, np, sampleSize, params),
                toXSample(norm, min, np, nvar),
                toCovariance(probs, weights, min, np));
        double threshold = getThreshold();
        if ( tTest(gamma, nvar, threshold) ){
            return powerSeries(gamma, inverseCumulativeNormal(level), nvar);            
        } else {
            nvar = 3;
            gamma = glsRegression(
                    toYSample(beta, min, np, sampleSize, params),
                    toXSample(norm, min, np, nvar),
                    toCovariance(probs, weights, min, np));
            return powerSeries(gamma, inverseCumulativeNormal(level), nvar);            
        }
    }

    public double estimateValue(double[] probs, double[] norm, double[] weights, double[] criticalValues,
            double level){
        int min = calculateMinimizingIndex(probs, level, 1e-6);
        int np = getNp();
        int nvar = 4;        
        double[] gamma = glsRegression(
                toYSample(criticalValues, min, np),
                toXSample(norm, min, np, nvar),
                toCovariance(probs, weights, min, np));
        double threshold = getThreshold();
        if ( tTest(gamma, nvar, threshold) ){
            return powerSeries(gamma, inverseCumulativeNormal(level), nvar);            
        } else {
            nvar = 3;
            gamma = glsRegression(
                    toYSample(criticalValues, min, np),
                    toXSample(norm, min, np, nvar),
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
