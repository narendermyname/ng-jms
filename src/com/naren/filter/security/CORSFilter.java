/*package com.naren.filter.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

*//**
 * Servlet Filter implementation class CORSFilter
 *//*
@WebFilter(description = "Filter to handle Same Origin Policy related issues", urlPatterns = { "/CORSFilter" })
public class CORSFilter implements Filter {

    *//**
     * Default constructor. 
     *//*
    public CORSFilter() {
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see Filter#destroy()
	 *//*
	public void destroy() {
		// TODO Auto-generated method stub
	}

	*//**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 *//*
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Filtering on...........................................................");
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	*//**
	 * @see Filter#init(FilterConfig)
	 *//*
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
*/