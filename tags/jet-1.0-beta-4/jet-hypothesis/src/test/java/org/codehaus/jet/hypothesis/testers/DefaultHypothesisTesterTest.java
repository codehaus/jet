package org.codehaus.jet.hypothesis.testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.codehaus.jet.hypothesis.RejectionValueEstimateFailedException;
import org.codehaus.jet.hypothesis.HypothesisTest;
import org.codehaus.jet.hypothesis.HypothesisTester;
import org.codehaus.jet.hypothesis.rejection.RejectionValueType;
import org.junit.Test;

public class DefaultHypothesisTesterTest {

    private HypothesisTester tester = new DefaultHypothesisTester();       
    private double precision = 1e-6;

    @Test
    public void canEstimateCriticalValue() throws Exception {
        assertRejectionValue(-2.56503943, RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 1, 0, 1, 0.01, 0);
        assertRejectionValue(-1.94084677, RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 1, 0, 1, 0.05, 0);
        assertRejectionValue(-1.61675256, RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 1, 0, 1, 0.1, 0);        
        assertRejectionValue(-5.26769030, RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 4, 3, 1, 0.01, 0);        
        assertRejectionValue(-2.56549694, RejectionValueType.CRITICAL, HypothesisTest.ECM.getName(), 1, 0, 1, 0.01, 0);        
        assertRejectionValue(-1.94077862, RejectionValueType.CRITICAL, HypothesisTest.ECM.getName(), 1, 0, 1, 0.05, 0);
        assertRejectionValue(-1.61678256, RejectionValueType.CRITICAL, HypothesisTest.ECM.getName(), 1, 0, 1, 0.1, 0);        
        assertRejectionValue(-5.03855256, RejectionValueType.CRITICAL, HypothesisTest.ECM.getName(), 4, 3, 1, 0.01, 0);        
        assertRejectionValue(6.93989672, RejectionValueType.CRITICAL, HypothesisTest.JOHANSEN.getName(), 1, 0, 1, 0.01, 0);        
        assertRejectionValue(4.13019335, RejectionValueType.CRITICAL, HypothesisTest.JOHANSEN.getName(), 1, 0, 1, 0.05, 0);        
        assertRejectionValue(2.97607175, RejectionValueType.CRITICAL, HypothesisTest.JOHANSEN.getName(), 1, 0, 1, 0.1, 0);        
        assertRejectionValue(33.73238820, RejectionValueType.CRITICAL, HypothesisTest.JOHANSEN.getName(), 4, 3, 1, 0.01, 0);        
    }
    
    @Test
    public void canValidateCriticalValueParams() throws Exception {
        assertRejectionValueParamsInvalid(RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 0, 0, 1, 0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 13, 0, 1, 0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 1, -1, 1, 0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 1, 4, 1, 0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 1, 0, 0, 0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 1, 0, 3, 0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 1, 0, 1, -0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 1, 0, 1, 1.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.CRITICAL, HypothesisTest.URC.getName(), 1, 0, 1, 0.01, -1);
    }

    @Test
    public void canEstimatePValue() throws Exception {
        assertRejectionValue(0.68594692, RejectionValueType.P, HypothesisTest.URC.getName(), 1, 0, 1, 0.01, 0);
        assertRejectionValue(0.69877782, RejectionValueType.P, HypothesisTest.URC.getName(), 1, 0, 1, 0.05, 0);
        assertRejectionValue(0.71441539, RejectionValueType.P, HypothesisTest.URC.getName(), 1, 0, 1, 0.1, 0);
        assertRejectionValue(0.99998863, RejectionValueType.P, HypothesisTest.URC.getName(), 4, 3, 1, 0.01, 0);        
        assertRejectionValue(0.68593103, RejectionValueType.P, HypothesisTest.ECM.getName(), 1, 0, 1, 0.01, 0);
        assertRejectionValue(0.69876740, RejectionValueType.P, HypothesisTest.ECM.getName(), 1, 0, 1, 0.05, 0);
        assertRejectionValue(0.71442130, RejectionValueType.P, HypothesisTest.ECM.getName(), 1, 0, 1, 0.1, 0);
        assertRejectionValue(0.99960596, RejectionValueType.P, HypothesisTest.ECM.getName(), 4, 3, 1, 0.01, 0);        
        assertRejectionValue(0.93438108, RejectionValueType.P, HypothesisTest.JOHANSEN.getName(), 1, 0, 1, 0.01, 0);        
        assertRejectionValue(0.85455867, RejectionValueType.P, HypothesisTest.JOHANSEN.getName(), 1, 0, 1, 0.05, 0);        
        assertRejectionValue(0.79484622, RejectionValueType.P, HypothesisTest.JOHANSEN.getName(), 1, 0, 1, 0.1, 0);        
        assertRejectionValue(1.00000000, RejectionValueType.P, HypothesisTest.JOHANSEN.getName(), 4, 3, 1, 0.01, 0);        
    }    

    @Test
    public void canValidatePValueParams() throws Exception {
        assertRejectionValueParamsInvalid(RejectionValueType.P, HypothesisTest.URC.getName(), 0, 0, 1, 0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.P, HypothesisTest.URC.getName(), 13, 0, 1, 0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.P, HypothesisTest.URC.getName(), 1, -1, 1, 0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.P, HypothesisTest.URC.getName(), 1, 4, 1, 0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.P, HypothesisTest.URC.getName(), 1, 0, 0, 0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.P, HypothesisTest.URC.getName(), 1, 0, 3, 0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.P, HypothesisTest.URC.getName(), 1, 0, 1, -0.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.P, HypothesisTest.URC.getName(), 1, 0, 1, 1.01, 0);
        assertRejectionValueParamsInvalid(RejectionValueType.P, HypothesisTest.URC.getName(), 1, 0, 1, 0.01, -1);
    }

    @Test
    public void testTestNamesCanBeRetrieved() {
        assertEquals(Arrays.toString(DefaultHypothesisTester.SUPPORTED_TEST_NAMES), Arrays.toString(tester.listTestNames()));
    }
    
    private void assertRejectionValue(double expected, RejectionValueType type, String testName, int integratedVariables, 
            int regressionVariables, int testType, double level, int sampleSize) throws Exception{
        assertDouble(expected, tester.estimateRejectionValue(type, testName, new int[]{integratedVariables, regressionVariables,
                        testType}, level, sampleSize));
    }

    private boolean assertRejectionValueParamsInvalid(RejectionValueType type, String testName, 
            int integratedVariables, int regressionVariables, int testType, double level, int sampleSize) {
        try {
            tester.estimateRejectionValue(type, testName, new int[]{integratedVariables, 
                            regressionVariables, testType}, level, sampleSize);
            fail("RejectionValueEstimateFailedException expected");
            return false;
        } catch ( RejectionValueEstimateFailedException e) {
           return e.getCause() instanceof IllegalArgumentException;
        }
    }

    private void assertDouble(double expected, double actual) {
        assertEquals(expected, actual, precision);
    }

}
