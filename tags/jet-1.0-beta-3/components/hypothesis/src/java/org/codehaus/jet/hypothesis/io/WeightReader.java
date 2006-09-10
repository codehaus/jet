package org.codehaus.jet.hypothesis.io;

import java.io.IOException;

/**
 * Reader of weights required to construct the covariance matrix of the GLS regression
 * 
 * @author Mauro Talevi
 */
public interface WeightReader {

    void read(int[] options) throws IOException;
    
    double[] getWeights();

}