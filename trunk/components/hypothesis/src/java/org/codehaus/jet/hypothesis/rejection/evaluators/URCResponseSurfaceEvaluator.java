package org.codehaus.jet.hypothesis.rejection.evaluators;

/**
 * Implementations of the ResponseSurfaceEvaluator for URC tests.
 * 
 * @author Mauro Talevi
 */
public class URCResponseSurfaceEvaluator extends AbstractResponseSurfaceEvaluator {

    public double evaluate(double[] beta, int T, int[] params) {
        validateParams(beta, T, params);
        double value = 0;
        int model = params[0];
        int nreg = params[1];
        if (T == 0) {
            value = beta[0];
            return value;
        }
        switch (model) {
        case 2:
            value = powerSeries(beta, calculateX(T, 0), 3);
            break;
        case 3:
            value = powerSeries(beta, calculateX(T, 0), 4);
            break;
        case 4:
            value = powerSeries(beta, calculateX(T, nreg), 3);
            break;
        case 5:
            value = powerSeries(beta, calculateX(T, nreg), 4);
            break;
        }
        return value;
    }
    
    protected void validateParams(double[] beta, int T, int[] params) {
        if ( beta.length < 3 ){
            throw new IllegalArgumentException("beta must be have at least 3 coefficients");
        }
        if ( T < 0 ){
            throw new IllegalArgumentException("sample size must be a non-negative integer");
        }
        if ( params.length < 2 ){
            throw new IllegalArgumentException("params must contain at least 2 parameters");
        }
        if ( params[0] < 2 || params[0] > 5){
            throw new IllegalArgumentException("first parameter must be an integer between 2 and 5");
        }
    }    
}