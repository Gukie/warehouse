package com.baijia.warehouse.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author gushu
 * @date 2018/04/03
 */
public class WarehouseFilter implements javax.servlet.Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.err.println("filter init, and only invoke once in whole life-cycle");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.err.println("invoke chain.doFilter before...");
		chain.doFilter(request, response);
		System.err.println("invoke chain.doFilter after...");
		
	}

	@Override
	public void destroy() {
		System.err.println("filter destroy, and only invoke once in whole life-cycle");
		
	}


}
