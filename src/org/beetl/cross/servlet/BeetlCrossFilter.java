package org.beetl.cross.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class BeetlCrossFilter
 */
@WebFilter("/BeetlCrossFilter")
public class BeetlCrossFilter implements Filter {

	FilterConfig fConfig ;
    public BeetlCrossFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if(isStaticTemplate(request)){
			processStaticTemplate(request,response);
			
		}else{
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;	
		
	}
	
	protected boolean isStaticTemplate(ServletRequest request){
		String path = fConfig.getInitParameter("path");
		String[] requestInfo = parsePath(request);
		String prefix = requestInfo[0];
		return path.equals(prefix);
	}
	
	protected void processStaticTemplate(ServletRequest request, ServletResponse response){
		
	}
	
	protected String[] parsePath(ServletRequest request){
		HttpServletRequest r = (HttpServletRequest)request;
		return null;
	}

}
