package org.codehaus.jet.hypothesis.rejection;

public interface ResponseSurfaceEvaluator {

    /**
     * @param beta
     * @param sampleSize
     * @param params 
     * @return
     */
    double evaluate(double[] beta, int sampleSize, int[] params);

}