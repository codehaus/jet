package org.codehaus.jet.hypothesis.rejection;


public interface RejectionValueEstimatorProvider {

    RejectionValueEstimator getCriticalValueEstimator(String testName);

    RejectionValueEstimator getPValueEstimator(String testName);

}
