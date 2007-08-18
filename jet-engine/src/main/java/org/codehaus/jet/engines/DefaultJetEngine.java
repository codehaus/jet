package org.codehaus.jet.engines;

import org.codehaus.jet.JetEngine;
import org.codehaus.jet.hypothesis.HypothesisTester;
import org.codehaus.jet.hypothesis.rejection.RejectionValueType;
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

    public double estimateCriticalValue(String testName, int[] options, double level, int sampleSize) {
        return tester.estimateRejectionValue(RejectionValueType.CRITICAL, testName, options, level, sampleSize);
    }

    public double estimatePValue(String testName, int[] options, double level, int sampleSize) {
        return tester.estimateRejectionValue(RejectionValueType.P, testName, options, level, sampleSize);
    }

    public String[] listTestNames() {
        return tester.listTestNames();
    }

}
