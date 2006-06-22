package org.codehaus.jet.web.actions.struts;

import java.util.Arrays;

import org.apache.struts.util.LabelValueBean;
import org.codehaus.jet.JetEngine;
import org.codehaus.jet.JetException;
import org.codehaus.jet.web.actions.QueryEstimatesAction;
import org.codehaus.jet.web.forms.FormValidator;
import org.codehaus.jet.web.forms.struts.StrutsEstimatesQueryForm;


/**
 * Struts implementation of QueryEstimatesAction
 * 
 * @author Mauro Talevi
 */
public class StrutsQueryEstimatesAction 
	extends AbstractStrutsJetAction implements QueryEstimatesAction {

    private JetEngine engine;
    private FormValidator validator;
    
    /**
     * Creates a StrutsQueryEstimatesAction with a given Jet engine
     * @param engine the JetEngine
     * @param validator the query form validator
     * @throws JetException if engine creation fails
     */
    public StrutsQueryEstimatesAction(JetEngine engine, FormValidator validator) throws JetException {
        this.engine = engine;
        this.validator = validator;
    }

	public String execute() {
        StrutsEstimatesQueryForm form = (StrutsEstimatesQueryForm)getForm();
        if ( form.queryAction() ){
            return queryEstimates(form);
        } else {
            return prepareEstimatesQuery(form);
        }
	}

    /**
     * Executes an estimates query 
     * @param form the StrutsEstimatesQueryForm
     * @return A forward key
     */
    private String queryEstimates(StrutsEstimatesQueryForm form) {
        newMessages();
        try {
            populateEstimatesForm(form);
            validateEstimatesQuery(form);           
            int[] options = parseInts(form.getOptions());
            double level = form.getLevel();
            int sampleSize = form.getSampleSize();
            String testName = form.getTestName();
            form.setCriticalValue(estimateCriticalValue(testName, options, level, sampleSize));
            form.setPValue(estimatePValue(testName, options, level, sampleSize));
            saveMessages();
        } catch ( Exception e) {   
            addMessageException(e);
            saveMessages();
            return ERROR;
        }
        return SUCCESS;
    }

    private int[] parseInts(String options) {
        String[] parts = options.split(",");
        int[] ints = new int[parts.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(parts[i]);
        }
        return ints;
    }

    private String formatInts(int[] options) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < options.length; i++) {
            sb.append(options[i]);
            if ( i < options.length - 1 ){
                sb.append(",");
            }
         }
        return sb.toString();
    }
    
    /**
     * Validates an estimates query form input 
     * @param form the StrutsEstimatesQueryForm
     * @throws IllegalArgumentException if any form input is not set or valid
     */
    protected void validateEstimatesQuery(StrutsEstimatesQueryForm form) {
    }


    /**
     * Prepares estimates query
     * @param form the StrutsEstimatesQueryForm
     * @return A forward key
     */
    private String prepareEstimatesQuery(StrutsEstimatesQueryForm form) {
        newMessages();
        try {
            populateEstimatesForm(form);
            saveMessages();
        } catch ( Exception e) {
            addMessageException(e);
            saveMessages();
            return ERROR;
        }
        return SUCCESS;
    }


    /**
     * Populates estimates form with engine lists
     * @param form the StrutsEstimatesQueryForm
     * @throws JetException
     */
    protected void populateEstimatesForm(StrutsEstimatesQueryForm form) throws JetException {
        form.setTestNames(toLabelValueBeans(engine.getTestNames()));
    }

    /**
     * Converts an array of names to a list of label-value pairs
     * @param names the array of names
     * @return An array of LabelValueBeans
     */
    protected LabelValueBean[] toLabelValueBeans(String[] names) {
        LabelValueBean[] beans = new LabelValueBean[names.length];
        for ( int i = 0; i < names.length; i++ ){
            String name = names[i];
            beans[i] = new LabelValueBean(name, name);
        }
        return beans;
    }

    public double estimateCriticalValue(String testName, int[] options, double level, int sampleSize) throws JetException {
        return engine.estimateCriticalValue(testName, options, level, sampleSize);
    }

    public double estimatePValue(String testName, int[] options, double level, int sampleSize) throws JetException {
        return engine.estimatePValue(testName, options, level, sampleSize);
    }

}

