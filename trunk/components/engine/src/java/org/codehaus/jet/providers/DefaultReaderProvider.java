package org.codehaus.jet.providers;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jet.ReaderProvider;
import org.codehaus.jet.engines.DefaultJetEngine;
import org.codehaus.jet.io.BetaReader;
import org.codehaus.jet.io.CriticalValueReader;
import org.codehaus.jet.io.ECMReader;
import org.codehaus.jet.io.JohansenReader;
import org.codehaus.jet.io.LRCReader;
import org.codehaus.jet.io.ProbabilityReader;
import org.codehaus.jet.io.URCReader;

public class DefaultReaderProvider implements ReaderProvider {

    private Map readers = new HashMap();
    
    public DefaultReaderProvider(){
        readers.put(DefaultJetEngine.ECM_TEST, new ECMReader("em2002"));
        readers.put(DefaultJetEngine.URC_TEST, new URCReader("mackinnon1996"));
        readers.put(DefaultJetEngine.LRC_TEST, new LRCReader("mhm1999"));
        readers.put(DefaultJetEngine.JOHANSEN_TEST, new JohansenReader("mhm1999"));
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
