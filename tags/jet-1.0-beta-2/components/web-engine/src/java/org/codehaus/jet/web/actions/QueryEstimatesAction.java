package org.codehaus.jet.web.actions;

import org.codehaus.jet.JetException;




/**
 * Action to query estimates for display
 * 
 * @author Mauro Talevi
 */
public interface QueryEstimatesAction extends JetAction {

    /**
     * Estimates critical value
     * @param testName 
     * @param options 
     * @param level
     * @param sampleSize
     * @return A critical value
     * @throws JetException
     */
    public double estimateCriticalValue(String testName, int[] options, double level, int sampleSize) throws JetException;

    /**
     * Estimates P-value
     * @param testName
     * @param options
     * @param level
     * @param sampleSize
     * @return A critical value
     * @throws JetException
     */
    public double estimatePValue(String testName, int[] options, double level, int sampleSize) throws JetException;

    

}