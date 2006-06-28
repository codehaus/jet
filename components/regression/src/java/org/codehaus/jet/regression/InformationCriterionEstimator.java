package org.codehaus.jet.regression;

/**
 * <p>
 * An autoregressive (AR) process can be represented as
 * <pre>
 *  y(t)=a_1*y(t-1)+...+a_p*y(t-p)+e_t, t=1,...,T
 * </pre>
 * where <code>a_i, i=,1,...,p</code> are autoregressive parameters,<code>e_t ~ N(0, sigma^2)</code>,
 * and <code>p</code> is the <b>lag order</b>.
 * </p>
 * 
 * <p>
 * The AR lag order can selected so that it minimises certain <b>Information Criterion</b> (IC), such as 
 * <ul>
 *  <li>AIC: Akike IC</li>
 *  <li>SIC: Schwarz IC</li>
 *  <li>HQIC: Hannan-Quinn IC</li>
 * </ul> 
 * </p>
 * <p>
 * For notation and IC formulae see V. Khim-Sen Liew,
 * <a href="http://www.economicsbulletin.com/2004/volume3/EB-04C20021A.pdf">Economics Bulletin (2004), Vol. 3, 33, 1-9</a>.
 * </p>
 * @author Mauro Talevi
 */
public interface InformationCriterionEstimator {

    /**
     * Adds sample data
     * 
     * @param y the array of size T representing the y sample
     */
    void addData(double[] y);
    
    /**
     * Estimates the IC value for a given lag order
     * 
     * @param lag the lag order used
     * @return The value of the IC 
     */
    double estimateIC(int lag);

    /**
     * Minimise the IC value for a given lag order interval
     * 
     * @param minLag the minimum lag order used
     * @param maxLag the maximum lag order used
     * @return The lag order that minimises the IC 
     */
    int minimiseIC(int minLag, int maxLag);
    
}
