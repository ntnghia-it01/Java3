package com.fpoly.java3.services;

import java.util.Date;

import com.fpoly.java3.beans.PostsFormBean;
import com.fpoly.java3.dao.PostsDAO;
import com.fpoly.java3.entities.Category;
import com.fpoly.java3.entities.Posts;
import com.fpoly.java3.entities.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class PostsServices {
	
	public boolean createPosts(PostsFormBean bean, Part image, HttpServletRequest request) {
		try {
//			Lưu ảnh vào project => Lấy được tên ảnh trong project 
			String ext = image.getContentType().split("/")[1]; 
			String fileName = String.valueOf(new Date().getTime()); 
			String path =  "/images/" + fileName + "." + ext;
			String pathContext = request.getServletContext().getRealPath(path);
			image.write(pathContext);
			
//			Chuyển dữ liệu từ Bean -> Entity
			Posts posts = new Posts();
			posts.setTitle(bean.getTitle());
			posts.setContent(bean.getContent());
			posts.setThumbnail(path);
			posts.setStatus(bean.getStatus());
			posts.setViewCount(0);
			
			Category category = new Category();
			category.setId(bean.getCategory());
			posts.setCategory(category);
			
			User user = new User();
//			Sẽ set tạm user id khi tạo bài viết là 1
//			Sau này xây dựng xong chức năng đăng nhập 
//			Sẽ lấy id user từ người dùng đang đăng nhập vào hệ thống
			user.setId(1);
			posts.setUser(user);
			
//			Gọi DAO để insert vào db
			PostsDAO postsDAO = new PostsDAO();
			return postsDAO.create(posts);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updatePosts(PostsFormBean bean, Part image, HttpServletRequest request, int id) {
		try {
//			Lưu ảnh vào project => Lấy được tên ảnh trong project 
			String ext = image.getContentType().split("/")[1]; 
			String fileName = String.valueOf(new Date().getTime()); 
			String path =  "/images/" + fileName + "." + ext;
			String pathContext = request.getServletContext().getRealPath(path);
			image.write(pathContext);
			
//			Chuyển dữ liệu từ Bean -> Entity
			Posts posts = new Posts();
			posts.setId(id);
			posts.setTitle(bean.getTitle());
			posts.setContent(bean.getContent());
			posts.setThumbnail(path);
			posts.setStatus(bean.getStatus());
			posts.setViewCount(0);
			
			Category category = new Category();
			category.setId(bean.getCategory());
			posts.setCategory(category);
			
			User user = new User();
//			Sẽ set tạm user id khi tạo bài viết là 1
//			Sau này xây dựng xong chức năng đăng nhập 
//			Sẽ lấy id user từ người dùng đang đăng nhập vào hệ thống
			user.setId(1);
			posts.setUser(user);
			
//			Gọi DAO để insert vào db
			PostsDAO postsDAO = new PostsDAO();
			return postsDAO.update(posts);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
