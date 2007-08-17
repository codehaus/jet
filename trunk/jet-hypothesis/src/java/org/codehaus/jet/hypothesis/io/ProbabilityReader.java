package org.codehaus.jet.hypothesis.io;

import java.io.IOException;
import java.util.Scanner;

import org.codehaus.jet.hypothesis.io.readers.AbstractReader;

/**
 * Reader of probabilities required to construct the covariance matrix of the GLS regression
 * 
 * @author Mauro Talevi
 */
public class ProbabilityReader extends AbstractReader {
    
    private static final String RESOURCE_PATH = "org/codehaus/jet/hypothesis/io/probs.tab";    
    private double[] norms;
    private double[] probs;
        
    public void read() throws IOException {
        String[] lines = readLines(RESOURCE_PATH);
        probs = new double[lines.length];
        norms = new double[lines.length];
        for (int i = 0; i < lines.length; i++) {
            Scanner scanner = new Scanner(lines[i]);           
            probs[i] = scanner.nextDouble();
            norms[i] = scanner.nextDouble(); 
        }
    }

    public double[] getProbs(){
        return probs;
    }
    
    public double[] getNorms(){
        return norms;
    }    

}

