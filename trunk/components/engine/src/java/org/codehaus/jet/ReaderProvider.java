package org.codehaus.jet;

import org.codehaus.jet.io.BetaReader;
import org.codehaus.jet.io.CriticalValueReader;
import org.codehaus.jet.io.ProbabilityReader;

public interface ReaderProvider {

    ProbabilityReader getProbabilityReader();

    BetaReader getBetaReader(String testName);

    CriticalValueReader getCriticalValueReader(String testName);
}
