package org.codehaus.jet.regression.estimators;

import static java.lang.Math.log;

import org.codehaus.jet.regression.GeneralLinearRegression;

/**
 * Estimator for the Akaike Information Criterion (AIC)
 * <pre>
 * AIC(p)= -2T[ln(sigma^2(p)]+2p
 * </pre>
 *
 * @see InformationCriterionEstimator
 * @author Mauro Talevi
 */
public class AkaikeInformationCriterionEstimator extends AbstractInformationCriterionEstimator {
  
    /** 
     *  Creates an AkaikeInformationCriterionEstimator with default regression estimator
     */
    public AkaikeInformationCriterionEstimator() {
        super();
    }

    /**
     * Creates an AkaikeInformationCriterionEstimator with given regression estimator
     * @param regression the GeneralLinearRegression
     */
    public AkaikeInformationCriterionEstimator(GeneralLinearRegression regression) {
        super(regression);
    }

    /**
     * Calculate AIC
     * <pre>
     * AIC(p)= -2T[ln(sigma^2(p)]+2p
     * </pre>
     * @param p the lag order
     * @return The AIC for the given lag order
     */
    public double estimateIC(int p) {
        int T = getSampleSize();
        double var = calculateYVariance(p);
        double value = -2*T*log(var)+2*p;
        return value;
    }


}
