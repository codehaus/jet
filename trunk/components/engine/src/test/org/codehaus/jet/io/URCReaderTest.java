package org.codehaus.jet.io;



public class URCReaderTest extends AbstractWeightReaderTestCase {
    
    public void testBetaAndWeightsCanBeRead() throws Exception {        
        BetaReader reader = new URCReader("mackinnon1996");
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
    
}
