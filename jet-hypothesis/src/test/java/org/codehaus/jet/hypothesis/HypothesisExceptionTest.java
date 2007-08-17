package org.codehaus.jet.hypothesis;

import junit.framework.TestCase;

/**
 * 
 * @author Mauro Talevi
 */
public class HypothesisExceptionTest extends TestCase {

    public void testWithMessage(){
        HypothesisException e = new HypothesisException("message");
        assertEquals("message", e.getMessage());        
    }

    public void testWithMessageAndCause(){
        Throwable cause = new Exception("cause");
        HypothesisException e = new HypothesisException("message", cause);
        assertEquals("message", e.getMessage());        
        assertEquals(cause, e.getCause());        
     }

}
