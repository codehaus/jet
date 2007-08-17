package org.codehaus.jet.engines;

import static org.junit.Assert.assertTrue;

import org.codehaus.jet.JetEngine;
import org.codehaus.jet.hypothesis.testers.DefaultHypothesisTester;
import org.junit.Test;

/**
 * @author Mauro Talevi
 */
public class DefaultJetEngineTest {

    private JetEngine engine;
    
    @Test
    public void canEstimateValues() throws Exception {
        engine = new DefaultJetEngine();
        assertCriticalValue(DefaultHypothesisTester.URC_TEST, 1, 0, 1, 0.01, 0);
        assertPValue(DefaultHypothesisTester.URC_TEST, 1, 0, 1, 0.01, 0);
        assertTrue(engine.listTestNames().length > 0);
    }

    private void assertCriticalValue(String testName, int integratedVariables, int regressionVariables, int testType,
            double level, int sampleSize) throws Exception {
        assertTrue(engine.estimateCriticalValue(testName, new int[] { integratedVariables, regressionVariables,
                testType }, level, sampleSize) != 0.0);
    }

    private void assertPValue(String testName, int integratedVariables, int regressionVariables, int testType,
            double level, int sampleSize) throws Exception {
        assertTrue(engine.estimatePValue(testName, new int[] { integratedVariables, regressionVariables, testType },
                level, sampleSize) != 0.0);
    }

}
