package org.codehaus.jet.hypothesis.io;

/**
 * Reader of critical values required to estimate asymptotic rejection values
 * 
 * @author Mauro Talevi
 */
public interface CriticalValueReader extends WeightReader {

    double[] getCriticalValues();

}