package com.fpoly.java3.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.PostsFormBean;
import com.fpoly.java3.dao.CategoryDAO;
import com.fpoly.java3.entities.Category;
import com.fpoly.java3.services.PostsServices;

/**
 * Servlet implementation class PostFormController
 */
@WebServlet("/admin/posts")
@MultipartConfig  // Nhận dữ liệu file được submit 
public class PostFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getList();
		request.setAttribute("categories", categories);
		
		request.getRequestDispatcher("/post-form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getList();
		request.setAttribute("categories", categories);
		
		PostsFormBean bean = new PostsFormBean();
		try {
			BeanUtils.populate(bean, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		Part image = request.getPart("image");

		request.setAttribute("bean", bean);
		
		if(bean.getErrors().isEmpty()) {
			PostsServices postsServices = new PostsServices();
			boolean insert = postsServices.createPosts(bean, image, request);
			if(insert) {
				System.out.println("Thêm bài viết thành công");
			}else {
				request.setAttribute("error", "Thêm bài viết thất bại");
			}
		}
		
		request.getRequestDispatcher("/post-form.jsp").forward(request, response);
	}

}

// Người dùng truy cập url ở browser => doGet render ra giao diện 
// => Submit => doPost nhận được các giá trị mà người dùng nhập 
// => Gửi dữ liệu từ doPost qua view bằng model => Render lại giao diện 
