package org.codehaus.jet.hypothesis.rejection;

/**
 * <p>
 * Estimator of rejection values - critical values and p-values - for unit roots
 * and cointegration tests, as detailed in
 * <ul>
 * <li> <a href="http://qed.econ.queensu.ca/jae/1996-v11.6/mackinnon"> James G.
 * MacKinnon, "Numerical Distribution Functions for Unit Root and Cointegration
 * Tests", Journal of Applied Econometrics, Vol. 11, No. 6, 1996, 601-618 </a>
 * </li>
 * <li> <a href="http://qed.econ.queensu.ca/faculty/mackinnon/johtest">James G.
 * MacKinnon, Alfred Haug, and Leo Michelis, "Numerical distribution functions
 * of likelihood ratio tests for cointegration," Journal of Applied
 * Econometrics, 14, 1999, 563-577.</a> </li>
 * <li> <a href="http://qed.econ.queensu.ca/faculty/mackinnon/ecmtest"> Neil R.
 * Ericsson and James G. MacKinnon (2002) "Distributions of Error Correction
 * Tests for Cointegration", Econometrics Journal, 5, 2002, 285-318 </a> </li>
 * </ul>
 * </p>
 * 
 * @author Mauro Talevi
 */
public interface RejectionValueEstimator {

    /**
     * Estimates a rejection value
     * 
     * @param norms the normalisations used to construct the regressors matrix
     * @param probs the probabilities used to construct the covariance matrix
     * @param weights the weights sed to construct the covariance matrix
     * @param beta the beta values 
     * @param sampleSize the sample size
     * @param params the params specific to different tests
     * @param level the significance level
     * 
     * @return A rejection value
     */
    double estimateValue(double[] norms, double[] probs,
            double[] weights, double[][] beta, int sampleSize, int[] params, double level);

    /**
     * Estimates an asymptotic rejection value
     * 
     * @param norms the normalisations used to construct the regressors matrix
     * @param probs the probabilities used to construct the covariance matrix
     * @param weights the weights used to construct the covariance matrix
     * @param criticalValues the criticalvalues
     * @param level the significance level
     * 
     * @return A rejection value
     */
    double estimateAsymptoticValue(double[] norms, double[] probs, 
            double[] weights, double[] criticalValues, double level);

}