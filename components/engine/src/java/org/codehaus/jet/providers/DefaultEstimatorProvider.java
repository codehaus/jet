package org.codehaus.jet.providers;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jet.EstimatorProvider;
import org.codehaus.jet.engines.DefaultJetEngine;
import org.codehaus.jet.estimators.CriticalValueEstimator;
import org.codehaus.jet.estimators.ECMResponseSurfaceEvaluator;
import org.codehaus.jet.estimators.PValueEstimator;
import org.codehaus.jet.estimators.StatisticValueEstimator;
import org.codehaus.jet.estimators.URCResponseSurfaceEvaluator;

/**
 * 
 * @author Mauro Talevi
 */
public class DefaultEstimatorProvider implements EstimatorProvider {
    
    private Map criticalValueEstimators = new HashMap();
    private Map pValueEstimators = new HashMap();
    
    public DefaultEstimatorProvider(){
        criticalValueEstimators.put(DefaultJetEngine.ECM_TEST, new CriticalValueEstimator(new ECMResponseSurfaceEvaluator(), 11, 2.0));
        criticalValueEstimators.put(DefaultJetEngine.URC_TEST, new CriticalValueEstimator(new URCResponseSurfaceEvaluator(), 9, 2.0));
        criticalValueEstimators.put(DefaultJetEngine.LRC_TEST, new CriticalValueEstimator(11, 2.0));
        criticalValueEstimators.put(DefaultJetEngine.JOHANSEN_TEST, new CriticalValueEstimator(11, 2.0));
        pValueEstimators.put(DefaultJetEngine.ECM_TEST, new PValueEstimator(new ECMResponseSurfaceEvaluator(), 11, 2.0));
        pValueEstimators.put(DefaultJetEngine.URC_TEST, new PValueEstimator(new URCResponseSurfaceEvaluator(), 9, 2.0));
        pValueEstimators.put(DefaultJetEngine.LRC_TEST, new PValueEstimator(11, 2.0));
        pValueEstimators.put(DefaultJetEngine.JOHANSEN_TEST, new PValueEstimator(11, 2.0));
    }

    public StatisticValueEstimator getCriticalValueEstimator(String testName) {
        StatisticValueEstimator estimator = (StatisticValueEstimator)criticalValueEstimators.get(testName);
        if ( estimator == null ) {
            throw new IllegalArgumentException("StatisticValueEstimator not found for test name "+testName);
        }
        return estimator;
    }

    public StatisticValueEstimator getPValueEstimator(String testName) {
        StatisticValueEstimator estimator = (StatisticValueEstimator)pValueEstimators.get(testName);
        if ( estimator == null ) {
            throw new IllegalArgumentException("StatisticValueEstimator not found for test name "+testName);
        }
        return estimator;
    }

}
