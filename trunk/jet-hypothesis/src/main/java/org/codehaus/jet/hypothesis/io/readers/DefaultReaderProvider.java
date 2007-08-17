package org.codehaus.jet.hypothesis.io.readers;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jet.hypothesis.HypothesisTest;
import org.codehaus.jet.hypothesis.io.BetaReader;
import org.codehaus.jet.hypothesis.io.CriticalValueReader;
import org.codehaus.jet.hypothesis.io.ProbabilityReader;
import org.codehaus.jet.hypothesis.io.ReaderProvider;

/**
 * Default implementation of ReaderProvider. 
 * Uses simple caching via Maps.
 * 
 * @author Mauro Talevi
 */
public class DefaultReaderProvider implements ReaderProvider {

    private Map<String, AbstractReader> readers;

    /**
     * Creates DefaultReaderProvider with default readers
     */
    public DefaultReaderProvider(){
        this(createDefaultReaders());
    }

    /**
     * Creates DefaultReaderProvider with given readers
     * 
     * @param readers the cache of readers
     */
    public DefaultReaderProvider(Map<String, AbstractReader> readers) {
        this.readers = readers;
    }

    public ProbabilityReader getProbabilityReader() {
        return (ProbabilityReader)getReader(ProbabilityReader.class.getName());
    }

    public BetaReader getBetaReader(String testName) {
        return (BetaReader)getReader(testName);
    }

    public CriticalValueReader getCriticalValueReader(String testName) {
        return (CriticalValueReader)getReader(testName);
    }

    private Object getReader(String name) {
        Object reader = readers.get(name);
        if ( reader == null ) {
            throw new IllegalArgumentException("Reader not found for test name "+name);
        }
        return reader;
    }

    private static Map<String, AbstractReader> createDefaultReaders() {
        Map<String, AbstractReader> map = new HashMap<String, AbstractReader>();
        map.put(HypothesisTest.ECM.getName(), new ECMReader("em2002"));
        map.put(HypothesisTest.URC.getName(), new URCReader("mackinnon1996"));
        map.put(HypothesisTest.LRC.getName(), new LRCReader("mhm1999"));
        map.put(HypothesisTest.JOHANSEN.getName(), new JohansenReader("mhm1999"));
        map.put(ProbabilityReader.class.getName(), new ProbabilityReader());
        return map;
    }

}
