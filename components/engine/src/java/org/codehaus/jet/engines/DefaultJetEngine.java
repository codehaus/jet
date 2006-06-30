package org.codehaus.jet.engines;

import org.codehaus.jet.JetEngine;
import org.codehaus.jet.JetException;
import org.codehaus.jet.hypothesis.HypothesisException;
import org.codehaus.jet.hypothesis.HypothesisTester;
import org.codehaus.jet.hypothesis.testers.DefaultHypothesisTester;

/**
 * Default implementation of JetEngine
 * 
 * @author Mauro Talevi
 */
public class DefaultJetEngine implements JetEngine {

    private HypothesisTester tester; 
    
    /**
     * Create DefaultJetEngine with default hypothesis tester
     */
    public DefaultJetEngine() {
        this(new DefaultHypothesisTester());
    }

    /**
     * Create DefaultJetEngine with given hypothesis tester
     * @param tester the HypothesisTester
     */
    public DefaultJetEngine(HypothesisTester tester) {
        this.tester = tester;
    }

    public double estimateCriticalValue(String testName, int[] options, double level, int sampleSize) throws JetException {
        try {
            return tester.estimateCriticalValue(testName, options, level, sampleSize);
        } catch ( HypothesisException e) {
            throw new JetException("Failed to estimate critical value", e);
        }
    }

    public double estimatePValue(String testName, int[] options, double level, int sampleSize) throws JetException {
        try {
            return tester.estimatePValue(testName, options, level, sampleSize);
        } catch ( HypothesisException e) {
            throw new JetException("Failed to estimate P value", e);
        }
    }

    public String[] getTestNames() {
        return tester.getTestNames();
    }

}
