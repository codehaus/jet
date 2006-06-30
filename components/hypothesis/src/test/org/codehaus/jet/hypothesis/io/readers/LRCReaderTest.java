package org.codehaus.jet.hypothesis.io.readers;

import org.codehaus.jet.hypothesis.io.CriticalValueReader;


/**
 * @author Mauro Talevi
 */
public class LRCReaderTest extends AbstractWeightReaderTestCase {
    
    private CriticalValueReader reader = new LRCReader("mhm1999");

    public void testCriticalValuesAndWeightsCanBeRead() throws Exception {        
        reader.read(new int[]{1, 0, 0, 1});
        assertCriticalValues(reader.getCriticalValues(), 221, 1.54352E+01, 2.86249E-08);
        assertWeights(reader.getWeights(), 221, 5.64862E-02, 1.74981E-09);        
        reader.read(new int[]{1, 0, 1, 1});
        assertCriticalValues(reader.getCriticalValues(), 221, 2.16330E+01, 3.51260E-07);
        assertWeights(reader.getWeights(), 221, 6.34731E-02, 2.43344E-08);        
        reader.read(new int[]{2, 1, 0, 1});
        assertCriticalValues(reader.getCriticalValues(), 221, 30.3570, 0.8625);
        assertWeights(reader.getWeights(), 221, 0.071536, 0.004689);        
        reader.read(new int[]{2, 1, 0, 2});
        assertCriticalValues(reader.getCriticalValues(), 221, 35.2631, 1.0909);
        assertWeights(reader.getWeights(), 221, 0.072756, 0.005580);        
    }
         
    public void testInputParamsAreValidated() throws Exception {        
        try {
            reader.read(new int[] { 0, 0, 0, 1 });
        } catch ( IllegalArgumentException e) {
            assertEquals("restriction variables must be an integer between 1 and 12", e.getMessage());
        }
        try {
            reader.read(new int[] { 13, 0, 0, 1 });
        } catch ( IllegalArgumentException e) {
            assertEquals("restriction variables must be an integer between 1 and 12", e.getMessage());
        }
        try {
            reader.read(new int[] { 1, -1, 0, 1 });
        } catch ( IllegalArgumentException e) {
            assertEquals("exogenous variables must be an integer between 0 and 8", e.getMessage());
        }
        try {
            reader.read(new int[] { 1, 9, 0, 1 });
        } catch ( IllegalArgumentException e) {
            assertEquals("exogenous variables must be an integer between 0 and 8", e.getMessage());
        }
        try {
            reader.read(new int[] { 1, 0, -1, 1 });
        } catch ( IllegalArgumentException e) {
            assertEquals("regression variables must be an integer between 0 and 4", e.getMessage());
        }
        try {
            reader.read(new int[] { 1, 0, 5, 1 });
        } catch ( IllegalArgumentException e) {
            assertEquals("regression variables must be an integer between 0 and 4", e.getMessage());
        }
        try {
            reader.read(new int[] { 1, 0, 0, 0 });
        } catch ( IllegalArgumentException e) {
            assertEquals("test type must be an integer between 1 and 2", e.getMessage());
        }
        try {
            reader.read(new int[] { 1, 0, 0, 3 });
        } catch ( IllegalArgumentException e) {
            assertEquals("test type must be an integer between 1 and 2", e.getMessage());
        }
    }
}
