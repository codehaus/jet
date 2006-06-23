package org.codehaus.jet;

import org.codehaus.jet.engines.DefaultJetEngine;

import junit.framework.TestCase;

public class DefaultJetEngineTest extends TestCase {

    private JetEngine jet;
    private double precision = 1e-6;
    
    public void setUp(){
        jet = new DefaultJetEngine();        
    }
    
    public void testCriticalValueCanBeEstimated() throws Exception {
        assertCriticalValue(-2.56503943, DefaultJetEngine.URC_TEST, 1, 0, 1, 0.01, 0);
        assertCriticalValue(-1.94084677, DefaultJetEngine.URC_TEST, 1, 0, 1, 0.05, 0);
        assertCriticalValue(-1.61675256, DefaultJetEngine.URC_TEST, 1, 0, 1, 0.1, 0);        
        assertCriticalValue(-5.26769030, DefaultJetEngine.URC_TEST, 4, 3, 1, 0.01, 0);        
        assertCriticalValue(-2.56549694, DefaultJetEngine.ECM_TEST, 1, 0, 1, 0.01, 0);        
        assertCriticalValue(-1.94077862, DefaultJetEngine.ECM_TEST, 1, 0, 1, 0.05, 0);
        assertCriticalValue(-1.61678256, DefaultJetEngine.ECM_TEST, 1, 0, 1, 0.1, 0);        
        assertCriticalValue(-5.03855256, DefaultJetEngine.ECM_TEST, 4, 3, 1, 0.01, 0);        
        assertCriticalValue(6.93989672, DefaultJetEngine.JOHANSEN_TEST, 1, 0, 1, 0.01, 0);        
        assertCriticalValue(4.13019335, DefaultJetEngine.JOHANSEN_TEST, 1, 0, 1, 0.05, 0);        
        assertCriticalValue(2.97607175, DefaultJetEngine.JOHANSEN_TEST, 1, 0, 1, 0.1, 0);        
        assertCriticalValue(33.73238820, DefaultJetEngine.JOHANSEN_TEST, 4, 3, 1, 0.01, 0);        
    }
    
    public void testCriticalValueParamsAreValidated() throws Exception {
        assertCriticalValueParamsInvalid(DefaultJetEngine.URC_TEST, 0, 0, 1, 0.01, 0);
        assertCriticalValueParamsInvalid(DefaultJetEngine.URC_TEST, 13, 0, 1, 0.01, 0);
        assertCriticalValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, -1, 1, 0.01, 0);
        assertCriticalValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, 4, 1, 0.01, 0);
        assertCriticalValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, 0, 0, 0.01, 0);
        assertCriticalValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, 0, 3, 0.01, 0);
        assertCriticalValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, 0, 1, -0.01, 0);
        assertCriticalValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, 0, 1, 1.01, 0);
        assertCriticalValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, 0, 1, 0.01, -1);
    }

    public void testPValueCanBeEstimated() throws Exception {
        assertPValue(0.68594692, DefaultJetEngine.URC_TEST, 1, 0, 1, 0.01, 0);
        assertPValue(0.69877782, DefaultJetEngine.URC_TEST, 1, 0, 1, 0.05, 0);
        assertPValue(0.71441539, DefaultJetEngine.URC_TEST, 1, 0, 1, 0.1, 0);
        assertPValue(0.99998863, DefaultJetEngine.URC_TEST, 4, 3, 1, 0.01, 0);        
        assertPValue(0.68593103, DefaultJetEngine.ECM_TEST, 1, 0, 1, 0.01, 0);
        assertPValue(0.69876740, DefaultJetEngine.ECM_TEST, 1, 0, 1, 0.05, 0);
        assertPValue(0.71442130, DefaultJetEngine.ECM_TEST, 1, 0, 1, 0.1, 0);
        assertPValue(0.99960596, DefaultJetEngine.ECM_TEST, 4, 3, 1, 0.01, 0);        
        assertPValue(0.93438108, DefaultJetEngine.JOHANSEN_TEST, 1, 0, 1, 0.01, 0);        
        assertPValue(0.85455867, DefaultJetEngine.JOHANSEN_TEST, 1, 0, 1, 0.05, 0);        
        assertPValue(0.79484622, DefaultJetEngine.JOHANSEN_TEST, 1, 0, 1, 0.1, 0);        
        assertPValue(1.00000000, DefaultJetEngine.JOHANSEN_TEST, 4, 3, 1, 0.01, 0);        
    }    

    public void testPValueParamsAreValidated() throws Exception {
        assertPValueParamsInvalid(DefaultJetEngine.URC_TEST, 0, 0, 1, 0.01, 0);
        assertPValueParamsInvalid(DefaultJetEngine.URC_TEST, 13, 0, 1, 0.01, 0);
        assertPValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, -1, 1, 0.01, 0);
        assertPValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, 4, 1, 0.01, 0);
        assertPValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, 0, 0, 0.01, 0);
        assertPValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, 0, 3, 0.01, 0);
        assertPValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, 0, 1, -0.01, 0);
        assertPValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, 0, 1, 1.01, 0);
        assertPValueParamsInvalid(DefaultJetEngine.URC_TEST, 1, 0, 1, 0.01, -1);
    }

    private void assertCriticalValue(double expected, String testName, int integratedVariables, int regressionVariables, 
            int testType, double level, int sampleSize) throws Exception{
        assertDouble(expected, jet.estimateCriticalValue(testName, new int[]{integratedVariables, regressionVariables,
                testType}, level, sampleSize));
    }

    private void assertPValue(double expected, String testName, int integratedVariables, int regressionVariables, 
            int testType, double level, int sampleSize) throws Exception{
        assertDouble(expected, jet.estimatePValue(testName, new int[]{integratedVariables, regressionVariables,
                testType}, level, sampleSize));
    }

    private boolean assertCriticalValueParamsInvalid(String testName, int integratedVariables, 
            int regressionVariables, int testType, double level, int sampleSize) {
        try {
            jet.estimateCriticalValue(testName, new int[]{integratedVariables, 
                    regressionVariables, testType}, level, sampleSize);
            fail("JetException expected");
            return false;
        } catch ( JetException e) {
           return e.getCause() instanceof IllegalArgumentException;
        }
    }

    private boolean assertPValueParamsInvalid(String testName, int integratedVariables, 
            int regressionVariables, int testType, double level, int sampleSize) {
        try {
            jet.estimatePValue(testName, new int[]{integratedVariables, 
                    regressionVariables, testType}, level, sampleSize);
            fail("JetException expected");
            return false;
        } catch ( JetException e) {
            return e.getCause() instanceof IllegalArgumentException;
        }
    }

    private void assertDouble(double expected, double actual) {
        assertEquals(expected, actual, precision);
    }


}
