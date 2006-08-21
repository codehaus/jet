package org.codehaus.jet.regression.estimators;

import static java.lang.Math.log;

import org.codehaus.jet.regression.MultipleLinearRegressionEstimator;

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
        this(createDefaultRegressionEstimator());
    }

    /**
     * Creates an AkaikeInformationCriterionEstimator with given regression estimator
     * @param regression the GeneralLinearRegression
     */
    public AkaikeInformationCriterionEstimator(MultipleLinearRegressionEstimator regression) {
        super(regression);
    }

    /**
     * Calculate AIC
     * <pre>
     * AIC(p)= -2T[ln(sigma^2(p)]+2p
     * </pre>
     * @param p the lag order
     * @param T the sample size
     * @param var the sample variance
     * @return The AIC value
     */
    protected double calculateIC(int p, int T, double var) {
        return -2*T*log(var)+2*p;
    }


}
