package com.riches.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;

public class LogMDCFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		String uuid = UUID.randomUUID().toString();
		Map<String,String> tags = new HashMap<String, String>();
		tags.put("requestUUID", uuid.replaceAll("-", "").toUpperCase());
		MDC.setContextMap(tags);//通过MDC标记Tags
		//		MDC.put("requestUUID", uuid.replaceAll("-", "").toUpperCase());
		try{
			filterChain.doFilter(request, response);
		} finally{
//			MDC.remove("requestUUID");
			MDC.clear();
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
