package org.codehaus.jet.hypothesis.rejection;

/**
 * 
 * @author Mauro Talevi
 */
public interface RejectionValueEstimator {

    double estimateValue(double[] probs, double[] norm,
            double[] weights, double[][] beta, int sampleSize, int[] params, double level);

    double estimateValue(double[] probs, double[] norm, 
            double[] weights, double[] criticalValues, double level);

}