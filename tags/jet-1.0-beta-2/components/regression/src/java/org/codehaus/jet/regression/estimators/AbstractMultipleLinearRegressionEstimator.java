package org.codehaus.jet.regression.estimators;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;
import org.codehaus.jet.regression.MultipleLinearRegressionEstimator;

/**
 * Abstract base class for implementations of MultipleLinearRegression
 * 
 * @author Mauro Talevi
 */
public abstract class AbstractMultipleLinearRegressionEstimator implements
        MultipleLinearRegressionEstimator {

    protected RealMatrix X;
    protected RealMatrix Y;

    /**
     * Adds y sample data
     * 
     * @param y the [n,1] array representing the y sample
     */
    protected void addYSampleData(double[] y){
        this.Y = new RealMatrixImpl(y);
    }

    /**
     * Adds x sample data
     * 
     * @param x the [n,p] array representing the x sample
     */
    protected void addXSampleData(double[][] x){
        this.X = new RealMatrixImpl(x);
    }

    public double[] estimateRegressionParameters(){
        RealMatrix b = calculateBeta();
        return b.getColumn(0);
    }    
    
    public double[] estimateResiduals(){
        RealMatrix b = calculateBeta();
        RealMatrix e = Y.subtract(X.multiply(b));
        return e.getColumn(0);
    }
    
    public double[][] estimateRegressionParametersVariance() {
        return calculateBetaVariance().getData();
    }

    public double estimateRegressandVariance() {
        return calculateYVariance();
    }
    
    /**
     * Calculates the beta of multiple linear regression in matrix notation
     */
    protected abstract RealMatrix calculateBeta();    
    
    /**
     * Calculates the beta variance of multiple linear regression in matrix notation
     */
    protected abstract RealMatrix calculateBetaVariance();
    
    /**
     * Calculates the Y variance of multiple linear regression 
     */
    protected abstract double calculateYVariance();

    /**
     * Calculates the residuals of multiple linear regression in matrix notation
     * <pre>
     * u = y - X*b
     * </pre>
     * 
     * @return The residuals [n,1] matrix 
     */
    protected RealMatrix calculateResiduals() {
        RealMatrix b = calculateBeta();
        return Y.subtract(X.multiply(b));
    }
    
}
