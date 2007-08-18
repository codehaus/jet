package org.codehaus.jet.hypothesis.testers;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import org.codehaus.jet.hypothesis.RejectionValueEstimateFailedException;
import org.codehaus.jet.hypothesis.HypothesisTest;
import org.codehaus.jet.hypothesis.HypothesisTester;
import org.codehaus.jet.hypothesis.io.BetaReader;
import org.codehaus.jet.hypothesis.io.CriticalValueReader;
import org.codehaus.jet.hypothesis.io.ProbabilityReader;
import org.codehaus.jet.hypothesis.io.ReaderProvider;
import org.codehaus.jet.hypothesis.io.readers.DefaultReaderProvider;
import org.codehaus.jet.hypothesis.rejection.RejectionValueEstimator;
import org.codehaus.jet.hypothesis.rejection.RejectionValueEstimatorProvider;
import org.codehaus.jet.hypothesis.rejection.RejectionValueType;
import org.codehaus.jet.hypothesis.rejection.estimators.DefaultEstimatorProvider;

/**
 * Default implementation of HypothesisTester
 * 
 * @author Mauro Talevi
 */
public class DefaultHypothesisTester implements HypothesisTester {

    private static final String PARAMETERS = "[type={0}, testName={1}, options={2}, level={3}, sampleSize={4}]";

    static final String[] SUPPORTED_TEST_NAMES = new String[]{HypothesisTest.URC.getName(), HypothesisTest.ECM.getName(), HypothesisTest.JOHANSEN.getName()};
    static final String[] ASYMPTOTIC_TEST_NAMES = new String[]{HypothesisTest.LRC.getName(),  HypothesisTest.JOHANSEN.getName()};

    private RejectionValueEstimatorProvider estimatorProvider;
    private ReaderProvider readerProvider;

    private Collection<String> asymptoticTests = Arrays.asList(ASYMPTOTIC_TEST_NAMES);

    public DefaultHypothesisTester() {
        this(new DefaultEstimatorProvider(), new DefaultReaderProvider());
    }
    
    public DefaultHypothesisTester(RejectionValueEstimatorProvider estimatorProvider, ReaderProvider readerProvider) {
        this.estimatorProvider = estimatorProvider;
        this.readerProvider = readerProvider;
    }

    public double estimateRejectionValue(RejectionValueType type, String testName, int[] options, double level, int sampleSize) {
        try {            
            ProbabilityReader probabilityReader = readerProvider.getProbabilityReader();
            probabilityReader.read();
            if ( isAsymptotic(testName) ){
                CriticalValueReader reader = readerProvider.getCriticalValueReader(testName);
                reader.read(options);
                RejectionValueEstimator estimator = estimatorProvider.getEstimator(type, testName);
                return estimator.estimateAsymptoticValue(probabilityReader.getNorms(), probabilityReader.getProbs(),
                        reader.getWeights(), reader.getCriticalValues(), level);
            } else {
                BetaReader reader = readerProvider.getBetaReader(testName);
                reader.read(options);
                RejectionValueEstimator estimator = estimatorProvider.getEstimator(type, testName);
                return estimator.estimateValue(probabilityReader.getNorms(), probabilityReader.getProbs(),
                        reader.getWeights(), reader.getBeta(), 
                        sampleSize, reader.getParams(), level);                
            }
        } catch ( Exception e) {
            throw new RejectionValueEstimateFailedException("Failed to estimate rejection value for parameters " +
                    MessageFormat.format(PARAMETERS, new Object[]{type, testName, Arrays.toString(options), level, sampleSize}),e);
        }        
    }

    public String[] listTestNames() {
        return SUPPORTED_TEST_NAMES ;
    }

    private boolean isAsymptotic(String testName) {
        return asymptoticTests.contains(testName);
    }

}
