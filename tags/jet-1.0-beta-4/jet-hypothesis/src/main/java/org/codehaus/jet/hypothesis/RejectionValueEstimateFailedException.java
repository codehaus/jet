package org.codehaus.jet.hypothesis;

/**
 * Exception thrown when a rejection value estimate fails
 * 
 * @author Mauro Talevi
 */
@SuppressWarnings("serial")
public class RejectionValueEstimateFailedException extends RuntimeException {

    /**
     * Creates a RejectionValueEstimateFailedException with message and cause
     * @param message the message String 
     * @param cause the Throwable which caused the exception 
     */
    public RejectionValueEstimateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
