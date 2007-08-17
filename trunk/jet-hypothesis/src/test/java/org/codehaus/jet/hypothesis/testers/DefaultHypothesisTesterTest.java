package org.codehaus.jet.hypothesis.testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.codehaus.jet.hypothesis.HypothesisException;
import org.codehaus.jet.hypothesis.HypothesisTest;
import org.codehaus.jet.hypothesis.HypothesisTester;
import org.junit.Test;

public class DefaultHypothesisTesterTest {

    private HypothesisTester tester = new DefaultHypothesisTester();       
    private double precision = 1e-6;

    @Test
    public void canEstimateCriticalValue() throws Exception {
        assertCriticalValue(-2.56503943, HypothesisTest.URC.getName(), 1, 0, 1, 0.01, 0);
        assertCriticalValue(-1.94084677, HypothesisTest.URC.getName(), 1, 0, 1, 0.05, 0);
        assertCriticalValue(-1.61675256, HypothesisTest.URC.getName(), 1, 0, 1, 0.1, 0);        
        assertCriticalValue(-5.26769030, HypothesisTest.URC.getName(), 4, 3, 1, 0.01, 0);        
        assertCriticalValue(-2.56549694, HypothesisTest.ECM.getName(), 1, 0, 1, 0.01, 0);        
        assertCriticalValue(-1.94077862, HypothesisTest.ECM.getName(), 1, 0, 1, 0.05, 0);
        assertCriticalValue(-1.61678256, HypothesisTest.ECM.getName(), 1, 0, 1, 0.1, 0);        
        assertCriticalValue(-5.03855256, HypothesisTest.ECM.getName(), 4, 3, 1, 0.01, 0);        
        assertCriticalValue(6.93989672, HypothesisTest.JOHANSEN.getName(), 1, 0, 1, 0.01, 0);        
        assertCriticalValue(4.13019335, HypothesisTest.JOHANSEN.getName(), 1, 0, 1, 0.05, 0);        
        assertCriticalValue(2.97607175, HypothesisTest.JOHANSEN.getName(), 1, 0, 1, 0.1, 0);        
        assertCriticalValue(33.73238820, HypothesisTest.JOHANSEN.getName(), 4, 3, 1, 0.01, 0);        
    }
    
    @Test
    public void canValidateCriticalValueParams() throws Exception {
        assertCriticalValueParamsInvalid(HypothesisTest.URC.getName(), 0, 0, 1, 0.01, 0);
        assertCriticalValueParamsInvalid(HypothesisTest.URC.getName(), 13, 0, 1, 0.01, 0);
        assertCriticalValueParamsInvalid(HypothesisTest.URC.getName(), 1, -1, 1, 0.01, 0);
        assertCriticalValueParamsInvalid(HypothesisTest.URC.getName(), 1, 4, 1, 0.01, 0);
        assertCriticalValueParamsInvalid(HypothesisTest.URC.getName(), 1, 0, 0, 0.01, 0);
        assertCriticalValueParamsInvalid(HypothesisTest.URC.getName(), 1, 0, 3, 0.01, 0);
        assertCriticalValueParamsInvalid(HypothesisTest.URC.getName(), 1, 0, 1, -0.01, 0);
        assertCriticalValueParamsInvalid(HypothesisTest.URC.getName(), 1, 0, 1, 1.01, 0);
        assertCriticalValueParamsInvalid(HypothesisTest.URC.getName(), 1, 0, 1, 0.01, -1);
    }

