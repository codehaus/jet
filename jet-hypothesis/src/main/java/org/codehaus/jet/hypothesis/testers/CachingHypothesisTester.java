package org.codehaus.jet.hypothesis.testers;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jet.hypothesis.HypothesisTester;
import org.codehaus.jet.hypothesis.rejection.RejectionValueType;

/**
 * Default implementation of HypothesisTester
 * 
 * @author Mauro Talevi
 */
public class CachingHypothesisTester implements HypothesisTester {

    private static final String KEY = "{0}-{1}-{2}-{3}-{4}";
    private Map<String, Double> cache = new HashMap<String, Double>();
    private HypothesisTester delegate; 
    
    public CachingHypothesisTester(HypothesisTester delegate) {
        this.delegate = delegate;
    }
    
    public double estimateRejectionValue(RejectionValueType type, String testName, int[] options, double level, int sampleSize) {
        return cachedValue(type, testName, options, level, sampleSize);
    }

    private double cachedValue(RejectionValueType type, String testName, int[] options, double level, int sampleSize) {
        String key = MessageFormat.format(KEY, type, testName, Arrays.toString(options), level, sampleSize);
        if ( !cache.containsKey(key) ){
            double value = delegate.estimateRejectionValue(type, testName, options, level, sampleSize);
            cache.put(key, value);
        }
        return cache.get(key);
    }

    public String[] listTestNames() {
        return delegate.listTestNames();
    }


}
