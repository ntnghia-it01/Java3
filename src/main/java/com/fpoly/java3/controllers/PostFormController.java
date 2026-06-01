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
import com.fpoly.java3.entities.Category;

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
//		Khởi tạo 1 danh sách danh mục với 5 danh mục ngẫu nhiên
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(1, "Danh mục 1", false));
		categories.add(new Category(2, "Danh mục 2", false));
		categories.add(new Category(3, "Danh mục 3", false));
		categories.add(new Category(4, "Danh mục 4", false));
		categories.add(new Category(5, "Danh mục 5", false));
		
//		Gửi dữ liệu từ Controller qua View 
		request.setAttribute("categories", categories);
		
//		Lấy dữ liệu query params ở url
//		key=value&key=value
//		http://localhost:8080/Java3/admin/posts?title=Ti%C3%AAu+%C4%91%E1%BB%81&content=N%E1%BB%99i+dung&category=4&status=2
		
		
		request.getRequestDispatcher("/post-form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(1, "Danh mục 1", false));
		categories.add(new Category(2, "Danh mục 2", false));
		categories.add(new Category(3, "Danh mục 3", false));
		categories.add(new Category(4, "Danh mục 4", false));
		categories.add(new Category(5, "Danh mục 5", false));
		
//		Gửi dữ liệu từ Controller qua View 
		request.setAttribute("categories", categories);
		
		PostsFormBean bean = new PostsFormBean();
		try {
			BeanUtils.populate(bean, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		BeanUtils chỉ hỗ trợ chuyển đổi dữ liệu nguyên thuỷ 
		
//		Lấy thông tin của file khi submit 
		Part image = request.getPart("image");
		
		System.out.println(image.getContentType()); // Kiểu của file 
		System.out.println(image.getSize()); // Kích thước của file byte 
//		1kb == 1024 byte
//		1 byte == 8 bit
		System.out.println(image.getSubmittedFileName()); // Tên file lưu trên máy user
		
//		Mỗi lần lưu ảnh sẽ có 1 tên ảnh mới khác hoàn toàn với các ảnh đã có ???
//		Cắt phần định dạng file từ getContentType => image/png, image/jpg,...
//		image/png => split("/") => ["image", "png"]
		String ext = image.getContentType().split("/")[1]; 
		// Thời gian hiện tại trừ năm 1900 || 1970 -> đổi qua ms 
		String fileName = String.valueOf(new Date().getTime()); 
		String path =  "/images/" + fileName + "." + ext;
		String pathContext = request.getServletContext().getRealPath(path);
		image.write(pathContext);
		
		System.out.println(pathContext);
		
		request.setAttribute("bean", bean);
		
		request.getRequestDispatcher("/post-form.jsp").forward(request, response);
	}

}

// Người dùng truy cập url ở browser => doGet render ra giao diện 
// => Submit => doPost nhận được các giá trị mà người dùng nhập 
// => Gửi dữ liệu từ doPost qua view bằng model => Render lại giao diện 
