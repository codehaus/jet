package org.codehaus.jet.io;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Scanner;

public class JohansenReader extends AbstractCriticalValueReader {
    
    private static final String RESOURCE_PATH = "org/codehaus/jet/io/{0}/joh-{1}.tab";
    
    public JohansenReader(String source) {
        super(source);
    }

    public void read(int[] options) throws IOException {        
        validateParams(options);
        int restrictionVariables = options[0];
        int regressionVariables = options[1];
        int testType = options[2];
        String[] lines = readLines(MessageFormat.format(RESOURCE_PATH, new Object[]{source, restrictionVariables}));
        int start = 0;
        switch ( testType ){
        case 1: start = regressionVariables*(DIMENSION+1)+1; break;
        case 2: start = 5*(DIMENSION+1)+1; break;
        default: throw new IllegalArgumentException("Invalid testType "+testType);
        }
        values = new double[DIMENSION];
        weights = new double[DIMENSION];
        for (int i = 0; i < DIMENSION + 1; i++) {
            Scanner scanner = new Scanner(lines[start + i]);           
            if ( i == 0 ){
                scanner.next();
            } else {
               int inverse = DIMENSION - i;
               values[inverse] =  scanner.nextDouble();
               weights[inverse] =  scanner.nextDouble();
            }
        }
    }
    
    private void validateParams(int[] options) {
        if ( options[0] < 1 || options[0] > 12 ){
            throw new IllegalArgumentException("restriction variables must be an integer between 1 and 12");
        }
        if ( options[1] < 0 || options[1] > 4){
            throw new IllegalArgumentException("regression variables must be an integer between 0 and 4");
        }
        if ( options[2] < 1 || options[2] > 2){
            throw new IllegalArgumentException("test type must be an integer between 1 and 2");
        }
    }

}
