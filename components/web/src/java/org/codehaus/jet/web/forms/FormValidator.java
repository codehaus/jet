package org.codehaus.jet.web.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Collection of validator methods for form input
 * 
 * @author Mauro Talevi
 */
public class FormValidator {
    
    private static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
    private static final String COMMA = ",";
    
    private SimpleDateFormat dateFormat;
    
    /**
     * Creates a FormValidator with a date format
     * 
     * @param datePattern the date format pattern used for date validation
     */
    public FormValidator(String datePattern) {
        this.dateFormat = new SimpleDateFormat(datePattern);
    }


    /**
     * Creates a FormValidator with a default date format
     * */
    public FormValidator(){
        this(DEFAULT_DATE_PATTERN);
    }    
    
    /**
     * Determines if value is an int
     * 
     * @param value the value to be checked
     * @return A boolean <code>true</code> if value is an int
     */
    public boolean isInt(String value){
        if ( !isSet(value) ){
            return false;
        }
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Determines if value is a double
     * 
     * @param value the value to be checked
     * @return A boolean <code>true</code> if value is an double
     */
    public boolean isDouble(String value) {
        if ( !isSet(value) ){
            return false;
        }
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
     }
    
    /**
     * Determines if value is set
     * 
     * @param value the value to be checked
     * @return A boolean <code>true</code> if value is set
     */
    public boolean isSet(String value){
        return ( value != null && value.length() > 0);
    }

    /**
     * Determines if value is a CSV of ints
     * @param value the value to be checked
     * @return  A boolean <code>true</code> if value is CSV of ints
     */
    public boolean areInts(String value) {
        if ( !isSet(value) ){
            return false;
        }
        try {
            parseInts(value);
            return true;
        } catch ( NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Determines if value is a CSV of doubles
     * @param value the value to be checked
     * @return  A boolean <code>true</code> if value is CSV of doubles
     */
    public boolean areDoubles(String value) {
        if ( !isSet(value) ){
            return false;
        }
        try {
            parseDoubles(value);
            return true;
        } catch ( NumberFormatException e) {
            return false;
        }
    }    

    /**
     * Determines if values are set 
     * @param values the values to be checked
     * @return  A boolean <code>true</code> if values are set
     */
    public boolean areSet(String[] values) {
        return ( values != null && values.length > 0 );
    }

    
    /**
     * Determines if value is a valid date using the configured DateFormat
     * @param value the value to be checked
     * @return A boolean <code>true</code> if value is valid date
     */
    public boolean isValidDate(String value) {
        try {
            parseDate(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Formats a Date using the configured DateFormat
     * @param date the Date to format
     * @return A formatted date String
     */
    public String formatDate(Date date) {
        return dateFormat.format(date);
    }    

    /**
     * Parses a formatted date String using the configured DateFormat
     * @param value the formatted date value
     * @return The Date 
     * @throws ParseException
     */
    public Date parseDate(String value) throws ParseException {
        return dateFormat.parse(value);
    }


    /**
     * Returns the date format pattern
     * @return A String representing the date pattern
     */
    public String getDatePattern() {
        return dateFormat.toPattern();
    }

    /**
     * Parses a CSV String to an array of ints
     * @param csv a CSV String
     * @return An array of ints
     */
    public int[] parseInts(String csv) {
        String[] parts = csv.split(COMMA);
        int[] ints = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            ints[i] = Integer.parseInt(parts[i]);
        }
        return ints;
    }

    /**
     * Parses a CSV String to an array of doubles
     * @param csv a CSV String
     * @return An array of doubles
     */
    public double[] parseDoubles(String csv) {
        String[] parts = csv.split(COMMA);
        double[] doubles = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            doubles[i] = Double.parseDouble(parts[i]);
        }
        return doubles;
    }    


}

