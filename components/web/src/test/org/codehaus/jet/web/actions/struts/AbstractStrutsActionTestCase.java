package org.codehaus.jet.web.actions.struts;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

/**
 * @author Mauro Talevi
 */
public abstract class AbstractStrutsActionTestCase extends MockObjectTestCase {

    protected String testSrcDir;
    
    public void setUp() throws Exception {
        setTestDir();
    }    
    
    protected void setTestDir() {
        testSrcDir =  System.getProperty("test.src.dir");
        if ( testSrcDir == null ){
            testSrcDir = "components/web/src/test/"; 
        } else if ( !testSrcDir.endsWith(File.separator) ){
            testSrcDir = testSrcDir + File.separator; 
        }        
    }
    
    protected HttpServletRequest mockHttpServletRequestWithMethod(String method) {
        Mock mock = mock(HttpServletRequest.class);
        mock.expects(atLeastOnce()).method(method);
        return (HttpServletRequest)mock.proxy();
    }

    protected HttpServletRequest mockHttpServletRequest() {
        Mock mock = mock(HttpServletRequest.class);
        return (HttpServletRequest)mock.proxy();
    }

    protected HttpServletResponse mockHttpServletResponse() {
        Mock mock = mock(HttpServletResponse.class);
        return (HttpServletResponse)mock.proxy();
    }

    @SuppressWarnings("serial")
    protected ActionMapping createActionMappingWithForward() {
        ActionMapping mapping = new ActionMapping() {
            public ActionForward findForward(String name){
                return new ActionForward(name);
            }           
        };
        return mapping;
    }


    @SuppressWarnings("serial")
    protected ActionMapping createActionMappingWithForwardAndParameter(final String param) {
        ActionMapping mapping = new ActionMapping() {
            public ActionForward findForward(String name){
                return new ActionForward(name);
            }           
            public String getParameter() {
                return param;
            }
        };
        return mapping;
    }
    
}
