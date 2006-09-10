package org.codehaus.jet.web.forms.struts;

import org.apache.struts.util.LabelValueBean;

/**
 * Struts ActionForm associated to security estimates query actions.
 * 
 * @author Mauro Talevi
 */
@SuppressWarnings("serial")
public class StrutsEstimatesQueryForm extends StrutsJetForm {

    private LabelValueBean[] testNames;

    private String testName;
    
    String options = "1,0,1";
    
    int sampleSize = 0;

    double level = 0.01;

    double criticalValue;

    double pValue;
    
    public double getCriticalValue() {
        return criticalValue;
    }

    public void setCriticalValue(double criticalValue) {
        this.criticalValue = criticalValue;
    }

    public double getPValue() {
        return pValue;
    }

    public void setPValue(double value) {
        pValue = value;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public int getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    public LabelValueBean[] getTestNames() {
        return testNames;
    }

    public void setTestNames(LabelValueBean[] names) {
        this.testNames = names;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
    
    
}
