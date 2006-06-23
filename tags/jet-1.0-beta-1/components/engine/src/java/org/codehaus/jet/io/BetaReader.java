package org.codehaus.jet.io;


public interface BetaReader extends WeightReader {

    double[][] getBeta();

    int[] getParams();

}