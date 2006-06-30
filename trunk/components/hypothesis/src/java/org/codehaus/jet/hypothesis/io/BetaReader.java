package org.codehaus.jet.hypothesis.io;


public interface BetaReader extends WeightReader {

    double[][] getBeta();

    int[] getParams();

}