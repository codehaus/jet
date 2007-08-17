package org.codehaus.jet.regression.estimators;

import static java.lang.Math.log;

import org.codehaus.jet.regression.MultipleLinearRegressionEstimator;

/**
 * Estimator for the Schwarz Information Criterion (SIC)
 * <pre>
 * SIC(p)= log(sigma^2(p)+[p*log(T)]/T
 * </pre>
 *
 * @see InformationCriterionEstimator
 * @author Mauro Talevi
 */
public class SchwarzInformationCriterionEstimator extends AbstractInformationCriterionEstimator {
  
    /** 
     *  Creates an SchwarzInformationCriterionEstimator with default regression estimator
     */
    public SchwarzInformationCriterionEstimator() {
        super();
    }

    /**
     * Creates an SchwarzInformationCriterionEstimator with given regression estimator
     * @param regression the MultipleLinearRegressionEstimator
     */
    public SchwarzInformationCriterionEstimator(MultipleLinearRegressionEstimator regression) {
        super(regression);
    }

    /**
     * Calculate SIC
     * <pre>
     * SIC(p)= log(sigma^2(p)+[p*log(T)]/T
     * </pre>
     * @param p the lag order
     * @param T the sample size
     * @param var the sample variance
     * @return The SIC value
     */
    protected double calculateIC(int p, int T, double var) {
        return log(var)+p*log(T)/T;
    }


}
