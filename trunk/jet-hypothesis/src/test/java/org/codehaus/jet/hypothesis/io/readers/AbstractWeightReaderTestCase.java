package org.codehaus.jet.hypothesis.io.readers;

import junit.framework.TestCase;

public abstract class AbstractWeightReaderTestCase extends TestCase {

    protected void assertBeta(double[][] beta, int length, int regression, double first, double last) {
        assertEquals(length, beta.length);
        assertEquals(first, beta[0][regression], 0.0001);
        assertEquals(last, beta[length-1][regression], 0.0001);
    }

    protected void assertCriticalValues(double[] values, int length, double first, double last) {
        assertEquals(length, values.length);
        assertEquals(first, values[0], 0.0001);
        assertEquals(last, values[length-1], 0.0001);
    }
    
    protected void assertWeights(double[] weights, int length, double first, double last) {
        assertEquals(length, weights.length);
        assertEquals(first, weights[0], 0.0001);
        assertEquals(last, weights[length-1], 0.0001);
    }

}
