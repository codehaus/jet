package org.codehaus.jet.hypothesis.io.readers;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Scanner;

public class URCReader extends AbstractBetaReader {
    
    private static final String RESOURCE_PATH = "org/codehaus/jet/hypothesis/io/{0}/urc-{1}.tab";
    
    public URCReader(String source) {
        super(source);
    }

    public void read(int[] options) throws IOException {        
        validateParams(options);
        int integrationVariables = options[0];
        int regressionVariables = options[1];
        int testType = options[2];
        String[] lines = readLines(MessageFormat.format(RESOURCE_PATH, new Object[]{source, integrationVariables}));
        int start = 0;
        switch ( testType ){
        case 1: start = regressionVariables*(DIMENSION+1)+1; break;
        case 2: start = 4*(DIMENSION+1)+1; break;
        default: throw new IllegalArgumentException("Invalid testType "+testType);
        }
        beta = new double[DIMENSION][];
        weights = new double[DIMENSION];
        for (int i = 0; i < DIMENSION + 1; i++) {
            Scanner scanner = new Scanner(lines[start + i]);           
            if ( i == 0 ){
                scanner.next();
                nz = scanner.nextInt();
                nreg = scanner.nextInt();
                model = scanner.nextInt();
                minsize = scanner.nextInt();
            } else {
               int nvar = 0;
               if (model == 2 || model == 4) {
                   nvar = 3;
               } else {
                   nvar = 4;
               }
               beta[i-1] = new double[nvar];
               for ( int j = 0; j < nvar; j++ ){
                   beta[i-1][j] = scanner.nextDouble();
               }
               weights[i-1] =  scanner.nextDouble();
            }
        }
    }
    
    private void validateParams(int[] options) {
        if ( options[0] < 1 || options[0] > 12 ){
            throw new IllegalArgumentException("integrated variables must be an integer between 1 and 12");
        }
        if ( options[1] < 0 || options[1] > 3){
            throw new IllegalArgumentException("regression variables must be an integer between 0 and 3");
        }
        if ( options[2] < 1 || options[2] > 2){
            throw new IllegalArgumentException("test type must be an integer between 1 and 2");
        }
    }
    

}
