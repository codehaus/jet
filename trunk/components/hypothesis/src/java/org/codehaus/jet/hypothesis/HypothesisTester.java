package org.codehaus.jet.hypothesis;

/**
 * HypothesisTester is a facade exposing the hypothesis test functionality 
 * 
 * @author Mauro Talevi
 */
public interface HypothesisTester {
    
    double estimateCriticalValue(String testName, int[] options, double level, int sampleSize)
            throws HypothesisException;

    double estimatePValue(String testName, int[] options, double level, int sampleSize) 
            throws HypothesisException;

    String[] getTestNames();

}