package org.codehaus.jet.hypothesis.rejection.estimators;

import junit.framework.TestCase;

/**
 * 
 * @author Mauro Talevi
 */
public class DefaultEstimatorProviderTest extends TestCase {

    public void testCanHandleEstimatorNotFound(){
        DefaultEstimatorProvider provider = new DefaultEstimatorProvider();
        String name = "non-existent";
        try {
            provider.getCriticalValueEstimator(name);
            fail("IllegalArgumentException expected");
        } catch ( IllegalArgumentException e) {
            assertEquals("RejectionValueEstimator not found for test name "+name, e.getMessage());
        }
        try {
            provider.getPValueEstimator(name);
            fail("IllegalArgumentException expected");
        } catch ( IllegalArgumentException e) {
            assertEquals("RejectionValueEstimator not found for test name "+name, e.getMessage());
        }
    }
    
}
