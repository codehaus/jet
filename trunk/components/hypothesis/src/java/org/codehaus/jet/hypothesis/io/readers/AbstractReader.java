package org.codehaus.jet.hypothesis.io.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Abstract base class for readers
 * 
 * @author Mauro Talevi
 */
public abstract class AbstractReader  {

    protected static final NumberFormat NUMBER_FORMAT = new DecimalFormat("00.000");
    protected static final String COMMA = ",";    
    protected static final String COMMENT_PREFIX = "--";
    protected static final String FIELD_SEPARATOR = "|";
    protected static final String FIELD_SEPARATOR_REGEX = "\\"+FIELD_SEPARATOR;
    protected static final String NA_PREFIX = "#N/A";
    protected static final String WHITESPACE = "\\s";    
    
    protected AbstractReader(){
    }
    
    protected String[] readLines(String resource) throws IOException {
        return toLines(getReader(resource, getClassLoader()));
    }
    
    protected String[] toLines(Reader resource) throws IOException {
        BufferedReader br = new BufferedReader(resource);
        List lines = new ArrayList();
        String line = br.readLine();
        while ( line != null) {
            lines.add(line);
            line = br.readLine();
        }
        return (String[])lines.toArray(new String[lines.size()]);
    }
    
    /**
     * Retrieves reader for resource in ClassLoader
     * @param resource
     * @param classLoader
     * @return A Reader
     */
    protected Reader getReader(String resource, ClassLoader classLoader) {
        InputStream is = classLoader.getResourceAsStream(resource);
        if ( is == null ){
            throw new NoSuchElementException("Resource "+resource+" not found in ClassLoader "+classLoader.getClass());
        }
        return new InputStreamReader(is);
    }
    
    /**
     * Retrieves the context classloader of the current thread. 
     * If no ContextClassLoader is found then the ClassLoader that loaded this class is returned.
     * 
     * @return A ClassLoader
     */
    protected ClassLoader getClassLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = this.getClass().getClassLoader();
        }
        return loader;
    }

    
}
