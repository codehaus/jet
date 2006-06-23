package org.codehaus.jet.regression;


/**
 * The general linear regression can be represented in matrix-notation as
 * <pre>
 *  y=X*beta+u
 * </pre>
 * where y is an <code>n-vector</code> <b>regressand</b>, X is a <code>[n,k]</code> matrix whose <code>k</code> columns are called
 * <b>regressors</b>, beta is <code>k-vector</code> of <b>regression parameters</b> and <code>u</code> is an <code>n-vector</code>
 * of <b>error terms</b>.
 * 
 * The notation is quite standard in literature, see eg <a href="http://www.econ.queensu.ca/ETM">Davidson and MacKinnon, Econometrics Theory and Methods, 2004</a>.
 * 
 * @author Mauro Talevi
 */
public interface GeneralLinearRegression {

    /**
     * Adds sample and covariance data
     * 
     * @param y the [n,1] array representing the y sample
     * @param x the [n,k-1] array representing x sample, to which a unity column is added to make a [n,k] matrix.
     * @param covariance the [n,n] array representing the covariance matrix 
     */
    void addData(double[] y, double[][] x, double[][] covariance);

    /**
     * Returns beta of general linear regression
     * 
     * @return The [k,1] array representing beta
     */
    double[] getBeta();

    /**
     * Returns beta variance of general linear regression
     * 
     * @return The [k,k] array representing the variance
     */
    double[][] getBetaVariance();
    
    /**
     * Returns residuals of general linear regression
     * 
     * @return The [n,1] array of residuals
     */
    double[] getResiduals();
    
    /**
     * Returns Y variance of general linear regression
     * 
     * @return The double representing the variance
     */
    double getYVariance();

}