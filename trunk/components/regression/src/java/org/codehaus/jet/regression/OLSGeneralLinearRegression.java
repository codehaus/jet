package org.codehaus.jet.regression;

import org.apache.commons.math.linear.RealMatrix;


/**
 * The OLS implementation of the general linear regression
 * 
 * OLS assumes the covariance matrix of the error to be diagonal and with equal variance.
 * <pre>
 * u ~ N(0, sigma^2*I)
 * </pre>
 * 
 * Estimated by OLS, 
 * <pre>
 * b=(X'X)^-1X'y
 * </pre>
 * whose variance is
 * <pre>
 * Var(b)=MSE*(X'X)^-1, MSE=u'u/(n-k)
 * </pre>
 * 
 * @author Mauro Talevi
 */
public class OLSGeneralLinearRegression extends AbstractGeneralLinearRegression {
    

    public void addData(double[] y, double[][] x, double[][] covariance) {
        addYSampleData(y);
        addXSampleData(x);
    }
    
    /**
     * Calculates beta by OLS: 
     * <pre>
     * b=(X'X)^-1X'y
     * </pre> 
     */
    protected RealMatrix calculateBeta() {
        RealMatrix XTX = X.transpose().multiply(X);
        return XTX.inverse().multiply(X.transpose()).multiply(Y);
    }

    /**
     * Calculates the variance on the beta by OLS:
     * <pre>
     *  Var(b)=(X'X)^-1
     * </pre>
     * @return The beta variance
     */
    protected RealMatrix calculateBetaVariance() {
        RealMatrix XTX = X.transpose().multiply(X);
        return XTX.inverse();
    }
    

    /**
     * Calculates the variance on the Y by OLS:
     * <pre>
     *  Var(y)=Tr(u'u)/(n-k)
     * </pre>
     * @return The Y variance
     */
    protected double calculateYVariance() {
        RealMatrix u = calculateResiduals();
        RealMatrix sse = u.transpose().multiply(u);
        return sse.getTrace()/(X.getRowDimension()-X.getColumnDimension());
    }

}
