package org.codehaus.jet.hypothesis.io.readers;

import junit.framework.TestCase;

/**
 * 
 * @author Mauro Talevi
 */
public class DefaultReaderProviderTest extends TestCase {

    public void testCanHandleReaderNotFound(){
        DefaultReaderProvider provider = new DefaultReaderProvider();
        String name = "non-existent";
        try {
            provider.getBetaReader(name);
            fail("IllegalArgumentException expected");
        } catch ( IllegalArgumentException e) {
            assertEquals("Reader not found for test name "+name, e.getMessage());
        }
    }
    
}
