package org.codehaus.jet.hypothesis.io;

/**
 * Provider of readers of data required for different tests
 *
 * @author Mauro Talevi
 */
public interface ReaderProvider {

    ProbabilityReader getProbabilityReader();

    BetaReader getBetaReader(String testName);

    CriticalValueReader getCriticalValueReader(String testName);

}
