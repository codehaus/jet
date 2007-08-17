package org.codehaus.jet.hypothesis.rejection.evaluators;

import static java.lang.Math.pow;

import org.codehaus.jet.hypothesis.rejection.ResponseSurfaceEvaluator;

/**
 * Abstract base class for ResponseSurfaceEvaluator implementations
 * 
 * @author Mauro Talevi
 */
public abstract class AbstractResponseSurfaceEvaluator implements ResponseSurfaceEvaluator {

    /**
     * Calculates the independent variable of the power series
     * <pre>
     * x = 1/T*, T* = T-correction
     * </pre>
     * 
     * @param T the sample size
     * @param correction the sample size correction
     * @return The value of x, or <code>1</code> if <code>T* <= 0 </code>
     */
    protected double calculateX(int T, int correction) {
        int d = T - correction;
        if ( d <= 0 ) d = 1;
        return 1.0/d;
    }

    /**
     * Evaluates the power series in <code>x</code> up to the given order
     * with coefficients provided 
     * <pre>
     *  b(0) + b(1)*x + b(2)*x^2 + ... + b(n)*x^n
     * </pre>
     *
     * @param beta the power series coefficients
     * @param x the independent variable of the power series
     * @param order the order of the power series
     * @return The value of the power series
     */
    protected double powerSeries(double[] beta, double x, int order) {
        double value = 0;
        for (int i = 0; i < order; i++) {
            value = value + beta[i]*pow(x, i);
        }
        return value;
    }
    
}
