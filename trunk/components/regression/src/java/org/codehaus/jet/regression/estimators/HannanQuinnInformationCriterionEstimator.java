package org.codehaus.jet.regression.estimators;

import static java.lang.Math.log;

import org.codehaus.jet.regression.GeneralLinearRegression;

/**
 * Estimator for the Hannan-Quinn Information Criterion (HQIC)
 * <pre>
 * HQIC(p)= log(sigma^2(p)+ 2p*log(log(T))/T
 * </pre>
 *
 * @see InformationCriterionEstimator
 * @author Mauro Talevi
 */
public class HannanQuinnInformationCriterionEstimator extends AbstractInformationCriterionEstimator {
  
    /** 
     *  Creates an HannanQuinnInformationCriterionEstimator with default regression estimator
     */
    public HannanQuinnInformationCriterionEstimator() {
        super();
    }

    /**
     * Creates an HannanQuinnInformationCriterionEstimator with given regression estimator
     * @param regression the GeneralLinearRegression
     */
    public HannanQuinnInformationCriterionEstimator(GeneralLinearRegression regression) {
        super(regression);
    }

    /**
     * Calculate HQIC
     * <pre>
     * HQIC(p)= log(sigma^2(p)+ 2p*log(log(T))/T
     * </pre>
     * @param p the lag order
     * @return The HQIC for the given lag order
     */
    public double estimateIC(int p) {
        int T = getSampleSize();
        double var = calculateYVariance(p);
        double value = log(var)+2*p*log(log(T))/T;
        return value;
    }


}
