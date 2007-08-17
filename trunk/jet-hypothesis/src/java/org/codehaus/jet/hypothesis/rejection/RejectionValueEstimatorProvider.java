package org.codehaus.jet.hypothesis.rejection;

/**
 * Provider of rejection value estimators for different tests.
 * 
 * @author Mauro Talevi
 *
 */
public interface RejectionValueEstimatorProvider {

    /**
     * Returns the CriticalValueEstimator for a given test
     * 
     * @param testName the test name
     * @return A RejectionValueEstimator
     */
    RejectionValueEstimator getCriticalValueEstimator(String testName);

    /**
     * Returns the PValueEstimator for a given test
     * 
     * @param testName the test name
     * @return A RejectionValueEstimator
     */
    RejectionValueEstimator getPValueEstimator(String testName);

}
