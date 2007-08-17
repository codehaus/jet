package org.codehaus.jet.hypothesis.io.readers;

import static org.junit.Assert.assertEquals;

import org.codehaus.jet.hypothesis.io.ProbabilityReader;
import org.junit.Test;


public class ProbabilityReaderTest {
    
    @Test
    public void canReadProbabilities() throws Exception {        
        ProbabilityReader reader = new ProbabilityReader();
        reader.read();
        assertValues(reader.getProbs(), 221, 0.0001, 0.9999);
        assertValues(reader.getNorms(), 221, -3.71901649, 3.71901649);
    }
 
    private void assertValues(double[] values, int length, double first, double last) {
        assertEquals(length, values.length);
        assertEquals(first, values[0], 0.0001);
        assertEquals(last, values[length-1], 0.0001);
    }
    
}
