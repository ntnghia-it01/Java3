package com.fpoly.java3.config;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter({"/user/*", "/editor/*", "/admin/*"})
public class AuthFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		Cookie[] cookies = req.getCookies();
		
		if(cookies == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		String userId = null, role = null;
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("userId")) {
				userId = cookie.getValue();
			}
			if(cookie.getName().equals("role")) {
				role = cookie.getValue();
			}
		}
		
		if(userId == null || role == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
//		Đường đẫn mà browser đang truy cập
//		http://localhost:8080/Java3/admin/post-list => /admin/post-list 
		String path = req.getServletPath();
		System.out.println(path);
		
//		Role 1 => User: /user/**
//		Role 2 => Editor: /editor/**
//		Role 3 => Admin: /admin/**
		
		if(path.startsWith("/admin/") && !role.equals("3")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		if(path.startsWith("/editor/") && !role.equals("2")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		if(path.startsWith("/user/") && (!role.equals("2") && !role.equals("1"))) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
//		Để chạy tiếp đến controller
		chain.doFilter(request, response);
	}

}
