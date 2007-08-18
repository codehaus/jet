package org.codehaus.jet.hypothesis.rejection.estimators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.codehaus.jet.hypothesis.rejection.RejectionValueEstimatorProvider;
import org.codehaus.jet.hypothesis.rejection.RejectionValueType;
import org.junit.Test;

/**
 * 
 * @author Mauro Talevi
 */
public class DefaultEstimatorProviderTest {

    @Test
    public void testCanHandleEstimatorNotFound(){
        RejectionValueEstimatorProvider provider = new DefaultEstimatorProvider();
        String name = "non-existent";
        try {
            provider.getEstimator(RejectionValueType.CRITICAL, name);
            fail("IllegalArgumentException expected");
        } catch ( IllegalArgumentException e) {
            assertEquals("RejectionValueEstimator not found for test name "+name, e.getMessage());
        }
        try {
            provider.getEstimator(RejectionValueType.P, name);
            fail("IllegalArgumentException expected");
        } catch ( IllegalArgumentException e) {
            assertEquals("RejectionValueEstimator not found for test name "+name, e.getMessage());
        }
    }
    
}
