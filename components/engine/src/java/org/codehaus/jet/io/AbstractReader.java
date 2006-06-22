package org.codehaus.jet.io;

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

    /**
     * Splits a String into an array of Strings
     * 
     * @param value
     *            the String to split
     * @param separatorRegex
     *            the regex used to split the value
     * @return An array of Strings
     */
    protected String[] split(String value, String separatorRegex) {
        if (value == null) {
            return new String[] {};
        }
        return value.split(separatorRegex);
    }

    /**
     * Splits line into an array of fields separated by
     * {@link #FIELD_SEPARATOR FIELD_SEPARATOR}
     * 
     * @param line the String encoding the line to split
     * @return An array of Strings, one for each field
     */
    protected String[] splitLine(String line) {
        return split(line, FIELD_SEPARATOR_REGEX);
    }

    /**
     * Converts String value to <code>null</code> if empty or starts with {@see NA_PREFIX}
     * 
     * @param value
     * @return A String value or <code>null</code>
     */
    protected String toString(String value) {
        if ( value == null || value.trim().length() == 0|| value.startsWith(NA_PREFIX)) {
            return null;            
        }
        return value;
    }

    /**
     * Converts String value to a long
     * 
     * @param value
     * @return A long 
     * @throws IllegalArgumentException if value not parseable
     */
    protected long toLong(String value) throws IllegalArgumentException {
        try {
            return Long.parseLong(value);
        } catch ( NumberFormatException e) {
            throw new IllegalArgumentException("Not a long: "+value, e);
        }
    }



    /**
     * Converts String value to a double
     * 
     * @param value A string representing a double value 
     * @return A double or 0.0d if value is an empty string
     * @throws IllegalArgumentException if value not parseable
     */
    protected double toDouble(String value) throws IllegalArgumentException {
        try {
            if (value.trim().length() == 0) return 0;
            return Double.parseDouble(value);
        } catch ( NumberFormatException e) {
            throw new IllegalArgumentException("Not a double: "+value, e);
        }
    }

    
}
