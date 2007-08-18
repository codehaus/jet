package org.codehaus.jet.hypothesis.rejection;

/**
 * Provider of rejection value estimators for different tests.
 * 
 * @author Mauro Talevi
 */
public interface RejectionValueEstimatorProvider {

    /**
     * Returns the estimator of a given type for a given test
     * 
     * @param type the rejection value type
     * @param testName the test name
     * @return A RejectionValueEstimator
     */
    RejectionValueEstimator getEstimator(RejectionValueType type, String testName);

}
