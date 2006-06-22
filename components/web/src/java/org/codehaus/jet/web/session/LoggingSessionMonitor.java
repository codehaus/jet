package org.codehaus.jet.web.session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of SessionMonitor which logs to file
 * 
 * @author Mauro Talevi
 */
public class LoggingSessionMonitor implements SessionMonitor {

    private Log log;
    
    /**
     * Creates a LoggingSessionMonitor with default Log instance
     */
    public LoggingSessionMonitor() {
        this(LogFactory.getLog(LoggingSessionMonitor.class));
    }

    /**
     * Creates a LoggingSessionMonitor with a Log instance
     * @param log the Log
     */
    public LoggingSessionMonitor(Log log) {
        this.log = log;
    }

    public void imagePathAdded(String imagePath) {
        log.info("Image path "+imagePath+" added to session list");
    }

    public void imageWritten(String imagePath) {
        log.info("Image written to path "+imagePath);
    }

    public void imageDeleted(String imagePath) {
        log.info("Image deleted from path "+imagePath);
    }

    public void imageListCreated() {
        log.info("Image list created in session");
    }

    public void imageListNotFound() {
        log.info("Image list not found in session");
    }

}
