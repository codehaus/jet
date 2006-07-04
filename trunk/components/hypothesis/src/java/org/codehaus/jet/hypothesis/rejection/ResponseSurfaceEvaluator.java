package org.codehaus.jet.hypothesis.rejection;

/**
 * Evaluator of response surface of the power series form
 * <pre>
 * q(T)=b(0) + b(1)/T + b(2)/T^2 + b(3)/T^3 + e
 * </pre>
 * where <code>T</code> is the sample size and <code>b(i), i=0,...,3</code> are the coefficients of the
 * power series.
 * 
 * @author Mauro Talevi
 */
public interface ResponseSurfaceEvaluator {

    /**
     * Evaluates the response surface 
     * 
     * @param beta the array of the power series coefficients
     * @param T the sample size
     * @param params the parameters required by specific implementations
     * @return The evaluated surface value
     */
    double evaluate(double[] beta, int T, int[] params);

}