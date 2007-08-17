package org.codehaus.jet.hypothesis.rejection.estimators;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.NormalDistribution;
import org.apache.commons.math.distribution.NormalDistributionImpl;
import org.codehaus.jet.hypothesis.rejection.RejectionValueEstimator;
import org.codehaus.jet.hypothesis.rejection.ResponseSurfaceEvaluator;
import org.codehaus.jet.regression.MultipleLinearRegressionEstimator;
import org.codehaus.jet.regression.estimators.GLSMultipleLinearRegressionEstimator;

/**
 * Abstract base RejectionValueEstimator.  Concrete implementations include the 
 * CriticalValueEstimator and PValueEstimator.
 * 
 * @author Mauro Talevi
 * @see CriticalValueEstimator
 * @see PValueEstimator
 */
public abstract class AbstractRejectionValueEstimator implements RejectionValueEstimator {

    protected NormalDistribution distribution = new NormalDistributionImpl();
    protected MultipleLinearRegressionEstimator gls = new GLSMultipleLinearRegressionEstimator();

    protected ResponseSurfaceEvaluator responseSurfaceEvaluator;
    private int numberOfPoints;
    private double threshold;    
        
    /**
     * Creates an AbstractRejectionValueEstimator
     * @param numberOfPoints
     * @param threshold
     */
    public AbstractRejectionValueEstimator(int numberOfPoints, double threshold) {
        this(null, numberOfPoints, threshold);
    }
    
    /**
     * @param responseSurfaceEvaluator
     * @param numberOfPoints
     * @param threshold
     */
    public AbstractRejectionValueEstimator(ResponseSurfaceEvaluator responseSurfaceEvaluator, int numberOfPoints, double threshold) {
        this.responseSurfaceEvaluator = responseSurfaceEvaluator;
        this.numberOfPoints = numberOfPoints;
        this.threshold = threshold;
    }
    
    protected void validateParams(int sampleSize, double level) {
        if ( sampleSize < 0 ){
            throw new IllegalArgumentException("sample size must be a non-negative integer");
        }
        if ( level < 0.0 || level > 1.0){
            throw new IllegalArgumentException("level must be a number between 0.0 and 1.0");
        }
    }    
    

    protected double powerSeries(double[] series, double x, int order) {
        double value = 0;
        for (int i = 0; i < order; i++) {
            value = value + series[i]*pow(x, i);
        }
        return value;
    }
    

    protected int calculateMinimizingIndex(double[] values, double level, double threshold){
        double diff = 0;
        double minDiff = 1000.0d;
        int min = 0;
        for (int i = 0; i < values.length; i++) {
            diff = abs(level-values[i]);
            if ( diff < minDiff ) {
                minDiff = diff;
                min = i;
                if ( minDiff < threshold ){
                   return min; 
                }
            }
        }
        return min;
    }
   
    protected double[] extractMiddleColumnPoints(double[][] beta, int first, int min, int np) {
        double[] values = new double[np];
        for (int i = 0; i < np; i++) {
            int index = toMiddleIndex(min, np, first + i);
            values[i] = beta[index][0];
        }
        return values;
    }

    protected double[] extractTailColumnPoints(double[][] beta, int first, int np, boolean upper) {
        double[] values = new double[np];
        for (int i = 0; i < np; i++) {
            int index = first + i;
            if ( upper ) {
                index = toUpperIndex(first + i);
            }
            values[i] = beta[index][0];
        }
        return values;
    }    

