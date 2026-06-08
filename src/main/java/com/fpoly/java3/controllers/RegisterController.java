package com.fpoly.java3.controllers;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.RegisterBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/register")
@MultipartConfig
public class RegisterController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			RegisterBean bean = new RegisterBean();
			BeanUtils.populate(bean, req.getParameterMap());
			
			req.setAttribute("bean", bean);
			
//			Nhận hình ảnh
			Part part = req.getPart("avatar");
//			Bắt buộc phải upload ảnh
//			File upload lên phải là ảnh ???
			if(part == null) {
//				Không có ảnh
				req.setAttribute("errImage", "Bắt buộc phải có ảnh");
			}else if(!part.getContentType().startsWith("image/")) {
				req.setAttribute("errImage", "File upload bắt buộc là ảnh");
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
}
