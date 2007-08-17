package org.codehaus.jet.hypothesis.io.readers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;


/**
 * 
 * @author Mauro Talevi
 */
public class DefaultReaderProviderTest {

    @Test
    public void canHandleReaderNotFound(){
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
