package org.codehaus.jet.hypothesis.io.readers;

import java.util.NoSuchElementException;

import junit.framework.TestCase;


public class NoopReaderTest extends TestCase {
    
    private NoopReader reader;
    
    public void setUp() throws Exception {        
        reader = new NoopReader();
    }

    public void testCanGetNonContextClassLaader() throws Exception {
        Thread.currentThread().setContextClassLoader(null);
        assertEquals(AbstractReader.class.getClassLoader(), reader.getClassLoader());
    }
    
    public void testCanHandleNotFindingResource() throws Exception {
        try {
            reader.getReader("/non/existent/resource", reader.getClassLoader());
            fail("NoSuchElementException expected");
        } catch ( NoSuchElementException e) {
            // expected
        }
    }

    static class NoopReader extends AbstractReader {
        
    }
}
