package org.codehaus.jet.web.actions.struts;

import org.codehaus.jet.JetEngine;
import org.codehaus.jet.JetException;

public class TestJetEngine implements JetEngine {

    public double estimateCriticalValue(String source, int[] options, double level, int sampleSize) throws JetException {
        return 0;
    }

    public double estimatePValue(String source, int[] options, double level, int sampleSize) throws JetException {
        return 0;
    }

    public String[] listTestNames() {
        return null;
    }

}
