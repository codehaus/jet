package org.codehaus.jet.hypothesis;

import org.codehaus.jet.hypothesis.rejection.RejectionValueType;

/**
 * HypothesisTester is a facade exposing the hypothesis test functionality
 * 
 * @author Mauro Talevi
 */
public interface HypothesisTester {

    /**
     * Estimate rejection value for a given test
     * 
     * @param type the RejectionValueType
     * @param testName the name of the test
     * @param options the ints encoding the options
     * @param level the significance level
     * @param sampleSize the sample size
     * @return The rejection value
     * @throws RejectionValueEstimateFailedException when estimate fails
     */
    double estimateRejectionValue(RejectionValueType type, String testName, int[] options, double level, int sampleSize);

    /**
     * Returns the list of supported test names
     * 
     * @return The list of test names
     */
    String[] listTestNames();

}
