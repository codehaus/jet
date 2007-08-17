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
    
    private Map<String, CriticalValueEstimator> criticalValueEstimators;
    private Map<String, PValueEstimator> pValueEstimators;
    
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
    public DefaultEstimatorProvider(Map<String, CriticalValueEstimator> criticalValueEstimators, 
                                    Map<String, PValueEstimator> pValueEstimators){
        this.criticalValueEstimators = criticalValueEstimators;
        this.pValueEstimators = pValueEstimators;
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
    
    private static Map<String, CriticalValueEstimator> createDefaultCriticalValueEstimators() {
        Map<String, CriticalValueEstimator> map = new HashMap<String, CriticalValueEstimator>();        
        map.put(HypothesisTest.ECM.getName(), new CriticalValueEstimator(new ECMResponseSurfaceEvaluator(), 11, 2.0));
        map.put(HypothesisTest.URC.getName(), new CriticalValueEstimator(new URCResponseSurfaceEvaluator(), 9, 2.0));
        map.put(HypothesisTest.LRC.getName(), new CriticalValueEstimator(11, 2.0));
        map.put(HypothesisTest.JOHANSEN.getName(), new CriticalValueEstimator(11, 2.0));
        return map;
    }

    private static Map<String, PValueEstimator> createDefaultPValueEstimators() {
        Map<String, PValueEstimator> map = new HashMap<String, PValueEstimator>();       
        map.put(HypothesisTest.ECM.getName(), new PValueEstimator(new ECMResponseSurfaceEvaluator(), 11, 2.0));
        map.put(HypothesisTest.URC.getName(), new PValueEstimator(new URCResponseSurfaceEvaluator(), 9, 2.0));
        map.put(HypothesisTest.LRC.getName(), new PValueEstimator(11, 2.0));
        map.put(HypothesisTest.JOHANSEN.getName(), new PValueEstimator(11, 2.0));
        return map;
    }

}
