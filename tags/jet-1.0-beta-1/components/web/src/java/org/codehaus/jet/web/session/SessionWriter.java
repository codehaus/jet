package org.codehaus.jet.web.session;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartUtilities;

/**
 * A Writer for session-scoped objects
 * 
 * @author Mauro Talevi
 */
public class SessionWriter {

    public static final String IMAGES_LIST = "sessionImagesList";

    private static final String TEMP_DIR = "/temp";

    private static final String IMAGE_PREFIX = "img-";

    private static final String JPEG_EXT = ".jpeg";

    private static final String IMAGE_PATH = "{0}/{1}";

    private SessionMonitor monitor;

    /**
     * Creates a SessionWriter with default monitor
     */
    public SessionWriter(){
        this(new LoggingSessionMonitor());
    }
        
    /**
     * Creates a SessionWriter with a given monitor
     * @param monitor the SessionMonitor
     */
    public SessionWriter(SessionMonitor monitor) {
        this.monitor = monitor;
    }

    /**
     * Writes image as JPEG to servlet context
     * 
     * @param session the HttpSession
     * @param image the BufferedImage
     * @return The path of the image relative to the context
     * @throws IOException
     */
    public String writeImageAsJPEG(HttpSession session, BufferedImage image)
            throws IOException {
        File tempDir = createDir(session, TEMP_DIR);
        File tempFile = File.createTempFile(IMAGE_PREFIX, JPEG_EXT, tempDir);
        String imagePath = MessageFormat.format(IMAGE_PATH, new Object[]{tempDir.getName(), tempFile.getName()});
        writeImageAsJPEG(tempFile, image);
        monitor.imageWritten(imagePath);
        addImagePathToSessionList(session, imagePath);
        return imagePath;
    }

    /**
     * Writes image as JPEG to temp file
     * @param tempFile the temp File
     * @param image the BufferedImage
     * @throws IOException
     */
    private void writeImageAsJPEG(File tempFile, BufferedImage image) throws IOException {
        FileOutputStream fos = new FileOutputStream(tempFile);
        ChartUtilities.writeBufferedImageAsJPEG(fos, image);
        fos.close();
    }

    /**
     * Adds image path to session list 
     * 
     * @param session the HttpSession 
     * @param imagePath the image path
     */
    private void addImagePathToSessionList(HttpSession session, String imagePath) {
        List images = (List)session.getAttribute(IMAGES_LIST);
        if ( images != null ){
            images.add(imagePath);
            monitor.imagePathAdded(imagePath);
        }        
    }

    /**
     * Creates a dir - if one does not already exist - 
     * for given path in the servlet context.
     * 
     * @param session the HttpSession to get the servlet context
     * @param path the String encoding the path 
     * @return A File with the created dir
     */
    private File createDir(HttpSession session, String path) {
        String realPath = session.getServletContext().getRealPath(path);
        File dir = new File(realPath);
        // create the directory if it doesn't exist
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
}