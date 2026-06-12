package com.fpoly.java3.controllers;

import java.io.IOException;

import com.fpoly.java3.dao.PostsDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/post-delete")
public class PostDeleteController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("id");
			PostsDAO postsDAO = new PostsDAO();
			postsDAO.delete(Integer.parseInt(id));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect(req.getContextPath() + "/admin/post-list");
	}
}

// Chức năng đặc biệt ở Servlet
// Xoá
// Sẽ không có giao diện => Không cần JSP 
// Sẽ không dùng phương thức GET để bảo mật dữ liệu => Không có doGet
