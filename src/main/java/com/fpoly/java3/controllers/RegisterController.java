package com.fpoly.java3.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterController extends HttpServlet{
	
//	GET
//	Dùng tìm kiếm ở DB 
//	Dùng để hiển thị giao diện 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Start doGet");
		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
	
//	POST 
//	Dùng để CRUD database 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Start doPOST");
		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
}
// PUT sửa 
// DELETE xoá
