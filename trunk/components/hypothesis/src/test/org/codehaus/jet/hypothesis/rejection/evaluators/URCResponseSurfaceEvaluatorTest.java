package org.codehaus.jet.hypothesis.rejection.evaluators;

import org.codehaus.jet.hypothesis.rejection.ResponseSurfaceEvaluator;

/**
 * 
 * @author Mauro Talevi
 */
public class URCResponseSurfaceEvaluatorTest extends AbstractResponseSurfaceEvaluatorTestCase {
    
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testCanEvaluateSurface(){
        assertSurface(1.0, new double[]{1.0, 0.9, 0.8, 0.7}, 0, new int[]{2, 3});
        assertSurface(1.0183200, new double[]{1.0, 0.9, 0.8, 0.7}, 50, new int[]{2, 3});
        assertSurface(1.0183256, new double[]{1.0, 0.9, 0.8, 0.7}, 50, new int[]{3, 3});
        assertSurface(1.0195111, new double[]{1.0, 0.9, 0.8, 0.7}, 50, new int[]{4, 3});
        assertSurface(1.0195178, new double[]{1.0, 0.9, 0.8, 0.7}, 50, new int[]{5, 3});
    }

    public void testCanValidateParameters(){
        assertInvalidParams("beta must be have at least 4 coefficients", 
                new double[]{1.0, 0.9, 0.8}, 0, new int[]{2, 3});
        assertInvalidParams("sample size must be a non-negative integer", 
                new double[]{1.0, 0.9, 0.8, 0.7}, -1, new int[]{2, 3});
        assertInvalidParams("params must contain at least 2 parameters", 
                new double[]{1.0, 0.9, 0.8, 0.7}, 100, new int[]{2});
        assertInvalidParams("first parameter must be an integer between 2 and 5", 
                new double[]{1.0, 0.9, 0.8, 0.7}, 100, new int[]{1, 3});
    }    
        

    @Override
    protected ResponseSurfaceEvaluator createResponseSurfaceEvaluator() {
        return new URCResponseSurfaceEvaluator();
    }    

}
