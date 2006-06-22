package org.codehaus.jet.estimators;

public interface ResponseSurfaceEvaluator {

    /**
     * @param beta
     * @param sampleSize
     * @param params 
     * @return
     */
    double evaluate(double[] beta, int sampleSize, int[] params);

}