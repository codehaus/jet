package org.codehaus.jet.web.actions.struts;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jet.web.actions.ActionMessageBundle;
import org.codehaus.jet.web.actions.ActionMonitor;
import org.codehaus.jet.web.actions.bundles.DefaultActionMessageBundle;
import org.codehaus.jet.web.actions.monitors.CommonsLoggingActionMonitor;
import org.codehaus.jet.web.forms.struts.StrutsJetForm;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;


/**
 * @author Mauro Talevi
 */
public class StrutsNoopActionTest extends MockObjectTestCase {

    private StrutsNoopAction action;
    private StrutsJetForm form;
    
    public void setUp() throws Exception{
        action = new StrutsNoopAction();
        form = new StrutsJetForm();
    }
    
    public void testWithNullParams(){
        try {
            action = new StrutsNoopAction(null, null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            assertEquals("messageBundle", e.getMessage());
        }
        try {
            action = new StrutsNoopAction(new DefaultActionMessageBundle(), null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            assertEquals("monitor", e.getMessage());
        }
    }

    public void testWithDefaultParams(){
        assertNotNull("messageBundle", action.getActionMessageBundle());
        assertTrue("messageBundle", action.getActionMessageBundle() instanceof DefaultActionMessageBundle);
        assertNotNull("monitor", action.getActionMonitor());
        assertTrue("monitor", action.getActionMonitor() instanceof CommonsLoggingActionMonitor);        
    }

    public void testWithDefaultBundle(){
    	action = new StrutsNoopAction(new NoopActionMonitor());
        assertNotNull("messageBundle", action.getActionMessageBundle());
        assertTrue("messageBundle", action.getActionMessageBundle() instanceof DefaultActionMessageBundle);
        assertNotNull("monitor", action.getActionMonitor());
        assertTrue("monitor", action.getActionMonitor() instanceof NoopActionMonitor);        
    }

    public void testWithDefaultMonitor(){
    	action = new StrutsNoopAction(new NoopActionMessageBundle());
        assertNotNull("messageBundle", action.getActionMessageBundle());
        assertTrue("messageBundle", action.getActionMessageBundle() instanceof NoopActionMessageBundle);
        assertNotNull("monitor", action.getActionMonitor());
        assertTrue("monitor", action.getActionMonitor() instanceof CommonsLoggingActionMonitor);        
    }
    
    public void testInvalidForward(){
        action = new StrutsNoopAction() {
            public String execute(){
                return null;
            }            
        };
        ActionForward forward = action.execute(createActionMappingWithNullForward(), form, mockHttpServletRequest(), mockHttpServletResponse());
        assertNull(forward);
    }

    public void testNullForward(){
        action = new StrutsNoopAction() {
            public String execute(){
                return NULL;
            }            
        };
        ActionForward forward = action.execute(createActionMappingWithNullForward(), form, mockHttpServletRequest(), mockHttpServletResponse());
        assertNull(forward);
    }

    public void testExecuteSuccess(){
        ActionForward forward = action.execute(createActionMappingWithForward(), form, mockHttpServletRequest(), mockHttpServletResponse());
        assertNotNull("mapping", action.getMapping());
        assertNotNull("form", action.getForm());
        assertNotNull("request", action.getRequest());
        assertNotNull("response", action.getResponse());
        assertEquals("success", forward.getPath());
    }

    public void testExecuteFails(){
        action = new StrutsNoopAction() {
            public String execute(){
                addMessageException(new Exception("mock"));
                return ERROR;
            }            
        };
        ActionForward forward = action.execute(createActionMappingWithForward(), form, mockHttpServletRequest(), mockHttpServletResponse());
        assertEquals("error", forward.getPath());
        assertEquals("mock", (String)action.getMessages().iterator().next());
    }
    
    public void testAddMessage(){
        action.addMessage("first message");
        action.addMessage("another message");
        assertNotNull("messages", action.getMessages());
        Iterator i = action.getMessages().iterator();
        assertEquals("first message", i.next());        
        assertEquals("another message", i.next());        
    }

    public void testAddMessageException(){
        action.addMessageException(new Exception("message"));
        assertNotNull("messages", action.getMessages());
        Iterator i = action.getMessages().iterator();
        assertEquals("message", i.next());        
    }
    
    public void testAddMessageExceptionWithCause(){
        action.addMessageException(new Exception("message", new Throwable("cause")));
        assertNotNull("messages", action.getMessages());
        Iterator i = action.getMessages().iterator();
        assertEquals("message", i.next());        
        assertEquals("cause", i.next());        
    }

    public void testGetEmptyMessages(){
        assertNotNull("messages", action.getMessages());
        assertTrue(action.getMessages().isEmpty());        
    }

    public void testSaveMessages(){
        action.execute(createActionMappingWithForward(), form, mockHttpServletRequestWithMethod("setAttribute"), mockHttpServletResponse());
        action.addMessage("some message");
        action.saveMessages();
        assertNotNull("messages", action.getMessages());
        assertEquals("some message", action.getMessages().iterator().next());        
    }

    public void testSaveEmptyMessages(){
        action.execute(createActionMappingWithForward(), form, mockHttpServletRequestWithMethod("removeAttribute"), mockHttpServletResponse());
        action.saveMessages();
        assertNotNull("messages", action.getMessages());
        assertTrue(action.getMessages().isEmpty());        
    }
    
    public void testSaveMessagesWithNullRequest(){
        action.saveMessages();
    }
    
    public void testSaveMessagesWithRequest() throws Exception {
        ActionMonitor monitor = new CommonsLoggingActionMonitor();
        action = new StrutsNoopAction(new DefaultActionMessageBundle(), monitor);
        action.execute(createActionMappingWithForward(), form, 
                mockHttpServletRequestWithMethod("setAttribute"), mockHttpServletResponse());        
        action.newMessages();
        assertTrue(action.getMessages().isEmpty());
        action.addMessage("message", false);
        action.addMessageException(new Exception("test"));
        assertTrue(action.getMessages().size() > 0);
        action.saveMessages();        
    }

    public void testGetMode(){
        action.execute(createActionMappingWithParameter("mode"), form, mockHttpServletRequest(), mockHttpServletResponse());
        assertEquals("mode", action.getMode());
    }

    public void testModeNotFound(){
        action.execute(createActionMappingWithForward(), form, mockHttpServletRequest(), mockHttpServletResponse());
        assertEquals(StrutsNoopAction.EMPTY, action.getMode());
    }

    public void testModeWithNullMapping(){
        assertEquals(StrutsNoopAction.EMPTY, action.getMode());
    }
    
    @SuppressWarnings("serial")
    private ActionMapping createActionMappingWithForward() {
        ActionMapping mapping = new ActionMapping() {
            public ActionForward findForward(String name){
                return new ActionForward(name);
            }
        };
        return mapping;
    }

    @SuppressWarnings("serial")
    private ActionMapping createActionMappingWithNullForward() {
        ActionMapping mapping = new ActionMapping() {
            public ActionForward findForward(String name){
                return null;
            }
        };
        return mapping;
    }
    
    @SuppressWarnings("serial")
    private ActionMapping createActionMappingWithParameter(final String mode) {
        ActionMapping mapping = new ActionMapping() {
            public ActionForward findForward(String name){
                return new ActionForward(name);
            }
            public String getParameter(){
                return mode;
            }
        };
        return mapping;
    }
    
    private HttpServletRequest mockHttpServletRequestWithMethod(String method){
        Mock mock = mock(HttpServletRequest.class);
        mock.expects(atLeastOnce()).method(method);
        return (HttpServletRequest)mock.proxy();
    }

    private HttpServletRequest mockHttpServletRequest(){
        Mock mock = mock(HttpServletRequest.class);
        return (HttpServletRequest)mock.proxy();
    }

    private HttpServletResponse mockHttpServletResponse(){
        Mock mock = mock(HttpServletResponse.class);
        return (HttpServletResponse)mock.proxy();
    }
    
    static class StrutsNoopAction extends AbstractStrutsJetAction {

		public StrutsNoopAction() {
            super();
        }

    	public StrutsNoopAction(ActionMessageBundle messageBundle) {
			super(messageBundle);
		}

		public StrutsNoopAction(ActionMonitor monitor) {
			super(monitor);
		}
        
        public StrutsNoopAction(ActionMessageBundle bundle, ActionMonitor monitor) {
            super(bundle, monitor);
        }
        
        public String execute() {
            return SUCCESS;
        }
        
    }

    static class NoopActionMonitor implements ActionMonitor {

		public void actionFailed(Exception cause) {			
		}

		public void actionForwardNotFound(String key) {
		}

		public void actionForwarded(String key, String path) {
		}

		public void actionMappingNotFound() {
		}
    }    
    
    static class NoopActionMessageBundle implements ActionMessageBundle {

		public void addMessage(String message, boolean useAsKey) {
		}

		public void addMessageException(Exception e) {
		}

		public Collection getMessages() {
			return null;
		}

		public void newMessages() {
		}

		public void saveMessages(HttpServletRequest request) {			
		}
    }    
    

}
