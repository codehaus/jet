package org.codehaus.jet.io;

import java.io.IOException;

public interface WeightReader {

    void read(int[] options) throws IOException;
    
    double[] getWeights();

}