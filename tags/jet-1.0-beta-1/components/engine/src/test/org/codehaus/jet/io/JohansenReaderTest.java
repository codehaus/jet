package org.codehaus.jet.io;


/**
 * @author Mauro Talevi
 */
public class JohansenReaderTest extends AbstractWeightReaderTestCase {
    
    public void testCriticalValuesAndWeightsCanBeRead() throws Exception {        
        CriticalValueReader reader = new JohansenReader("mhm1999");
        reader.read(new int[]{1, 0, 1});
        assertCriticalValues(reader.getCriticalValues(), 221, .15481166E+02, .23480000E-07);
        assertWeights(reader.getWeights(), 221, .56106147E-01, .10000000E-05);        
        reader.read(new int[]{1, 1, 1});
        assertCriticalValues(reader.getCriticalValues(), 221, .15136705E+02, .15707963E-07);
        assertWeights(reader.getWeights(), 221, .10000000E-05, .10000000E-05);        
        reader.read(new int[]{2, 0, 1});
        assertCriticalValues(reader.getCriticalValues(), 221, .25498252E+02, .40303261E+00);
        assertWeights(reader.getWeights(), 221, .54059328E-01, .17219972E-02);        
        reader.read(new int[]{2, 0, 2});
        assertCriticalValues(reader.getCriticalValues(), 221, .27081888E+02, .46099498E+00);
        assertWeights(reader.getWeights(), 221, .57747879E-01, .21314414E-02);        
    }
    
}
