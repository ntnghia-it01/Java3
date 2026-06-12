package com.fpoly.java3.controllers;

import java.io.IOException;
import java.util.List;

import com.fpoly.java3.dao.PostsDAO;
import com.fpoly.java3.entities.Posts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/post-list")
public class PostListController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PostsDAO postsDAO = new PostsDAO();
		List<Posts> posts = postsDAO.getList();
		
		req.setAttribute("posts", posts);
		
		req.getRequestDispatcher("/posts-list.jsp").forward(req, resp);
	}

}
