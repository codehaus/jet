package org.codehaus.jet.io;


public abstract class AbstractBetaReader extends AbstractReader implements BetaReader {
    
    protected static final int DIMENSION = 221;
    
    protected double[][] beta;
    protected double[] weights;
    protected int nz;
    protected int nreg;
    protected int model;
    protected int minsize;
    protected String source;
    
    public AbstractBetaReader(String source) {
        this.source = source;
    }

    public double[][] getBeta(){
        return beta;
    }
    
    public double[] getWeights(){
        return weights;
    }

    public int[] getParams(){
        return new int[]{model, nreg, nz, minsize};
    }    
    

}
