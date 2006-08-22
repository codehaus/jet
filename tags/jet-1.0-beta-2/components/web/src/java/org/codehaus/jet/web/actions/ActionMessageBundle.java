package org.codehaus.jet.web.actions;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

/**
 * Represents a message bundle used the the actions
 * to display information to the user
 * @author Mauro Talevi
 */
public interface ActionMessageBundle {

    public final static String ACTION_MESSAGES = "org.codehaus.jet.web.actions.MESSAGES";    

    /**
     * Clears the collection of messages
     */
    public void newMessages();

    /**
     * Adds a message to the collection of messages.
     * 
     * @param message the message
     * @param useAsKey boolean <code>true</code> if message is a key
     */
    public void addMessage(String message, boolean useAsKey);

    /**
     * Adds an exception and cause (if available) to collection of messages
     * 
     * @param e the Exception
     */
    public void addMessageException(Exception e);

    /**
     * Returns the collection of messages
     * 
     * @return A Collection of Strings
     */
    public Collection getMessages();

    /**
     * Saves the collection of messages in the HttpServletRequest, using the
     * attribute key {@link #ACTION_MESSAGES ACTION_MESSAGES}.
     */
    public void saveMessages(HttpServletRequest request);

}