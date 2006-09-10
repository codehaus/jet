package org.codehaus.jet.web.actions.struts;


/**
 * Struts action to handle timeouts
 * 
 * @author Mauro Talevi
 */
public class StrutsSessionExpiredAction 
	extends AbstractStrutsJetAction {

	public String execute() {
        newMessages();
        addMessage("session.expired");
        saveMessages();
        return SUCCESS;
	}

}

