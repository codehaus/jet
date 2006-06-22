package org.codehaus.jet.estimators;

/**
 * 
 * @author Mauro Talevi
 */
public interface StatisticValueEstimator {

    double estimateValue(double[] probs, double[] norm,
            double[] weights, double[][] beta, int sampleSize, int[] params, double level);

    double estimateValue(double[] probs, double[] norm, 
            double[] weights, double[] criticalValues, double level);

}