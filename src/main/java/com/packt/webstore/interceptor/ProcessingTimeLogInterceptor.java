package com.packt.webstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sun.istack.internal.logging.Logger;

public class ProcessingTimeLogInterceptor implements HandlerInterceptor 
{
	private static final Logger LOGGER=Logger.getLogger(ProcessingTimeLogInterceptor.class);
	
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception 
	{
		

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception 
	{
		String queryString=request.getQueryString()==null ? "" : "?" +request.getQueryString();
		String path=request.getRequestURL() +queryString;
		long startTime=(Long) request.getAttribute("startTime");
		long endTime=System.currentTimeMillis();
		LOGGER.info(String.format("%s millisecond taken to process the request %s.",(endTime-startTime), path));
		

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		long startTime=System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

}
