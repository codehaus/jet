package org.codehaus.jet.web.actions.bundles;

import java.io.InputStream;
import java.util.Arrays;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

/**
 * 
 * @author Mauro Talevi
 */
public class DefaultActionMessageBundleTest extends MockObjectTestCase {

    private static final String BUNDLE_NAME = "ActionBundle.properties";
    
    public void testNewMessages() throws Exception{
        DefaultActionMessageBundle messageBundle = new DefaultActionMessageBundle();
        messageBundle.addMessage("message one", true);
        messageBundle.newMessages();
        assertTrue(messageBundle.getMessages().isEmpty());
    }    

    public void testMessageBundleWithResourceBundle() {
        ResourceBundle bundle = getResourceBundle(BUNDLE_NAME);
        DefaultActionMessageBundle messageBundle = new DefaultActionMessageBundle(bundle);
        messageBundle.addMessage("message one", true);
        messageBundle.newMessages();
        assertTrue(messageBundle.getMessages().isEmpty());
    }
    
    public void testMessageBundleWithNoResourceBundle() throws Exception{
        String resource = "inexistent.properties";
        try {
            new DefaultActionMessageBundle(resource);
            fail("Excepted IAE");
        } catch (IllegalArgumentException e) {
            assertEquals("Failed to get resource bundle for "+resource, e.getMessage());
        }
    }

    public void testAddMessageWithResourceBundle() throws Exception{
        DefaultActionMessageBundle messageBundle = new DefaultActionMessageBundle(BUNDLE_NAME);
        messageBundle.addMessage("key1", true);
        assertEquals(Arrays.asList(new String[]{"Value 1"}), messageBundle.getMessages());
    }

    public void testAddMessageWithResourceBundleInPackage() throws Exception{
        DefaultActionMessageBundle messageBundle = new DefaultActionMessageBundle("org/codehaus/jet/web/actions/bundles/"+BUNDLE_NAME);
        messageBundle.addMessage("key1", true);
        assertEquals(Arrays.asList(new String[]{"Bundle Value 1"}), messageBundle.getMessages());
    }

    public void testAddMessageWithResourceBundleButKeyIsNotFound() throws Exception{
        DefaultActionMessageBundle messageBundle = new DefaultActionMessageBundle(BUNDLE_NAME);
        messageBundle.addMessage("inexistent-key", true);
        assertEquals(Arrays.asList(new String[]{"inexistent-key"}), messageBundle.getMessages());
    }
    
    public void testAddMessageExceptionIgnoresResourceBundle() throws Exception{
        DefaultActionMessageBundle messageBundle = new DefaultActionMessageBundle(BUNDLE_NAME);
        messageBundle.addMessageException(new Exception("error", new Exception("cause")));
        assertEquals(Arrays.asList(new String[]{"error", "cause"}), messageBundle.getMessages());
    }
    
    public void testAddMessageExceptionWithDefaultResourceBundle(){
        DefaultActionMessageBundle messageBundle = new DefaultActionMessageBundle();
        messageBundle.addMessageException(new Exception("error", new Exception("cause")));
        assertEquals(Arrays.asList(new String[]{"error", "cause"}), messageBundle.getMessages());
    }

    public void testSaveMessagesIsIgnoredIfRequestNull(){
        DefaultActionMessageBundle messageBundle = new DefaultActionMessageBundle();
        messageBundle.saveMessages(null);
    }

    public void testSaveMessagesWithNullOrEmptyMessages(){
        DefaultActionMessageBundle messageBundle = new DefaultActionMessageBundle();
        messageBundle.saveMessages(mockRequest(true, false));
        messageBundle.newMessages();
        messageBundle.saveMessages(mockRequest(true, false));
    }
 
    public void testSaveMessagesWithMessages(){
        DefaultActionMessageBundle messageBundle = new DefaultActionMessageBundle();
        messageBundle.addMessage("message one", true);
        messageBundle.saveMessages(mockRequest(false, true));
    }
    
    private ResourceBundle getResourceBundle(String resource) {        
        try {
            return new PropertyResourceBundle(getResource(resource));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get resource bundle for "+resource, e);
        }
    }

    private InputStream getResource(String resource) {
        return getClass().getClassLoader().getResourceAsStream(resource);
    }
    
    private HttpServletRequest mockRequest(boolean removeAttribute, boolean setAttribute) {
        Mock mock = mock(HttpServletRequest.class);
        if ( removeAttribute ){
            mock.expects(once()).method("removeAttribute");
        }
        if ( setAttribute ){
            mock.expects(once()).method("setAttribute");
        }
        return (HttpServletRequest)mock.proxy();
    }

}
