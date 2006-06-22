package org.codehaus.jet.web.session;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.codehaus.jet.web.session.SessionWriter;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

/**
 * 
 * @author Mauro Talevi
 */
public class SessionWriterTest extends MockObjectTestCase {

    private static final BufferedImage IMAGE = new BufferedImage(300, 300, BufferedImage.TYPE_BYTE_INDEXED);
    
    private SessionWriter writer;
    
    public void setUp(){ 
        writer = new SessionWriter();        
    }
    
    public void testWriteImageAsJPEG() throws Exception {
        writer.writeImageAsJPEG(mockSession(new ArrayList()), IMAGE);
    }

    public void testImageIsNotAddedToSessionListIfListNotFond() throws Exception {
        writer.writeImageAsJPEG(mockSession(null), IMAGE);
    }
    
    private HttpSession mockSession(List list) {
        Mock mock = mock(HttpSession.class);
        mock.expects(atLeastOnce()).method("getAttribute").with(eq(SessionWriter.IMAGES_LIST)).will(returnValue(list));
        mock.expects(atLeastOnce()).method("getServletContext").withAnyArguments().will(returnValue(mockServletContext()));
        return (HttpSession)mock.proxy();
    }
    
    private ServletContext mockServletContext() {
        Mock mock = mock(ServletContext.class);        
        mock.expects(atLeastOnce()).method("getRealPath").withAnyArguments().will(returnValue("target/"+Long.toString(System.currentTimeMillis())));
        return (ServletContext)mock.proxy();
    }
}
