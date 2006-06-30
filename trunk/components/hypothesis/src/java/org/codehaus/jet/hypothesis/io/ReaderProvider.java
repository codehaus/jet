package org.codehaus.jet.hypothesis.io;

public interface ReaderProvider {

    ProbabilityReader getProbabilityReader();

    BetaReader getBetaReader(String testName);

    CriticalValueReader getCriticalValueReader(String testName);
}
