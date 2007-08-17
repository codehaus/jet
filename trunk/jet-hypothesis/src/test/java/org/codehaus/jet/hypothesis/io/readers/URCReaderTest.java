package org.codehaus.jet.hypothesis.io.readers;

import org.codehaus.jet.hypothesis.io.BetaReader;



public class URCReaderTest extends AbstractWeightReaderTestCase {

    private BetaReader reader = new URCReader("mackinnon1996");
    
    public void testBetaAndWeightsCanBeRead() throws Exception {        
        reader.read(new int[]{1, 0, 1});
        assertBeta(reader.getBeta(), 221, 0, -.38929681e+01, .34781086e+01);
        assertWeights(reader.getWeights(), 221, .33188192e-02, .31382626e-02);
        reader.read(new int[]{1, 1, 1});
        assertBeta(reader.getBeta(), 221, 0,  -.46498737e+01, .20018937e+01);
        assertBeta(reader.getBeta(), 221, 1,  -.19585128e+02, .65929082e+01);
        assertBeta(reader.getBeta(), 221, 2,  -.13404859e+03, .40856258e+02);
        assertWeights(reader.getWeights(), 221, .28987599e-02, .30589721e-02);
        reader.read(new int[]{2, 0, 1});
        assertBeta(reader.getBeta(), 221, 0, -.45836519e+01, .27115749e+01);
        assertWeights(reader.getWeights(), 221, .42871581e-02, .50910498e-02);
        reader.read(new int[]{1, 0, 2});
        assertBeta(reader.getBeta(), 221, 0,  -.30801328e+02,  .41878713e+01);
        assertWeights(reader.getWeights(), 221, .44569537e-01, .58185791e-02);
        reader.read(new int[]{2, 0, 2});
        assertBeta(reader.getBeta(), 221, 0,  -.42572121e+02,  .41292948e+01);
        assertWeights(reader.getWeights(), 221, .81241944e-01, .11234018e-01);
    }
    
    public void testInputParamsAreValidated() throws Exception {        
        try {
            reader.read(new int[] { 0, 0, 0 });
        } catch ( IllegalArgumentException e) {
            assertEquals("integrated variables must be an integer between 1 and 12", e.getMessage());
        }
        try {
            reader.read(new int[] { 13, 0, 0 });
        } catch ( IllegalArgumentException e) {
            assertEquals("integrated variables must be an integer between 1 and 12", e.getMessage());
        }
        try {
            reader.read(new int[] { 1, -1, 0 });
        } catch ( IllegalArgumentException e) {
            assertEquals("regression variables must be an integer between 0 and 3", e.getMessage());
        }
        try {
            reader.read(new int[] { 1, 4, 0 });
        } catch ( IllegalArgumentException e) {
            assertEquals("regression variables must be an integer between 0 and 3", e.getMessage());
        }
        try {
            reader.read(new int[] { 1, 0, 0 });
        } catch ( IllegalArgumentException e) {
            assertEquals("test type must be an integer between 1 and 2", e.getMessage());
        }
        try {
            reader.read(new int[] { 1, 0, 3 });
        } catch ( IllegalArgumentException e) {
            assertEquals("test type must be an integer between 1 and 2", e.getMessage());
        }
    }
    
    
}
