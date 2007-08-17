package org.codehaus.jet;

/**
 * Base exception for all Jet functionality
 * 
 * @author Mauro Talevi
 */
@SuppressWarnings("serial")
public class JetException extends Exception {

    /**
     * Creates a JetException with message and cause
     * @param message the message String 
     * @param cause the Throwable which caused the exception 
     */
    public JetException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a JetException with message
     * @param message the message String 
     */
    public JetException(String message) {
        super(message);
    }

}
