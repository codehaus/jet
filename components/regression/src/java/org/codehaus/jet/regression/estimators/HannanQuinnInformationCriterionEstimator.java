package org.codehaus.jet.regression.estimators;

import static java.lang.Math.log;

import org.codehaus.jet.regression.MultipleLinearRegressionEstimator;

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
        this(createDefaultRegressionEstimator());
    }

    /**
     * Creates an HannanQuinnInformationCriterionEstimator with given regression estimator
     * @param regression the MultipleLinearRegressionEstimator
     */
    public HannanQuinnInformationCriterionEstimator(MultipleLinearRegressionEstimator regression) {
        super(regression);
    }

    /**
     * Calculate HQIC
     * <pre>
     * HQIC(p)= log(sigma^2(p)+ 2p*log(log(T))/T
     * </pre>
     * @param p the lag order
     * @param T the sample size
     * @param var the sample variance
     * @return The HQIC value
     */
    protected double calculateIC(int p, int T, double var) {
        return log(var)+2*p*log(log(T))/T;
    }


}
