package org.codehaus.jet.web.session;

/**
 * Monitor for session events
 * 
 * @author Mauro Talevi
 */
public interface SessionMonitor {

    /**
     * An image path has been added to session image list
     * @param imagePath
     */
    void imagePathAdded(String imagePath);

    /**
     * An image has been written to servlet context
     * @param imagePath
     */
    void imageWritten(String imagePath);

    /**
     * An image has been deleted from servlet context
     * @param imagePath
     */
    void imageDeleted(String imagePath);

    /**
     * An image list has been created in session 
     */
    void imageListCreated();

    /**
     * An image list was not found in session 
     */
    void imageListNotFound();
}
