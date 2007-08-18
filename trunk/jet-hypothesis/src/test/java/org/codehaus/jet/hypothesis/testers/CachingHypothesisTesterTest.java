package org.codehaus.jet.hypothesis.testers;

import static org.junit.Assert.assertTrue;

import org.codehaus.jet.JetException;
import org.codehaus.jet.hypothesis.HypothesisTester;
import org.codehaus.jet.hypothesis.rejection.RejectionValueType;
import org.junit.Test;

/**
 * @author Mauro Talevi
 */
public class CachingHypothesisTesterTest {

    private HypothesisTester delegate = new DefaultHypothesisTester();
    private HypothesisTester tester = new CachingHypothesisTester(delegate);

    @Test
    public void canCacheCriticalValues() throws Exception {
        long firstTime = timeToEstimateRejectionValue(RejectionValueType.CRITICAL, new int[] { 2, 1, 1 }, 500, 0.01);
        long secondTime = timeToEstimateRejectionValue(RejectionValueType.CRITICAL, new int[] { 2, 1, 1 }, 500, 0.01);
        if (secondTime == 0)
            secondTime = 1;
        assertTrue(firstTime / secondTime > 100);
    }

    @Test
    public void canCachePValues() throws Exception {
        long firstTime = timeToEstimateRejectionValue(RejectionValueType.P, new int[] { 2, 1, 1 }, 500, 0.01);
        long secondTime = timeToEstimateRejectionValue(RejectionValueType.P, new int[] { 2, 1, 1 }, 500, 0.01);
        if (secondTime == 0)
            secondTime = 1;
        assertTrue(firstTime / secondTime > 100);
    }

    private long timeToEstimateRejectionValue(RejectionValueType type, int[] options, int sampleSize, double significanceLevel) throws JetException {
        long start = System.currentTimeMillis();
        tester.estimateRejectionValue(type, "URC", options, significanceLevel, sampleSize);
        return System.currentTimeMillis() - start;
    }
  
}
