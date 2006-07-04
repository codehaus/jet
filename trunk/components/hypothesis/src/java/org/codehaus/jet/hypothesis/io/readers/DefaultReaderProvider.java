package org.codehaus.jet.hypothesis.io.readers;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jet.hypothesis.io.BetaReader;
import org.codehaus.jet.hypothesis.io.CriticalValueReader;
import org.codehaus.jet.hypothesis.io.ProbabilityReader;
import org.codehaus.jet.hypothesis.io.ReaderProvider;
import org.codehaus.jet.hypothesis.testers.DefaultHypothesisTester;


public class DefaultReaderProvider implements ReaderProvider {

    private Map<String, AbstractReader> readers = new HashMap<String, AbstractReader>();
    
    public DefaultReaderProvider(){
        readers.put(DefaultHypothesisTester.ECM_TEST, new ECMReader("em2002"));
        readers.put(DefaultHypothesisTester.URC_TEST, new URCReader("mackinnon1996"));
        readers.put(DefaultHypothesisTester.LRC_TEST, new LRCReader("mhm1999"));
        readers.put(DefaultHypothesisTester.JOHANSEN_TEST, new JohansenReader("mhm1999"));
        readers.put(ProbabilityReader.class.getName(), new ProbabilityReader());
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

}
