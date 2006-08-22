package org.codehaus.jet.regression;


/**
 * The multiple linear regression can be represented in matrix-notation as
 * <pre>
 *  y=X*b+u
 * </pre>
 * where y is an <code>n-vector</code> <b>regressand</b>, X is a <code>[n,k]</code> matrix whose <code>k</code> columns are called
 * <b>regressors</b>, b is <code>k-vector</code> of <b>regression parameters</b> and <code>u</code> is an <code>n-vector</code>
 * of <b>error terms</b> or <b>residuals</b>.
 * 
 * The notation is quite standard in literature, 
 * cf eg <a href="http://www.econ.queensu.ca/ETM">Davidson and MacKinnon, Econometrics Theory and Methods, 2004</a>.
 * 
 * @author Mauro Talevi
 */
public interface MultipleLinearRegressionEstimator {

    /**
     * Adds sample and covariance data
     * 
     * @param y the [n,1] array representing the y sample
     * @param x the [n,k-1] array representing x sample, to which a unity column is added to make a [n,k] matrix.
     * @param covariance the [n,n] array representing the covariance matrix 
     */
    void addData(double[] y, double[][] x, double[][] covariance);

    /**
     * Estimates the regression parameters b
     * 
     * @return The [k,1] array representing b
     */
    double[] estimateRegressionParameters();

    /**
     * Estimates the variance of the regression parameters, ie Var(b)
     * 
     * @return The [k,k] array representing the variance of b
     */
    double[][] estimateRegressionParametersVariance();
    
    /**
     * Estimates the residuals, ie u = y - X*b
     * 
     * @return The [n,1] array representing the residuals
     */
    double[] estimateResiduals();
    
    /**
     * Returns the variance of the regressand, ie Var(y)
     * 
     * @return The double representing the variance of y
     */
    double estimateRegressandVariance();

}