package com.fpoly.java3.controllers;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.LoginBean;
import com.fpoly.java3.services.UserServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Thực hiện lấy giá trị ở cookie ra 
//		Và kiểm tra nếu có userID và role thì sẽ mở trang home nếu không có thì cho đăng nhập 
		
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			String userId = null, role = null;
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("userId")) {
					userId = cookie.getValue();
				}
				if(cookie.getName().equals("role")) {
					role = cookie.getValue();
				}
			}
			
			if(userId != null && role != null) {
				resp.sendRedirect(req.getContextPath() + "/home");
				return;
			}
		}
		
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			LoginBean bean = new LoginBean();
			BeanUtils.populate(bean, req.getParameterMap());
			
			req.setAttribute("bean", bean);
			
			if(bean.getErrors().isEmpty()) {
				UserServices userServices = new UserServices();
				boolean loginCheck = userServices.login(bean, resp); 
				if(loginCheck) {
					resp.sendRedirect(req.getContextPath() + "/home");
					return;
				}else {
					req.setAttribute("errLogin", "Sai email hoặc mật khẩu");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
}
