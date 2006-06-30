package org.codehaus.jet.hypothesis.testers;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import org.codehaus.jet.hypothesis.HypothesisException;
import org.codehaus.jet.hypothesis.HypothesisTester;
import org.codehaus.jet.hypothesis.io.BetaReader;
import org.codehaus.jet.hypothesis.io.CriticalValueReader;
import org.codehaus.jet.hypothesis.io.ProbabilityReader;
import org.codehaus.jet.hypothesis.io.ReaderProvider;
import org.codehaus.jet.hypothesis.io.readers.DefaultReaderProvider;
import org.codehaus.jet.hypothesis.rejection.RejectionValueEstimator;
import org.codehaus.jet.hypothesis.rejection.RejectionValueEstimatorProvider;
import org.codehaus.jet.hypothesis.rejection.estimators.DefaultEstimatorProvider;

/**
 * Default implementation of HypothesisTester
 * 
 * @author Mauro Talevi
 */
public class DefaultHypothesisTester implements HypothesisTester {

    public static final String URC_TEST = "URC";
    public static final String ECM_TEST = "ECM";
    public static final String LRC_TEST = "LRC";
    public static final String JOHANSEN_TEST = "Johansen";
    
    private static final String PARAMETERS = "[testName={0}, options={1}, level={2}, sampleSize={3}]";

    private static final String[] SUPPORTED_TEST_NAMES = new String[]{URC_TEST, ECM_TEST, JOHANSEN_TEST};
    private static final String[] ASYMPTOTIC_TEST_NAMES = new String[]{LRC_TEST, JOHANSEN_TEST};

    private RejectionValueEstimatorProvider estimatorProvider;
    private ReaderProvider readerProvider;

    private Collection asymptoticTests = Arrays.asList(ASYMPTOTIC_TEST_NAMES);

    public DefaultHypothesisTester() {
        this(new DefaultEstimatorProvider(), new DefaultReaderProvider());
    }
    
    public DefaultHypothesisTester(RejectionValueEstimatorProvider estimatorProvider, ReaderProvider readerProvider) {
        this.estimatorProvider = estimatorProvider;
        this.readerProvider = readerProvider;
    }

    public double estimateCriticalValue(String testName, int[] options, double level, int sampleSize) throws HypothesisException {
        try {            
            ProbabilityReader probabilityReader = readerProvider.getProbabilityReader();
            probabilityReader.read();
            if ( isAsymptotic(testName) ){
                CriticalValueReader reader = readerProvider.getCriticalValueReader(testName);
                reader.read(options);
                RejectionValueEstimator estimator = estimatorProvider.getCriticalValueEstimator(testName);
                return estimator.estimateValue(probabilityReader.getProbs(), probabilityReader.getNorms(),
                        reader.getWeights(), reader.getCriticalValues(), level);
            } else {
                BetaReader reader = readerProvider.getBetaReader(testName);
                reader.read(options);
                RejectionValueEstimator estimator = estimatorProvider.getCriticalValueEstimator(testName);
                return estimator.estimateValue(probabilityReader.getProbs(), probabilityReader.getNorms(),
                        reader.getWeights(), reader.getBeta(), 
                        sampleSize, reader.getParams(), level);                
            }
        } catch ( Exception e) {
            throw new HypothesisException("Failed to estimate critical value for parameters " +
                    MessageFormat.format(PARAMETERS, new Object[]{testName, Arrays.toString(options), level, sampleSize}),e);
        }
    }

    public double estimatePValue(String testName, int[] options, double level, int sampleSize) throws HypothesisException {
        try {
            ProbabilityReader probabilityReader = readerProvider.getProbabilityReader();
            probabilityReader.read();
            if ( isAsymptotic(testName) ){
                CriticalValueReader reader = readerProvider.getCriticalValueReader(testName);
                reader.read(options);
                RejectionValueEstimator estimator = estimatorProvider.getPValueEstimator(testName);
                return estimator.estimateValue(probabilityReader.getProbs(), probabilityReader.getNorms(),
                        reader.getWeights(), reader.getCriticalValues(), level);
            } else {
                BetaReader reader = readerProvider.getBetaReader(testName);
                reader.read(options);
                RejectionValueEstimator estimator = estimatorProvider.getPValueEstimator(testName);
                return estimator.estimateValue(probabilityReader.getProbs(), probabilityReader.getNorms(),
                        reader.getWeights(), reader.getBeta(), 
                        sampleSize, reader.getParams(), level);                
            }
        } catch ( Exception e) {
            throw new HypothesisException("Failed to estimate p-value for parameters "+
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
