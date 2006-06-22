package org.codehaus.jet.web.filters;

import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

/**
 * @author Mauro Talevi
 */
public class SessionFilterTest extends MockObjectTestCase {

    private SessionFilter filter;
    
    public void setUp(){ 
        filter = new SessionFilter();        
    }
    
    public void testInitWithIgnorablePaths() throws Exception {
        filter.init(mockFilterConfig("redirectURL1", "actionPath1,actionPath2"));
        assertEquals(Arrays.asList(new String[]{"actionPath1","actionPath2"}).toString(),filter.ignorablePaths.toString());
    }

    public void testInitWithNoInitParameters() throws Exception {
        try {
            filter.init(mockFilterConfig(null,"actionPath1"));
        } catch ( ServletException e) {
            assertEquals("redirectURL is a mandatory init parameter for filter "+SessionFilter.class.getName(), e.getMessage());
        }
        try {
            filter.init(mockFilterConfig("redirectURL1", null));
        } catch ( ServletException e) {
            assertEquals("ignorePaths is a mandatory init parameter for filter "+SessionFilter.class.getName(), e.getMessage());
        }
    }
    
    public void testDestroy() throws Exception {
        filter.destroy();
        // no-op method
    }

    public void testIsIgnorable() throws Exception {
        filter.init(mockFilterConfig("redirectURL1", "actionPath1,actionPath2"));
        assertTrue(filter.isIgnorable("actionPath1"));
        assertTrue(filter.isIgnorable("actionPath2"));
        assertFalse(filter.isIgnorable("actionPath3"));
    }

    public void testDoFilterWithIgnorablePath() throws Exception {
        filter.init(mockFilterConfig("redirectURL1", "actionPath1,actionPath2"));
        filter.doFilter( mockRequestForIgnorablePath("actionPath1"), mockResponse(), mockFilterChain(true));
    }

    public void testDoFilterWithNotIgnorablePathAndNullSession() throws Exception {
        filter.init(mockFilterConfig("redirectURL1", "actionPath1,actionPath2"));
        filter.doFilter( mockRequestWithNullSession("actionPath3", "redirectURL1"), mockResponse(), mockFilterChain(false));
    }

    public void testDoFilterWithNotIgnorablePathAndSession() throws Exception {
        filter.init(mockFilterConfig("redirectURL1", "actionPath1,actionPath2"));
        filter.doFilter( mockRequestWithSession("actionPath3", "redirectURL1"), mockResponse(), mockFilterChain(true));
    }
    
    private HttpServletRequest mockRequestForIgnorablePath(String requestedPath) {
        Mock mock = mock(HttpServletRequest.class);
        mock.expects(once()).method("getServletPath").withNoArguments().will(returnValue(requestedPath));
        return (HttpServletRequest)mock.proxy();
    }

    private HttpServletRequest mockRequestWithNullSession(String requestedPath, String redirectURL) {        
        Mock mock = mock(HttpServletRequest.class);
        mock.expects(once()).method("getServletPath").withNoArguments().will(returnValue(requestedPath));
        mock.expects(once()).method("getSession").withAnyArguments().will(returnValue(null));
        mock.expects(once()).method("getRequestDispatcher").with(eq(redirectURL)).will(returnValue(mockDispatcher()));
        return (HttpServletRequest)mock.proxy();
    }

    private HttpServletRequest mockRequestWithSession(String requestedPath, String redirectURL) {        
        Mock mock = mock(HttpServletRequest.class);
        mock.expects(once()).method("getServletPath").withNoArguments().will(returnValue(requestedPath));
        mock.expects(once()).method("getSession").withAnyArguments().will(returnValue(mockSession()));
        return (HttpServletRequest)mock.proxy();
    }
    
    private RequestDispatcher mockDispatcher() {
        Mock mock = mock(RequestDispatcher.class);
        mock.expects(once()).method("forward").withAnyArguments();
        return (RequestDispatcher)mock.proxy();
    }

    private HttpSession mockSession() {
        Mock mock = mock(HttpSession.class);
        return (HttpSession)mock.proxy();
    }

    private HttpServletResponse mockResponse() {
        Mock mock = mock(HttpServletResponse.class);
        return (HttpServletResponse)mock.proxy();
    }
    
    private FilterChain mockFilterChain(boolean doFilter){
        Mock mock = mock(FilterChain.class);
        if ( doFilter ){
            mock.expects(atLeastOnce()).method("doFilter").withAnyArguments();
        }
        return (FilterChain)mock.proxy();
    }
    
    private FilterConfig mockFilterConfig(String redirectURL, String ignorePaths) {
        Mock mock = mock(FilterConfig.class);
        mock.expects(once()).method("getInitParameter").with(eq("redirectURL")).will(returnValue(redirectURL));
        if ( redirectURL != null ){
            mock.expects(once()).method("getInitParameter").with(eq("ignorePaths")).will(returnValue(ignorePaths));            
        }
        return (FilterConfig)mock.proxy();
    }

}
