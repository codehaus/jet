package org.codehaus.jet.web.session;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.codehaus.jet.web.session.SessionListener;
import org.codehaus.jet.web.session.SessionWriter;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;


/**
 * 
 * @author Mauro Talevi
 */
public class SessionListenerTest extends MockObjectTestCase {

    private SessionListener listener;
    
    public void setUp(){ 
        listener = new SessionListener();        
    }
    
    public void testSessionCreated() throws Exception {
        listener.sessionCreated(new HttpSessionEvent(mockSession(false, false, true)));
    }

    public void testSessionDestroyed() throws Exception {
        listener.sessionDestroyed(new HttpSessionEvent(mockSession(true, true, false)));
    }

    public void testSessionDestroyedWithNoList() throws Exception {
        listener.sessionDestroyed(new HttpSessionEvent(mockSession(true, false, false)));
    }
    
    private HttpSession mockSession(boolean getAttribute, boolean returnList, boolean setAttribute) {
        Mock mock = mock(HttpSession.class);
        if ( getAttribute ){
            List list = null;
            if ( returnList ){
                list = Arrays.asList(new String[]{"temp/img-1.jpeg"});
            }
            mock.expects(atLeastOnce()).method("getAttribute").with(eq(SessionWriter.IMAGES_LIST)).will(returnValue(list));
            mock.expects(atLeastOnce()).method("getServletContext").withAnyArguments().will(returnValue(mockServletContext()));
        }
        if ( setAttribute ){
            mock.expects(atLeastOnce()).method("setAttribute").withAnyArguments();
        }
        return (HttpSession)mock.proxy();
    }

    private ServletContext mockServletContext() {
        Mock mock = mock(ServletContext.class);        
        mock.expects(atLeastOnce()).method("getRealPath").with(eq("/")).will(returnValue("target"));
        return (ServletContext)mock.proxy();
    }
    
}