    private double[] extractRowPoints(double[][] beta, int row) {
        int dimension =  beta[0].length;
        double[] values = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            values[i] = beta[row][i];
        }        
        return values;
    }
    
    protected boolean isMiddle(int min, int np) {
        return min > np/2 && min < 220 - np/2;
    }

    protected boolean isLower(int min, int np) {
        return min < np;
    }

    protected int toMiddleIndex(int min, int np, int i) {
        return min - np/2 + i;
    }
    
    protected int toUpperIndex(int i) {
        return 220 - i;
    }
    
    protected int getTailPoints(int min, int np, boolean upper) {
        int points = 0;
        if ( upper ){
            points = 221 - min + np/2;
        } else {
            points = min + np/2;            
        }        
        return points < 5 ? 5 : points ;
    }
   
    protected double[][] toXSample(double[] norms, int min, int np, int nvar) {
        if ( isMiddle(min, np) ){
            return toMiddleXSample(norms, min, np, nvar);
        } else {
            if ( isLower(min,np) ){
                return toTailXSample(norms, getTailPoints(min, np, false), nvar, false);
            } else {
                return toTailXSample(norms, getTailPoints(min, np, true), nvar, true);
            }
        }
    }

    protected double[][] toMiddleXSample(double[] values, int min, int np, int nvar){
        double[][] x = new double[np][nvar];
        for (int i = 0; i < np; i++) {
            int middleI = toMiddleIndex(min, np, i);
            x[i][0] = 1.0d;
            x[i][1] = values[middleI];
            x[i][2] = x[i][1] * values[middleI];
            if ( nvar > 3 ){
                x[i][3] = x[i][2] * values[middleI];
            }
        }
        return x;
    }
    
    protected double[][] toTailXSample(double[] values, int np, int nvar, boolean upper){
        double[][] x = new double[np][nvar];
        for (int i = 0; i < np; i++) {
            int tailI = i;
            if ( upper ){
                tailI = toUpperIndex(i);
            }
            x[i][0] = 1.0d;
            x[i][1] = values[tailI];
            x[i][2] = x[i][1]*values[tailI];
            if ( nvar > 3 ){
                x[i][3] = x[i][2]*values[tailI];
            }
        }
        return x;
    }
        
    protected double[][] toCovariance(double[] probs, double[] weights, int min, int np) {
        if ( isMiddle(min, np) ){
            return toMiddleCovariance(probs, weights, min, np);
        } else {
            if ( isLower(min,np) ){
                return toTailCovariance(probs, weights, getTailPoints(min, np, false), false);
            } else {
                return toTailCovariance(probs, weights, getTailPoints(min, np, true), true);
            }
        }
    }
           
    protected double[][] toMiddleCovariance(double[] probs, double[] weights, int min, int np){
        double[][] o = new double[np][np];
        for (int i = 0; i < np; i++) {
            int middleI = toMiddleIndex(min, np, i);
            for (int j = 0; j < np; j++) {
                int middleJ = toMiddleIndex(min, np, j);
                double top = probs[middleI]*(1.0d - probs[middleJ]);
                double bottom = probs[middleJ]*(1.0d - probs[middleI]);
                o[i][j] = weights[middleI]*weights[middleJ]*sqrt(top/bottom);
            }
        }
        for (int i = 0; i < np; i++) {
            for (int j = 0; j < np; j++) {
                o[j][i] = o[i][j];
            }
        }
        return o;
    }

    protected double[][] toTailCovariance(double[] probs, double[] weights, int np, boolean upper){
        double[][] o = new double[np][np];
        for (int i = 0; i < np; i++) {
            for (int j = 0; j < np; j++) {
                if ( upper ){
                    o[i][j] = 0.0d;
                    if ( i == j ) o[i][j] = 1.0d;
                } else {
                    double top = probs[i]*(1.0d - probs[j]);
                    double bottom = probs[j]*(1.0d - probs[i]);
                    o[i][j] = weights[i]*weights[j]*sqrt(top/bottom);
                }
            }
        }
        for (int i = 0; i < np; i++) {
            for (int j = 0; j < np; j++) {
                o[j][i] = o[i][j];
            }
        }
        return o;
    }
    
    protected double[] toCriticalValues(double[][] beta, int sampleSize, int[] params) {
        double[] y = new double[beta.length];
        for (int i = 0; i < y.length; i++) {
            y[i] = responseSurfaceEvaluator.evaluate(extractRowPoints(beta, i), sampleSize, params);
        }
        return y;
    }

    protected double[] glsRegression(double[] y, double[][] x, double[][] omega) {
        gls.addData(y, x, omega);
        return gls.estimateRegressionParameters();
    }
    
    protected boolean tTest(double[] gamma, int element, double threshold) {
        double[][] variance = gls.estimateRegressionParametersVariance();
        double t = abs(gamma[element-1])/sqrt(gls.estimateRegressandVariance()*variance[element-1][element-1]);
        return ( t > threshold );
    }
    
    protected double cumulativeNormal(double level) {
        try {
            return distribution.cumulativeProbability(level);
        } catch (MathException e) {
            throw new IllegalArgumentException("Failed to calculate the cumulative probability for "+level, e);
        }
    }

    protected double inverseCumulativeNormal(double level) {
        try {
            return distribution.inverseCumulativeProbability(level);
        } catch (MathException e) {
            throw new IllegalArgumentException("Failed to calculate the inverse cumulative probability for "+level, e);
        }
    }

    protected int getNumberOfPoints() {
        return numberOfPoints;
    }

    protected double getThreshold() {
        return threshold;
    }

    protected double[] toYSample(double[] values, int min, int np) {
        if ( isMiddle(min, np) ){
            return toMiddleYSample(values, min, np);
        } else {
            if ( isLower(min,np) ){
                return toTailYSample(values, getTailPoints(min, np, false), false);
            } else {
                return toTailYSample(values, getTailPoints(min, np, true), true);
            }
        }
    }

    protected double[] toMiddleYSample(double[] values, int min, int np) {
        double[] y = new double[np];
        for (int i = 0; i < y.length; i++) {
            int middleI = toMiddleIndex(min, np, i);
            y[i] = values[middleI];
        }
        return y;
    }

    protected double[] toTailYSample(double[] values, int np, boolean upper) {
        double[] y = new double[np];
        for (int i = 0; i < y.length; i++) {
            int tailI = i;
            if ( upper ){
                tailI = toUpperIndex(i);
            }
            y[i] = values[tailI];
        }
        return y;
    }

}
