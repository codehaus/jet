package org.codehaus.jet.io;


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
