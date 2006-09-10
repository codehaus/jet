package org.codehaus.jet.web.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter that validates there is a valid session active when the user request a path
 * 
 * @author Mauro Talevi
 */
public class SessionFilter implements Filter {

    /** The URL to redirect to in case there is no session active */
    private String redirectURL;
    /** The pathsL to ignore */
    protected Collection<String> ignorablePaths = new ArrayList<String>();

    public void init(FilterConfig filterConfig) throws ServletException {        
        this.redirectURL = filterConfig.getInitParameter("redirectURL");        
        if ( redirectURL == null ) {
            throw new ServletException("redirectURL is a mandatory init parameter for filter "+SessionFilter.class.getName());
        }
        String ignorePaths = filterConfig.getInitParameter("ignorePaths");
        if ( ignorePaths == null ) {
            throw new ServletException("ignorePaths is a mandatory init parameter for filter "+SessionFilter.class.getName());
        }
        addIgnorablePaths(ignorePaths);
    }
    
    /**
     * Adds the CSV paths to the list of comparable paths
     * @param csvPaths
     */
    protected void addIgnorablePaths(String csvPaths) {
        String[] paths = csvPaths.split(",");
        for (int i = 0; i < paths.length; i++) {
            ignorablePaths.add(paths[i]);
        }
    }

    public void destroy() {
        // no-op
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String requestedPath = request.getServletPath();        

        if ( isIgnorable(requestedPath) ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        if (request.getSession(false) == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(this.redirectURL);
            requestDispatcher.forward(servletRequest, servletResponse);
            return;
        }
        
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Determines if requested path is ignorable
     * @param requestedPath the requested path
     * @return A boolean <code>true</code> if it matches any part of ignorable paths
     */
    protected boolean isIgnorable(String requestedPath) {
        for ( Iterator i = ignorablePaths.iterator(); i.hasNext(); ){
            String path = (String)i.next();
            if ( match(path, requestedPath) ){
                return true;
            }
        }        
        return false;
    }

    /**
     * Determines if paths match
     * @param path1
     * @param path2
     * @return A boolean <code>true</code> if any part of paths match
     */
    private boolean match(String path1, String path2) {
        return ( path1.indexOf(path2) > -1 || path2.indexOf(path1) > -1 );
    }
    

}
