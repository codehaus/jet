package org.codehaus.jet.web.actions.struts;

import org.apache.struts.action.ActionForward;
import org.codehaus.jet.web.actions.JetAction;
import org.codehaus.jet.web.forms.struts.StrutsJetForm;


/**
 * @author Mauro Talevi
 */
public class StrutsSessionExpiredActionTest extends AbstractStrutsActionTestCase {
    
    private StrutsSessionExpiredAction action;
    
    public void setUp() throws Exception{
        super.setUp();
    }
    
    public void testExecute() throws Exception {
        action = new StrutsSessionExpiredAction();
        ActionForward forward = action.execute(createActionMappingWithForward(), new StrutsJetForm(), mockHttpServletRequestWithMethod("setAttribute"), mockHttpServletResponse());        
        assertEquals(JetAction.SUCCESS, forward.getPath());
    }
    
 }
