package com.ontime.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ontime.util.UserLocator;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();

		if (uri.endsWith("account/login") || uri.endsWith("account/register") || uri.contains("resources")) {
			return true;
		}
		
		UserLocator locator = new UserLocator(request.getSession());

		if (locator.getCurrentUser() != null) {
			return true;
		}

		response.sendRedirect(request.getContextPath() + "/account/login");
		return false;
	}

}
