package org.codehaus.jet.hypothesis.io.readers;

import org.codehaus.jet.hypothesis.io.CriticalValueReader;


public abstract class AbstractCriticalValueReader extends AbstractReader implements CriticalValueReader {
    
    protected static final int DIMENSION = 221;
    
    protected String source;
    protected double[] values;
    protected double[] weights;    
    
    public AbstractCriticalValueReader(String source) {
        this.source = source;
    }

    public double[] getCriticalValues() {
        return values;
    }

    public double[] getWeights() {
        return weights;
    }

}