    @Test
    public void canEstimatePValue() throws Exception {
        assertPValue(0.68594692, HypothesisTest.URC.getName(), 1, 0, 1, 0.01, 0);
        assertPValue(0.69877782, HypothesisTest.URC.getName(), 1, 0, 1, 0.05, 0);
        assertPValue(0.71441539, HypothesisTest.URC.getName(), 1, 0, 1, 0.1, 0);
        assertPValue(0.99998863, HypothesisTest.URC.getName(), 4, 3, 1, 0.01, 0);        
        assertPValue(0.68593103, HypothesisTest.ECM.getName(), 1, 0, 1, 0.01, 0);
        assertPValue(0.69876740, HypothesisTest.ECM.getName(), 1, 0, 1, 0.05, 0);
        assertPValue(0.71442130, HypothesisTest.ECM.getName(), 1, 0, 1, 0.1, 0);
        assertPValue(0.99960596, HypothesisTest.ECM.getName(), 4, 3, 1, 0.01, 0);        
        assertPValue(0.93438108, HypothesisTest.JOHANSEN.getName(), 1, 0, 1, 0.01, 0);        
        assertPValue(0.85455867, HypothesisTest.JOHANSEN.getName(), 1, 0, 1, 0.05, 0);        
        assertPValue(0.79484622, HypothesisTest.JOHANSEN.getName(), 1, 0, 1, 0.1, 0);        
        assertPValue(1.00000000, HypothesisTest.JOHANSEN.getName(), 4, 3, 1, 0.01, 0);        
    }    

    @Test
    public void canValidatePValueParams() throws Exception {
        assertPValueParamsInvalid(HypothesisTest.URC.getName(), 0, 0, 1, 0.01, 0);
        assertPValueParamsInvalid(HypothesisTest.URC.getName(), 13, 0, 1, 0.01, 0);
        assertPValueParamsInvalid(HypothesisTest.URC.getName(), 1, -1, 1, 0.01, 0);
        assertPValueParamsInvalid(HypothesisTest.URC.getName(), 1, 4, 1, 0.01, 0);
        assertPValueParamsInvalid(HypothesisTest.URC.getName(), 1, 0, 0, 0.01, 0);
        assertPValueParamsInvalid(HypothesisTest.URC.getName(), 1, 0, 3, 0.01, 0);
        assertPValueParamsInvalid(HypothesisTest.URC.getName(), 1, 0, 1, -0.01, 0);
        assertPValueParamsInvalid(HypothesisTest.URC.getName(), 1, 0, 1, 1.01, 0);
        assertPValueParamsInvalid(HypothesisTest.URC.getName(), 1, 0, 1, 0.01, -1);
    }

    @Test
    public void testTestNamesCanBeRetrieved() {
        assertEquals(Arrays.toString(DefaultHypothesisTester.SUPPORTED_TEST_NAMES), Arrays.toString(tester.listTestNames()));
    }
    
    private void assertCriticalValue(double expected, String testName, int integratedVariables, int regressionVariables, 
            int testType, double level, int sampleSize) throws Exception{
        assertDouble(expected, tester.estimateCriticalValue(testName, new int[]{integratedVariables, regressionVariables,
                testType}, level, sampleSize));
    }

    private void assertPValue(double expected, String testName, int integratedVariables, int regressionVariables, 
            int testType, double level, int sampleSize) throws Exception{
        assertDouble(expected, tester.estimatePValue(testName, new int[]{integratedVariables, regressionVariables,
                testType}, level, sampleSize));
    }

    private boolean assertCriticalValueParamsInvalid(String testName, int integratedVariables, 
            int regressionVariables, int testType, double level, int sampleSize) {
        try {
            tester.estimateCriticalValue(testName, new int[]{integratedVariables, 
                    regressionVariables, testType}, level, sampleSize);
            fail("HypothesisException expected");
            return false;
        } catch ( HypothesisException e) {
           return e.getCause() instanceof IllegalArgumentException;
        }
    }

    private boolean assertPValueParamsInvalid(String testName, int integratedVariables, 
            int regressionVariables, int testType, double level, int sampleSize) {
        try {
            tester.estimatePValue(testName, new int[]{integratedVariables, 
                    regressionVariables, testType}, level, sampleSize);
            fail("HypothesisException expected");
            return false;
        } catch ( HypothesisException e) {
            return e.getCause() instanceof IllegalArgumentException;
        }
    }

    private void assertDouble(double expected, double actual) {
        assertEquals(expected, actual, precision);
    }


}
