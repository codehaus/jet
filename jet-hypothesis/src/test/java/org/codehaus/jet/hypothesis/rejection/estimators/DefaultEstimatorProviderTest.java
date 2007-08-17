package org.codehaus.jet.hypothesis.rejection.estimators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * 
 * @author Mauro Talevi
 */
public class DefaultEstimatorProviderTest {

    @Test
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
