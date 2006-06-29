package org.codehaus.jet.regression.estimators;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;


/**
 * The GLS implementation of the general linear regression
 * 
 * GLS assumes a general covariance matrix Omega of the error
 * <pre>
 * u ~ N(0, Omega)
 * </pre>
 * 
 * Estimated by GLS, 
 * <pre>
 * b=(X' Omega^-1 X)^-1X'Omega^-1 y
 * </pre>
 * whose variance is
 * <pre>
 * Var(b)=(X' Omega^-1 X)^-1
 * </pre>
 * 
 * @author Mauro Talevi
 */
public class GLSGeneralLinearRegression extends AbstractGeneralLinearRegression {
    
    private RealMatrix Omega;
    

    public void addData(double[] y, double[][] x, double[][] covariance) {
        addYSampleData(y);
        addXSampleData(x);
        addCovarianceData(covariance);
    }

    /**
     * Add the covariance data
     * 
     * @param omega the [n,n] array representing the covariance
     */
    protected void addCovarianceData(double[][] omega){
        this.Omega = new RealMatrixImpl(omega);
    }
    
    /**
     * Calculates beta by GLS: 
     * <pre>
     *  b=(X' Omega^-1 X)^-1X'Omega^-1 y
     * </pre> 
     */
    protected RealMatrix calculateBeta() {
        RealMatrix OI = Omega.inverse();
        RealMatrix XT = X.transpose();
        RealMatrix XTOIX = XT.multiply(OI).multiply(X);
        return XTOIX.inverse().multiply(XT).multiply(OI).multiply(Y);
    }

    /**
     * Calculates the variance on the beta by GLS:
     * <pre>
     *  Var(b)=(X' Omega^-1 X)^-1
     * </pre>
     * @return The beta variance matrix
     */
    protected RealMatrix calculateBetaVariance() {
        RealMatrix XTOIX = X.transpose().multiply(Omega.inverse()).multiply(X);
        return XTOIX.inverse();
    }

    /**
     * Calculates the variance on the y by GLS:
     * <pre>
     *  Var(y)=Tr(u' Omega^-1 u)/(n-k)
     * </pre>
     * @return The Y variance
     */
    protected double calculateYVariance() {
        RealMatrix u = calculateResiduals();
        RealMatrix sse =  u.transpose().multiply(Omega.inverse()).multiply(u);
        return sse.getTrace()/(X.getRowDimension()-X.getColumnDimension());
    }
    
}
