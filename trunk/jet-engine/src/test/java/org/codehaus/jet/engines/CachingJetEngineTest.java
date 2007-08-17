package org.codehaus.jet.engines;

import static org.junit.Assert.assertTrue;

import org.codehaus.jet.JetEngine;
import org.codehaus.jet.JetException;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauro Talevi
 */
public class CachingJetEngineTest {

    private JetEngine delegate;
    private JetEngine engine;

    @Before
    public void setUp() {
        delegate = new DefaultJetEngine();
        engine = new CachingJetEngine(delegate);
    }

    @Test
    public void testCriticalValuesAreCached() throws Exception {
        long firstTime = timeToGetCriticalValue(new int[] { 2, 1, 1 }, 500, 0.01);
        long secondTime = timeToGetCriticalValue(new int[] { 2, 1, 1 }, 500, 0.01);
        if (secondTime == 0)
            secondTime = 1;
        assertTrue(firstTime / secondTime > 100);
    }

    private long timeToGetCriticalValue(int[] options, int sampleSize, double significanceLevel) throws JetException {
        long start = System.currentTimeMillis();
        engine.estimateCriticalValue("URC", options, significanceLevel, sampleSize);
        return System.currentTimeMillis() - start;
    }

    @Test
    public void testPValuesAreCached() throws Exception {
        long firstTime = timeToGetPValue(new int[] { 2, 1, 1 }, 500, 0.01);
        long secondTime = timeToGetPValue(new int[] { 2, 1, 1 }, 500, 0.01);
        if (secondTime == 0)
            secondTime = 1;
        assertTrue(firstTime / secondTime > 100);
    }

    private long timeToGetPValue(int[] options, int sampleSize, double significanceLevel) throws JetException {
        long start = System.currentTimeMillis();
        engine.estimatePValue("URC", options, significanceLevel, sampleSize);
        return System.currentTimeMillis() - start;
    }
  
}
