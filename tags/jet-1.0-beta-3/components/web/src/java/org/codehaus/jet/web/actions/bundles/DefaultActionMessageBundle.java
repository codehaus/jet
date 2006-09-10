package org.codehaus.jet.web.actions.bundles;

import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Default implementation of ActionMessageBundle which uses a ResourceBundle
 * to retrieve the messages.  
 * The resource bundle is required but if a key is not found for a given message
 * the key itself will be returned.
 * 
 * @author Mauro Talevi
 */
public class DefaultActionMessageBundle extends AbstractActionMessageBundle {
    
    private static final String DEFAULT_ACTION_BUNDLE = "ActionBundle.properties";

    private ResourceBundle resourceBundle;
    
    /**
     * Creates DefaultActionMessageBundle with no resource bundle
     */
    public DefaultActionMessageBundle() {
        this(DEFAULT_ACTION_BUNDLE);
    }

    /**
     * Creates DefaultActionMessageBundle with a given resource bundle
     * @param bundle the ResourceBundle
     */
    public DefaultActionMessageBundle(ResourceBundle bundle) {
        resourceBundle = bundle;
    }

    /**
     * Creates DefaultActionMessageBundle with a given resource
     * @param resource the path of the resource with which to build a bundle
     */
    public DefaultActionMessageBundle(String resource) {
        resourceBundle = getResourceBundle(resource);
    }
    
    /**
     * Returns the resource bundle for the given resource
     * @param resource the path of the resource with which to build a bundle
     * @return A ResourceBundle
     * @throws IllegalArgumentException if fails to retrieve bundle
     */
    private ResourceBundle getResourceBundle(String resource) {        
        try {
            return new PropertyResourceBundle(getResource(resource));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get resource bundle for "+resource, e);
        }
    }

    /**
     * Returns an input stream for the resource loaded from the class classloader
     * @param resource the path of the resource 
     * @return The InputStream 
     */
    private InputStream getResource(String resource) {
        return getClass().getClassLoader().getResourceAsStream(resource);
    }

    protected String getResourceMessage(String key) {
        try {
            return resourceBundle.getString(key);
        } catch ( MissingResourceException e) {
            return key;
        }
    }
    
}
