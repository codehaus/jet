package org.codehaus.jet.hypothesis.rejection.estimators;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jet.hypothesis.HypothesisTest;
import org.codehaus.jet.hypothesis.rejection.RejectionValueEstimator;
import org.codehaus.jet.hypothesis.rejection.RejectionValueEstimatorProvider;
import org.codehaus.jet.hypothesis.rejection.evaluators.ECMResponseSurfaceEvaluator;
import org.codehaus.jet.hypothesis.rejection.evaluators.URCResponseSurfaceEvaluator;

/**
 * Default implementation of RejectionValueEstimatorProvider. 
 * Uses simple caching via Maps.
 * 
 * @author Mauro Talevi
 */
public class DefaultEstimatorProvider implements RejectionValueEstimatorProvider {
    
    private Map<String, RejectionValueEstimator> criticalValueEstimators;
    private Map<String, RejectionValueEstimator> pValueEstimators;
    
    /**
     * Creates a DefaultEstimatorProvider with default estimator caches
     */
    public DefaultEstimatorProvider(){
        this(createDefaultCriticalValueEstimators(), createDefaultPValueEstimators());
    }

    /**
     * Creates a DefaultEstimatorProvider with given estimator caches
     * 
     * @param criticalValueEstimators the cache of CriticalValuesEstimators
     * @param pValueEstimators the cache of PValueEstimators
     */
    public DefaultEstimatorProvider(Map<String, RejectionValueEstimator> criticalValueEstimators, 
                                    Map<String, RejectionValueEstimator> pValueEstimators){
        this.criticalValueEstimators = criticalValueEstimators;
        this.pValueEstimators = pValueEstimators;
    }
    
    public RejectionValueEstimator getCriticalValueEstimator(String testName) {
        RejectionValueEstimator estimator = criticalValueEstimators.get(testName);
        if ( estimator == null ) {
            throw new IllegalArgumentException("RejectionValueEstimator not found for test name "+testName);
        }
        return estimator;
    }

    public RejectionValueEstimator getPValueEstimator(String testName) {
        RejectionValueEstimator estimator = pValueEstimators.get(testName);
        if ( estimator == null ) {
            throw new IllegalArgumentException("RejectionValueEstimator not found for test name "+testName);
        }
        return estimator;
    }
    
    private static Map<String, RejectionValueEstimator> createDefaultCriticalValueEstimators() {
        Map<String, RejectionValueEstimator> map = new HashMap<String, RejectionValueEstimator>();        
        map.put(HypothesisTest.ECM.getName(), new CriticalValueEstimator(new ECMResponseSurfaceEvaluator(), 11, 2.0));
        map.put(HypothesisTest.URC.getName(), new CriticalValueEstimator(new URCResponseSurfaceEvaluator(), 9, 2.0));
        map.put(HypothesisTest.LRC.getName(), new CriticalValueEstimator(11, 2.0));
        map.put(HypothesisTest.JOHANSEN.getName(), new CriticalValueEstimator(11, 2.0));
        return map;
    }

    private static Map<String, RejectionValueEstimator> createDefaultPValueEstimators() {
        Map<String, RejectionValueEstimator> map = new HashMap<String, RejectionValueEstimator>();       
        map.put(HypothesisTest.ECM.getName(), new PValueEstimator(new ECMResponseSurfaceEvaluator(), 11, 2.0));
        map.put(HypothesisTest.URC.getName(), new PValueEstimator(new URCResponseSurfaceEvaluator(), 9, 2.0));
        map.put(HypothesisTest.LRC.getName(), new PValueEstimator(11, 2.0));
        map.put(HypothesisTest.JOHANSEN.getName(), new PValueEstimator(11, 2.0));
        return map;
    }

}
