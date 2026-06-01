package com.fpoly.java3.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fpoly.java3.beans.UserBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Mỗi controller tương ứng với 1 page của website
// Mỗi controller sẽ được quản lý thông qua 1 url riêng biệt
// http://localhost:8080/home
// url đăng ký bên trong @WebServlet phải là duy nhất và không phân biệt HOA thường trong cả project
@WebServlet("/home")
public class HomeController extends HttpServlet{
//	Phương thức GET của website
//	URL gõ ở các browser đều gọi đến phương thức GET 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		setAttribute là model để gửi dữ liệu từ C => V
//		Key, value 
//		Key => Kiểu String => Là 1 biến (Quy tắc đặt key như đặt tên biến) 
//		Value => Kiểu Object 	
		req.setAttribute("name", "Nguyễn Văn A");
		
		req.setAttribute("age", 20);
		
		List<Integer> points = new ArrayList<Integer>();
		points.add(10); // index == 0
		points.add(7);
		points.add(3);
		req.setAttribute("points", points);
		
		for(int item : points) {
//			item tương ứng với giá trị của var 
		}
		
//		<c:forEach items="${keys}" var="abc">
		
//		split => Chuyển chuỗi thành mảng được cắt từ ký tự 
//		"   Nguyễn    Văn A   ".split(" ") => ["Nguyễn", "Văn", "A"]
//		join => Chuyển mảng thì chuỗi và nối vào ký tự quy định 
//		["Nguyễn", "Văn", "A"].join(" ") => "Nguyễn Văn A"
//		["Nguyễn", "Văn", "A"].join("-") => "Nguyễn-Văn-A"
		
//		<c:url value="/home" var="homeURL">
//		Java3 là tên project 
//		String homeURL = "Java3/home";
		
//		Map<Kiểu dữ liệu key (tên biến), Kiểu dữ liệu của value>
		Map<String, String> student = new HashMap<String, String>();
//		Map tương ứng với object 
		student.put("code", "PC12345");
		student.put("pointJava", "10");
//		Nếu biết được số lượng thuộc tính => Object
//		Không biết số lượng thuộc tính => Map 
		req.setAttribute("student", student);
		
//		Khởi tạo đối tượng User 
//		Gán các giá trị vào thuộc tính của đối tượng user 
		UserBean user = new UserBean();
		user.setName("Nguyen Van A");
		user.setAddress("Can Tho");
		user.setPhone("0123456789");
		req.setAttribute("user", user);
//		Thuộc tính hiện thị ở JSP là đang hiển thị 
//		bằng phương thức Getter 
//		${user.address} => user.getAddress()
//		loại bỏ chữ "get", phần còn lại sẽ chuyển về đúng chuẩn tên biến 
		
		
//		Hiển thị giao diện ở file home.jsp khi truy cập vào url http://localhost:8080/home
//		Dòng này luôn nằm cuối func và trước dấu ngoặc đóng của func 
		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}
}

// Trong website sẽ có 4 phương thức Http Method
// GET
// POST
// PUT
// DELETE