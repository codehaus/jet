package org.codehaus.jet.engines;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jet.JetEngine;
import org.codehaus.jet.JetException;

/**
 * Implementation of JetEngine which caches estimated values
 * 
 * @author Mauro Talevi
 */
public class CachingJetEngine implements JetEngine {

    private enum ValueType { CRITICAL, P };
    private static final String KEY = "{0}-{1}-{2}-{3}-{4}";
    private Map<String, Double> cache = new HashMap<String, Double>();
    private JetEngine delegate; 
    
    /**
     * Create CachingJetEngine with given delegate JetEngine
     * @param delegate the delegate JetEngine
     */
    public CachingJetEngine(JetEngine delegate) {
        this.delegate = delegate;
    }

    public double estimateCriticalValue(String testName, int[] options, double level, int sampleSize) {
        return value(ValueType.CRITICAL, testName, options, level, sampleSize);
    }

    public double estimatePValue(String testName, int[] options, double level, int sampleSize) throws JetException {
        return value(ValueType.P, testName, options, level, sampleSize);
    }

    public String[] listTestNames() {
        return delegate.listTestNames();
    }

    private double value(ValueType type, String testName, int[] options, double level, int sampleSize) {
        String key = MessageFormat.format(KEY, type, testName, Arrays.toString(options), level, sampleSize);
        if ( !cache.containsKey(key) ){
            double value = 0;
            switch (type) {
            case CRITICAL:
                value = delegate.estimateCriticalValue(testName, options, level, sampleSize);
                break;
            case P:
                value = delegate.estimatePValue(testName, options, level, sampleSize);
                break;
            default:
                throw new IllegalArgumentException("Invalid value type "+type);
            }
            cache.put(key, value);
        }
        return cache.get(key);
    }

}
