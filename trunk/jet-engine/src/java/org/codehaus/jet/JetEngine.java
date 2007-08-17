package org.codehaus.jet;

/**
 * JetEngine is a facade exposing the functionality relevant to the user
 * 
 * @author Mauro Talevi
 */
public interface JetEngine {
    
    double estimateCriticalValue(String testName, int[] options, double level, int sampleSize)
            throws JetException;

    double estimatePValue(String testName, int[] options, double level, int sampleSize) 
            throws JetException;

    String[] listTestNames();

}