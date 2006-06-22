package org.codehaus.jet.web.session;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



/**
 * SessionListener which creates and removes temporary session 
 * images lists.
 * 
 * @author Mauro Talevi
 */
public class SessionListener implements HttpSessionListener {

    private SessionMonitor monitor = new LoggingSessionMonitor();
    
    /**
     * Creates a list in the session to store the image paths.
     * {@inheritDoc}
     */
    public void sessionCreated(HttpSessionEvent event) {
        List images = new ArrayList();
        event.getSession().setAttribute(SessionWriter.IMAGES_LIST, images);
        monitor.imageListCreated();
    }

    /**
     * Cleans up the images written in the session.
     * {@inheritDoc}
     */
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        List images = (List)session.getAttribute(SessionWriter.IMAGES_LIST);
        String rootPath = session.getServletContext().getRealPath("/");
        if ( images != null ){
            for ( Iterator i = images.iterator(); i.hasNext(); ){
                String path = (String)i.next();
                String realPath = rootPath+File.separator+path;
                File file = new File(realPath);
                file.delete();
                monitor.imageDeleted(realPath);
            }
        } else {
            monitor.imageListNotFound();            
        }
    }

}
