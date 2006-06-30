package org.codehaus.jet.hypothesis.rejection.estimators;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jet.hypothesis.rejection.RejectionValueEstimator;
import org.codehaus.jet.hypothesis.rejection.RejectionValueEstimatorProvider;
import org.codehaus.jet.hypothesis.rejection.evaluators.ECMResponseSurfaceEvaluator;
import org.codehaus.jet.hypothesis.rejection.evaluators.URCResponseSurfaceEvaluator;
import org.codehaus.jet.hypothesis.testers.DefaultHypothesisTester;

/**
 * 
 * @author Mauro Talevi
 */
public class DefaultEstimatorProvider implements RejectionValueEstimatorProvider {
    
    private Map criticalValueEstimators = new HashMap();
    private Map pValueEstimators = new HashMap();
    
    public DefaultEstimatorProvider(){
        criticalValueEstimators.put(DefaultHypothesisTester.ECM_TEST, new CriticalValueEstimator(new ECMResponseSurfaceEvaluator(), 11, 2.0));
        criticalValueEstimators.put(DefaultHypothesisTester.URC_TEST, new CriticalValueEstimator(new URCResponseSurfaceEvaluator(), 9, 2.0));
        criticalValueEstimators.put(DefaultHypothesisTester.LRC_TEST, new CriticalValueEstimator(11, 2.0));
        criticalValueEstimators.put(DefaultHypothesisTester.JOHANSEN_TEST, new CriticalValueEstimator(11, 2.0));
        pValueEstimators.put(DefaultHypothesisTester.ECM_TEST, new PValueEstimator(new ECMResponseSurfaceEvaluator(), 11, 2.0));
        pValueEstimators.put(DefaultHypothesisTester.URC_TEST, new PValueEstimator(new URCResponseSurfaceEvaluator(), 9, 2.0));
        pValueEstimators.put(DefaultHypothesisTester.LRC_TEST, new PValueEstimator(11, 2.0));
        pValueEstimators.put(DefaultHypothesisTester.JOHANSEN_TEST, new PValueEstimator(11, 2.0));
    }

    public RejectionValueEstimator getCriticalValueEstimator(String testName) {
        RejectionValueEstimator estimator = (RejectionValueEstimator)criticalValueEstimators.get(testName);
        if ( estimator == null ) {
            throw new IllegalArgumentException("RejectionValueEstimator not found for test name "+testName);
        }
        return estimator;
    }

    public RejectionValueEstimator getPValueEstimator(String testName) {
        RejectionValueEstimator estimator = (RejectionValueEstimator)pValueEstimators.get(testName);
        if ( estimator == null ) {
            throw new IllegalArgumentException("RejectionValueEstimator not found for test name "+testName);
        }
        return estimator;
    }

}
