package org.codehaus.jet;

import org.codehaus.jet.estimators.StatisticValueEstimator;

public interface EstimatorProvider {

    StatisticValueEstimator getCriticalValueEstimator(String testName);

    StatisticValueEstimator getPValueEstimator(String testName);

}
