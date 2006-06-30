package org.codehaus.jet.hypothesis;

/**
 * Base exception for all hypothesis functionality
 * 
 * @author Mauro Talevi
 */
public class HypothesisException extends Exception {

    /**
     * Creates a HypothesisException with message and cause
     * @param message the message String 
     * @param cause the Throwable which caused the exception 
     */
    public HypothesisException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a HypothesisException with message
     * @param message the message String 
     */
    public HypothesisException(String message) {
        super(message);
    }

}
