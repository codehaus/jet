package org.codehaus.jet.engines;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import org.codehaus.jet.EstimatorProvider;
import org.codehaus.jet.JetEngine;
import org.codehaus.jet.JetException;
import org.codehaus.jet.ReaderProvider;
import org.codehaus.jet.estimators.StatisticValueEstimator;
import org.codehaus.jet.io.BetaReader;
import org.codehaus.jet.io.CriticalValueReader;
import org.codehaus.jet.io.ProbabilityReader;
import org.codehaus.jet.providers.DefaultEstimatorProvider;
import org.codehaus.jet.providers.DefaultReaderProvider;

/**
 * Default implementation of JetEngine
 * 
 * @author Mauro Talevi
 */
public class DefaultJetEngine implements JetEngine {

    public static final String URC_TEST = "URC";
    public static final String ECM_TEST = "ECM";
    public static final String LRC_TEST = "LRC";
    public static final String JOHANSEN_TEST = "Johansen";
    
    private static final String PARAMETERS = "[testName={0}, options={1}, level={2}, sampleSize={3}]";

    private static final String[] SUPPORTED_TEST_NAMES = new String[]{URC_TEST, ECM_TEST, JOHANSEN_TEST};
    private static final String[] ASYMPTOTIC_TEST_NAMES = new String[]{LRC_TEST, JOHANSEN_TEST};

    private EstimatorProvider estimatorProvider;
    private ReaderProvider readerProvider;

    private Collection asymptoticTests = Arrays.asList(ASYMPTOTIC_TEST_NAMES);

    public DefaultJetEngine() {
        this(new DefaultEstimatorProvider(), new DefaultReaderProvider());
    }
    
    public DefaultJetEngine(EstimatorProvider estimatorProvider, ReaderProvider readerProvider) {
        this.estimatorProvider = estimatorProvider;
        this.readerProvider = readerProvider;
    }

    public double estimateCriticalValue(String testName, int[] options, double level, int sampleSize) throws JetException {
        try {            
            ProbabilityReader probabilityReader = readerProvider.getProbabilityReader();
            probabilityReader.read();
            if ( isAsymptotic(testName) ){
                CriticalValueReader reader = readerProvider.getCriticalValueReader(testName);
                reader.read(options);
                StatisticValueEstimator estimator = estimatorProvider.getCriticalValueEstimator(testName);
                return estimator.estimateValue(probabilityReader.getProbs(), probabilityReader.getNorms(),
                        reader.getWeights(), reader.getCriticalValues(), level);
            } else {
                BetaReader reader = readerProvider.getBetaReader(testName);
                reader.read(options);
                StatisticValueEstimator estimator = estimatorProvider.getCriticalValueEstimator(testName);
                return estimator.estimateValue(probabilityReader.getProbs(), probabilityReader.getNorms(),
                        reader.getWeights(), reader.getBeta(), 
                        sampleSize, reader.getParams(), level);                
            }
        } catch ( Exception e) {
            throw new JetException("Failed to estimate critical value for parameters " +
                    MessageFormat.format(PARAMETERS, new Object[]{testName, Arrays.toString(options), level, sampleSize}),e);
        }
    }

    public double estimatePValue(String testName, int[] options, double level, int sampleSize) throws JetException {
        try {
            ProbabilityReader probabilityReader = readerProvider.getProbabilityReader();
            probabilityReader.read();
            if ( isAsymptotic(testName) ){
                CriticalValueReader reader = readerProvider.getCriticalValueReader(testName);
                reader.read(options);
                StatisticValueEstimator estimator = estimatorProvider.getPValueEstimator(testName);
                return estimator.estimateValue(probabilityReader.getProbs(), probabilityReader.getNorms(),
                        reader.getWeights(), reader.getCriticalValues(), level);
            } else {
                BetaReader reader = readerProvider.getBetaReader(testName);
                reader.read(options);
                StatisticValueEstimator estimator = estimatorProvider.getPValueEstimator(testName);
                return estimator.estimateValue(probabilityReader.getProbs(), probabilityReader.getNorms(),
                        reader.getWeights(), reader.getBeta(), 
                        sampleSize, reader.getParams(), level);                
            }
        } catch ( Exception e) {
            throw new JetException("Failed to estimate p-value for parameters "+
                    MessageFormat.format(PARAMETERS, new Object[]{testName, Arrays.toString(options), level, sampleSize}),e);
        }
    }

    public String[] getTestNames() {
        return SUPPORTED_TEST_NAMES ;
    }

    private boolean isAsymptotic(String testName) {
        return asymptoticTests.contains(testName);
    }


}
