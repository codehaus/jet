package org.codehaus.jet.web.actions.monitors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jet.web.actions.ActionMonitor;


/**
 * Implementation of ActionMonitor which uses CommonsLogging to logs to file
 * 
 * @author Mauro Talevi
 */
public class CommonsLoggingActionMonitor implements ActionMonitor {

    /** The Log instance  */
    private Log log;

    /**
     * Creates a default CommonsLoggingActionMonitor
     */
    public CommonsLoggingActionMonitor() {
        this(LogFactory.getLog(CommonsLoggingActionMonitor.class));
    }
    
    /**
     * Creates a CommonsLoggingActionMonitor with a given log 
     * @param log the Log 
     */
    public CommonsLoggingActionMonitor(Log log) {
        this.log = log;
    }

	public void actionForwarded(String key, String path) {
        log.debug("Action forwarded for key "+key+" to path "+path);
	}

	public void actionForwardNotFound(String key) {
        log.warn("Action forward not found for key "+key);
	}

	public void actionMappingNotFound() {
        log.warn("Action mapping not found");
	}

    public void actionFailed(Exception cause) {
        log.error("Action failed: "+cause.getMessage(), cause);
    }
}
