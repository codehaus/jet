package org.codehaus.jet.web.actions.struts;

import org.codehaus.jet.web.actions.JetAction;
import org.jmock.MockObjectTestCase;


/**
 * @author Mauro Talevi
 */
public class StrutsForwardHomeActionTest extends MockObjectTestCase {

    private StrutsForwardHomeAction action;
    
    public void setUp() throws Exception{
        action = new StrutsForwardHomeAction();
    }
    
    public void testExecute() throws Exception {
        assertEquals(JetAction.SUCCESS, action.execute());
    }

}

