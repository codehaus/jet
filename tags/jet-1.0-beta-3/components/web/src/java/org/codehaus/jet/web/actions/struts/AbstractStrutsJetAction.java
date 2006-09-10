package org.codehaus.jet.web.actions.struts;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jet.web.actions.ActionMessageBundle;
import org.codehaus.jet.web.actions.ActionMonitor;
import org.codehaus.jet.web.actions.JetAction;
import org.codehaus.jet.web.actions.bundles.DefaultActionMessageBundle;
import org.codehaus.jet.web.actions.monitors.CommonsLoggingActionMonitor;
import org.codehaus.jet.web.forms.struts.StrutsJetForm;


/**
 * Abstract base class for all Struts implementation of Jet actions
 * 
 * @author Mauro Talevi
 */
public abstract class AbstractStrutsJetAction extends Action 
    implements JetAction, ActionMessageBundle {

	protected final static String EMPTY = "";
    
    protected final static String SUBMIT_MODE = "submitMode";

	protected final static String FORM_NAME = "formName";

	protected final static String TOKEN = "token";

	protected static final String LIST_SEPARATOR = "\\|";

	protected static final String PART_SEPARATOR = ":";

	protected static final String HTTP_PREFIX = "http://";

	protected static final String HTTPS_PREFIX = "https://";

	private static final String DEFAULT_FORWARD = ERROR;

	protected StrutsJetForm form;

	protected ActionMapping mapping;

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected Collection messages;

    protected ActionMessageBundle messageBundle;

    private ActionMonitor monitor;

	/**
	 * Creates an AbstractStrutsJetAction
	 */
	protected AbstractStrutsJetAction() {
		this(new DefaultActionMessageBundle(), new CommonsLoggingActionMonitor());
	}

	/**
	 * Creates an AbstractStrutsJetAction
	 * 
	 * @param messageBundle the ActionMessageBundle
	 */
	protected AbstractStrutsJetAction(ActionMessageBundle messageBundle) {
	    this(messageBundle, new CommonsLoggingActionMonitor());
	}

	/**
	 * Creates an AbstractStrutsJetAction
	 * 
	 * @param monitor the ActionMonitor
	 */
	protected AbstractStrutsJetAction(ActionMonitor monitor) {
	    this(new DefaultActionMessageBundle(), monitor);
	}
	
	/**
	 * Creates an AbstractStrutsJetAction
	 * 
	 * @param messageBundle the ActionMessageBundle
	 * @param monitor the ActionMonitor
	 */
	protected AbstractStrutsJetAction(ActionMessageBundle messageBundle, ActionMonitor monitor) {
	    this.messageBundle = messageBundle;
        this.monitor = monitor;
        checkParams();
	}

	/**
	 * Checks if any params are <code>null</code>
	 * 
	 * @throws NullPointerException
	 *             if any param passed in the contructor is <code>null</code>
	 */
	private void checkParams() {
        if (messageBundle == null) {
            throw new NullPointerException("messageBundle");
        }
        if (monitor == null) {
            throw new NullPointerException("monitor");
        }
    }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		this.form = (StrutsJetForm) form;
		this.mapping = mapping;
		this.request = request;
		this.response = response;
		String forward = execute();
        if ( NULL.equals(forward) ){
            return null;
        }
		ActionForward actionForward = mapping.findForward(forward);
		if (actionForward == null) {
			monitor.actionForwardNotFound(forward);
			actionForward = mapping.findForward(DEFAULT_FORWARD);
		}
		monitor.actionForwarded(forward, (actionForward != null ? actionForward
				.toString() : EMPTY));
		return actionForward;
	}

    public abstract String execute();

    public void newMessages() {
        this.messageBundle.newMessages();
    }

    public void addMessage(String message, boolean useAsKey) {
        this.messageBundle.addMessage(message, useAsKey);
    }

    public void addMessageException(Exception e) {
        this.messageBundle.addMessageException(e);
        monitor.actionFailed(e);
    }

    public Collection getMessages() {
        return messageBundle.getMessages();
    }

    public void saveMessages(HttpServletRequest request) {
        messageBundle.saveMessages(request);
    }
    
    protected void addMessage(String message) {
        addMessage(message, true);
    }

    protected void saveMessages(){
        saveMessages(getRequest());
    }
    
    /**
     * Returns the action mode, represented by the ActionMapping parameter.
     * The mode can be used to use the same class for different action modes, 
     * eg prepare and submit.
     * 
     * @return A String with the value of the mode
     */
	protected String getMode() {
		if (getMapping() == null) {
			monitor.actionMappingNotFound();
			return EMPTY;
		}
		String mode = getMapping().getParameter();
		if (mode == null) {
			mode = EMPTY;
		}
		return mode;
	}

    /**
     * Returns the StrutsJetForm associated to the Struts action
     * @return The StrutsJetForm
     */
    protected StrutsJetForm getForm() {
        return form;
    }

    /**
     * Returns the Struts ActionMapping
     * @return The ActionMapping
     */
	protected ActionMapping getMapping() {
		return mapping;
	}

    /**
     * Returns the HttpServletRequest
     * @return The HttpServletRequest
     */
	protected HttpServletRequest getRequest() {
		return request;
	}

    /**
     * Returns the HttpServletResponse
     * @return The HttpServletResponse
     */
	protected HttpServletResponse getResponse() {
		return response;
	}
	
    /**
     * Returns the ActionMessageBundle 
     * @return The ActionMessageBundle
     */
	protected ActionMessageBundle getActionMessageBundle() {
		return messageBundle;
	}

    /**
     * Returns the ActionMonitor
     * @return The ActionMonitor
     */
    protected ActionMonitor getActionMonitor() {
        return monitor;
    }
    
}
