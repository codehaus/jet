package org.codehaus.jet.hypothesis.testers;

import java.util.Arrays;

import junit.framework.TestCase;

import org.codehaus.jet.hypothesis.HypothesisException;
import org.codehaus.jet.hypothesis.HypothesisTester;

public class DefaultHypothesisTesterTest extends TestCase {

    private HypothesisTester tester;
    private double precision = 1e-6;
    
    public void setUp(){
        tester = new DefaultHypothesisTester();        
    }
    
    public void testCriticalValueCanBeEstimated() throws Exception {
        assertCriticalValue(-2.56503943, DefaultHypothesisTester.URC_TEST, 1, 0, 1, 0.01, 0);
        assertCriticalValue(-1.94084677, DefaultHypothesisTester.URC_TEST, 1, 0, 1, 0.05, 0);
        assertCriticalValue(-1.61675256, DefaultHypothesisTester.URC_TEST, 1, 0, 1, 0.1, 0);        
        assertCriticalValue(-5.26769030, DefaultHypothesisTester.URC_TEST, 4, 3, 1, 0.01, 0);        
        assertCriticalValue(-2.56549694, DefaultHypothesisTester.ECM_TEST, 1, 0, 1, 0.01, 0);        
        assertCriticalValue(-1.94077862, DefaultHypothesisTester.ECM_TEST, 1, 0, 1, 0.05, 0);
        assertCriticalValue(-1.61678256, DefaultHypothesisTester.ECM_TEST, 1, 0, 1, 0.1, 0);        
        assertCriticalValue(-5.03855256, DefaultHypothesisTester.ECM_TEST, 4, 3, 1, 0.01, 0);        
        assertCriticalValue(6.93989672, DefaultHypothesisTester.JOHANSEN_TEST, 1, 0, 1, 0.01, 0);        
        assertCriticalValue(4.13019335, DefaultHypothesisTester.JOHANSEN_TEST, 1, 0, 1, 0.05, 0);        
        assertCriticalValue(2.97607175, DefaultHypothesisTester.JOHANSEN_TEST, 1, 0, 1, 0.1, 0);        
        assertCriticalValue(33.73238820, DefaultHypothesisTester.JOHANSEN_TEST, 4, 3, 1, 0.01, 0);        
    }
    
    public void testCriticalValueParamsAreValidated() throws Exception {
        assertCriticalValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 0, 0, 1, 0.01, 0);
        assertCriticalValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 13, 0, 1, 0.01, 0);
        assertCriticalValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, -1, 1, 0.01, 0);
        assertCriticalValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, 4, 1, 0.01, 0);
        assertCriticalValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, 0, 0, 0.01, 0);
        assertCriticalValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, 0, 3, 0.01, 0);
        assertCriticalValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, 0, 1, -0.01, 0);
        assertCriticalValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, 0, 1, 1.01, 0);
        assertCriticalValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, 0, 1, 0.01, -1);
    }

    public void testPValueCanBeEstimated() throws Exception {
        assertPValue(0.68594692, DefaultHypothesisTester.URC_TEST, 1, 0, 1, 0.01, 0);
        assertPValue(0.69877782, DefaultHypothesisTester.URC_TEST, 1, 0, 1, 0.05, 0);
        assertPValue(0.71441539, DefaultHypothesisTester.URC_TEST, 1, 0, 1, 0.1, 0);
        assertPValue(0.99998863, DefaultHypothesisTester.URC_TEST, 4, 3, 1, 0.01, 0);        
        assertPValue(0.68593103, DefaultHypothesisTester.ECM_TEST, 1, 0, 1, 0.01, 0);
        assertPValue(0.69876740, DefaultHypothesisTester.ECM_TEST, 1, 0, 1, 0.05, 0);
        assertPValue(0.71442130, DefaultHypothesisTester.ECM_TEST, 1, 0, 1, 0.1, 0);
        assertPValue(0.99960596, DefaultHypothesisTester.ECM_TEST, 4, 3, 1, 0.01, 0);        
        assertPValue(0.93438108, DefaultHypothesisTester.JOHANSEN_TEST, 1, 0, 1, 0.01, 0);        
        assertPValue(0.85455867, DefaultHypothesisTester.JOHANSEN_TEST, 1, 0, 1, 0.05, 0);        
        assertPValue(0.79484622, DefaultHypothesisTester.JOHANSEN_TEST, 1, 0, 1, 0.1, 0);        
        assertPValue(1.00000000, DefaultHypothesisTester.JOHANSEN_TEST, 4, 3, 1, 0.01, 0);        
    }    

    public void testPValueParamsAreValidated() throws Exception {
        assertPValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 0, 0, 1, 0.01, 0);
        assertPValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 13, 0, 1, 0.01, 0);
        assertPValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, -1, 1, 0.01, 0);
        assertPValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, 4, 1, 0.01, 0);
        assertPValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, 0, 0, 0.01, 0);
        assertPValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, 0, 3, 0.01, 0);
        assertPValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, 0, 1, -0.01, 0);
        assertPValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, 0, 1, 1.01, 0);
        assertPValueParamsInvalid(DefaultHypothesisTester.URC_TEST, 1, 0, 1, 0.01, -1);
    }

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
