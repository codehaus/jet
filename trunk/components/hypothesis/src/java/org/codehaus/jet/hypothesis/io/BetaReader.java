package org.codehaus.jet.hypothesis.io;

/**
 * Reader of values required to estimate rejection values
 * 
 * @author Mauro Talevi
 */
public interface BetaReader extends WeightReader {

    double[][] getBeta();

    int[] getParams();

}