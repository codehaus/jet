package org.codehaus.jet.hypothesis.io.readers;

import org.codehaus.jet.hypothesis.io.BetaReader;


/**
 * @author Mauro Talevi
 */
public class ECMReaderTest extends AbstractWeightReaderTestCase {

    private BetaReader reader = new ECMReader("em2002");
    
    public void testBetaAndWeightsCanBeRead() throws Exception {        
        reader.read(new int[]{1, 0, 1});
        assertBeta(reader.getBeta(), 221, 0, -0.38836757E+01, 0.34765811E+01);
        assertWeights(reader.getWeights(), 221, 0.36431605E-02, 0.38811112E-02);
        reader.read(new int[]{1, 1, 1});
        assertBeta(reader.getBeta(), 221, 0, -0.46493748E+01, 0.19960864E+01);
        assertBeta(reader.getBeta(), 221, 1, -0.19828468E+02, 0.75717769E+01);
        assertBeta(reader.getBeta(), 221, 2, -0.69899802E-01, 0.69470194E-02);
        assertWeights(reader.getWeights(), 221, 0.33865493E-02, 0.33838147E-02);
        reader.read(new int[]{2, 0, 1});
        assertBeta(reader.getBeta(), 221, 0, -0.44831649E+01,  0.32339868E+01);
        assertWeights(reader.getWeights(), 221, 0.33755149E-02, 0.40881553E-02);
        
    }
    
    public void testInputParamsAreValidated() throws Exception {        
        try {
            reader.read(new int[] { 0, 0 });
        } catch ( IllegalArgumentException e) {
            assertEquals("integrated variables must be an integer between 1 and 12", e.getMessage());
        }
        try {
            reader.read(new int[] { 13, 0 });
        } catch ( IllegalArgumentException e) {
            assertEquals("integrated variables must be an integer between 1 and 12", e.getMessage());
        }
        try {
            reader.read(new int[] { 1, -1 });
        } catch ( IllegalArgumentException e) {
            assertEquals("regression variables must be an integer between 0 and 3", e.getMessage());
        }
        try {
            reader.read(new int[] { 1, 4 });
        } catch ( IllegalArgumentException e) {
            assertEquals("regression variables must be an integer between 0 and 3", e.getMessage());
        }
    }
    
    
}
