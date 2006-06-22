package org.codehaus.jet.web.actions.bundles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jet.web.actions.ActionMessageBundle;


/**
 * Abstract base class for all implementations of ActionMessageBundle.
 * Concrete implementations need to provide an implementation of 
 * {@link #getResourceMessage(String) getResourceMessage(String)}.
 * 
 * @author Mauro Talevi
 */
public abstract class AbstractActionMessageBundle implements ActionMessageBundle {

    private Collection messages;

    public void newMessages() {
        this.messages = new ArrayList();
    }

    public void addMessage(String message, boolean asKey) {
        if (messages == null) {
            newMessages();
        }
        if ( asKey ){
            this.messages.add(getResourceMessage(message));
        } else {
            this.messages.add(message);
        }
    }

    protected abstract String getResourceMessage(String key);

    public void addMessageException(Exception e) {
        addMessage(e.getMessage(), false);
        Throwable cause = e.getCause();
        if ( cause != null && cause.getMessage()!=null){
            addMessage(cause.getMessage(), false);
        }        
    }

    public Collection getMessages() {
        if (messages == null) {
            return Collections.EMPTY_LIST;
        }
        return messages;
    }

    public void saveMessages(HttpServletRequest request) {
        if (request == null) {
            return;
        }
        // Remove any messages attribute if none are required
        if (messages == null || messages.isEmpty()) {
            request.removeAttribute(ACTION_MESSAGES);
            return;
        }
    
        // Save the messages we need
        request.setAttribute(ACTION_MESSAGES, messages);
    }

}
