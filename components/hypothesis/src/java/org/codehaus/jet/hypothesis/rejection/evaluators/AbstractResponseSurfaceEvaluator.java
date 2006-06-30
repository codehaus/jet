package org.codehaus.jet.hypothesis.rejection.evaluators;

import org.codehaus.jet.hypothesis.rejection.ResponseSurfaceEvaluator;

/**
 * 
 * @author Mauro Talevi
 */
public abstract class AbstractResponseSurfaceEvaluator implements ResponseSurfaceEvaluator {

    protected double calculateX(int T, int nreg) {
        int d = T - nreg;
        if ( d <= 0 ) d = 1;
        return 1.0/d;
    }

    protected double calculateX(int sampleSize, int nreg, int nz) {
        int d = sampleSize - nreg - nz - 1;
        if ( d <= 0 ) d = 1;
        return 1.0/d;
    }

    protected double powerSeries(double[] beta, double x, int order) {
        double value = 0;
        for (int i = 0; i < order; i++) {
            value = value + beta[i]*Math.pow(x, i);
        }
        return value;
    }
    
}
