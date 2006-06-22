package org.codehaus.jet.regression;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;

/**
 * Abstract base class for implementations of GeneralLinearRegression
 * 
 * @author Mauro Talevi
 */
public abstract class AbstractGeneralLinearRegression implements
        GeneralLinearRegression {

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
     * @param x the [n,p-1] array representing the x sample
     */
    protected void addXSampleData(double[][] x){
        this.X = toX(x);        
    }

    public double[] getBeta(){
        RealMatrix b = calculateBeta();
        return b.getColumn(0);
    }    
    
    public double[] getResiduals(){
        RealMatrix b = calculateBeta();
        RealMatrix e = Y.subtract(X.multiply(b));
        return e.getColumn(0);
    }
    
    public double[][] getBetaVariance() {
        return calculateBetaVariance().getData();
    }

    public double getYVariance() {
        return calculateYVariance();
    }
    
    /**
     * Calculates the beta of general linear regression in matrix notation
     */
    protected abstract RealMatrix calculateBeta();    
    
    /**
     * Calculates the beta variance of general linear regression in matrix notation
     */
    protected abstract RealMatrix calculateBetaVariance();
    
    /**
     * Calculates the Y variance of general linear regression 
     */
    protected abstract double calculateYVariance();

    /**
     * Calculates the residual of general linear regression in matrix notation
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

    /**
     * Creates the X matrix [n,p] = [[1][x]], where [x] is a [n,p-1] array
     * 
     * @param x the [n,p-1] array of values
     * @return A [n,p] RealMatrix 
     */
    protected RealMatrix toX(double[][] x) {
        int n = x.length;
        int p = x[0].length;
        double[][] data = new double[n][p];
        for (int i = 0; i < n; i++) {
            data[i][0] = 1.0d;
            for (int j = 0; j < p - 1; j++) {
                data[i][j+1] = x[i][j];
            }
        }        
        return new RealMatrixImpl(data);
    }

    protected void printMatrix(String name, RealMatrix m) {
        System.out.println(name);
        for (int i = 0; i < m.getRowDimension(); i++) {
            RealMatrix row = m.getRowMatrix(i);
            System.out.println(row);
        }
    }


}
