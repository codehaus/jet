package org.codehaus.jet;

import junit.framework.TestCase;

import org.codehaus.jet.JetException;

/**
 * 
 * @author Mauro Talevi
 */
public class JetExceptionTest extends TestCase {

    public void testWithMessage(){
        JetException e = new JetException("message");
        assertEquals("message", e.getMessage());        
    }

    public void testWithMessageAndCause(){
        Throwable cause = new Exception("cause");
        JetException e = new JetException("message", cause);
        assertEquals("message", e.getMessage());        
        assertEquals(cause, e.getCause());        
     }

}
