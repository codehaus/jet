package org.codehaus.jet.hypothesis;

/**
 * Enumeration of available hypothesis tests.
 * 
 * @author Mauro Talevi
 */
public enum HypothesisTest {

    URC("URC"), 
    ECM("ECM"), 
    LRC("LRC"), 
    JOHANSEN("Johansen");

    private final String name;

    HypothesisTest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static HypothesisTest getTest(String name) {
        for (HypothesisTest test : HypothesisTest.values()) {
            if (test.getName().equals(name)) {
                return test;
            }
        }
        throw new IllegalArgumentException("No test found for name " + name);
    }

    public String toString() {
        return name;
    }

}
