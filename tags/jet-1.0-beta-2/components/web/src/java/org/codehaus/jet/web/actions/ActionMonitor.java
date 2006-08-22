package org.codehaus.jet.web.actions;


/**
 * Interface to mononitor the action events
 * 
 * @author Mauro Talevi
 */
public interface ActionMonitor {

	/**
     * The action forward for key has been found
	 * @param key the forward key
	 * @param path the forward path
	 */
	void actionForwarded(String key, String path);

	/**
     * The action forward was not found for the given key
	 * @param key the forward key
	 */
	void actionForwardNotFound(String key);

	/**
	 * The action mapping was not found
	 */
	void actionMappingNotFound();

    /**
     * The action has failed
     * @param cause
     */
    void actionFailed(Exception cause);

}
