package org.codehaus.jet;

import java.util.Arrays;

import org.codehaus.jet.engines.DefaultJetEngine;
import org.codehaus.jet.hypothesis.HypothesisException;
import org.codehaus.jet.hypothesis.HypothesisTester;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

/**
 * 
 * @author Mauro Talevi
 */
public class DefaultJetEngineTest extends MockObjectTestCase {

    private JetEngine engine;
    private double precision = 1e-6;
    private double criticalValue = 0.9;
    private double pValue = 1.9;
    private String[] testNames = new String[]{"test1", "test2"};
    
    private HypothesisTester mockHypothesisTester(boolean fail) {
        Mock mock = mock(HypothesisTester.class);
        if ( fail ) {
            mock.stubs().method("estimateCriticalValue").withAnyArguments()
                    .will(throwException(new HypothesisException("mock")));
            mock.stubs().method("estimatePValue").withAnyArguments()
                    .will(throwException(new HypothesisException("mock")));
        } else {
            mock.stubs().method("estimateCriticalValue").withAnyArguments()
                    .will(returnValue(criticalValue));
            mock.stubs().method("estimatePValue").withAnyArguments().will(
                    returnValue(pValue));
            mock.stubs().method("getTestNames").withAnyArguments().will(
                    returnValue(testNames));
            
        }
        return (HypothesisTester)mock.proxy();
    }

    public void testTesterMethodsCanBeInvoked() throws Exception {
        engine = new DefaultJetEngine(mockHypothesisTester(false)); 
        assertEquals(criticalValue, engine.estimateCriticalValue(null, null, 0.1, 100), precision);
        assertEquals(pValue, engine.estimatePValue(null, null, 0.1, 100), precision);
        assertEquals(Arrays.toString(testNames), Arrays.toString(engine.getTestNames()));
    }

    public void testTesterExceptionsCanBeCaught() throws Exception {
        engine = new DefaultJetEngine(mockHypothesisTester(true)); 
        try {
            assertEquals(criticalValue, engine.estimateCriticalValue(null,
                    null, 0.1, 100), precision);
        } catch ( JetException e) {
            assertException(e);
        }
        try {
            assertEquals(pValue, engine.estimatePValue(null, null, 0.1, 100),
                    precision);
        } catch ( JetException e) {
            assertException(e);
        }
    }

    private void assertException(JetException e) {
        assertTrue(e.getCause() instanceof HypothesisException );
        assertEquals("mock", e.getCause().getMessage());
    }
}
