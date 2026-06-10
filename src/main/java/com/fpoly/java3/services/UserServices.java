package com.fpoly.java3.services;

import java.io.IOException;
import java.util.Date;

import com.fpoly.java3.beans.RegisterBean;
import com.fpoly.java3.dao.UserDAO;
import com.fpoly.java3.entities.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class UserServices {
// Đối với các functions chỉ 1 TH xảy ra lỗi => sẽ return về kiểu boolean
// Đối với các functions có từ 2 TH lỗi trở lên => sẽ return về String 
// - Khi có lỗi thì functions sẽ trả về nội dung lỗi để Controller gửi dữ liệu 
//   hiển thị lên view
// - Nếu không có lỗi sẽ return về null;
	
	
	
	
//	Kiểm tra email và số điện thoại có tồn tại trong db không? 
//	Nếu không có => Insert 
//	Ở phương thức này có bao nhiêu TH phát sinh lỗi cần thông báo cho client?
//	3 TH lỗi:
//	- Email đã tồn tại
//	- SĐT đã tồn tại
//	- Đăng ký thất bại 
	public String regsiter(RegisterBean bean, Part image, HttpServletRequest request) {
		UserDAO userDAO = new UserDAO();
		if(userDAO.emailExist(bean.getEmail())) {
			return "Email đã tồn tại";
		}
		if(userDAO.phoneExist(bean.getPhone())) {
			return "Số điện thoại đã tồn tại";
		}
//		Lưu ảnh vào project để lấy tên ảnh		
		String ext = image.getContentType().split("/")[1]; 
		String fileName = String.valueOf(new Date().getTime()); 
		String path =  "/images/" + fileName + "." + ext;
		String pathContext = request.getServletContext().getRealPath(path);
		try {
			image.write(pathContext);
		} catch (IOException e) {
			e.printStackTrace();
			return "Lưu ảnh không thành công";
		}
//		Chuyển dữ liệu từ Bean => Entity 
		User user = new User();
		user.setEmail(bean.getEmail());
		user.setFullName(bean.getName());
		user.setPasswordHash(bean.getPassword());
		user.setPhone(bean.getPhone());
		user.setAvatar(path);
		user.setRole(1);
		user.setStatus(true);
//		Insert vào DB => true insert thành công
//		false => Thất bại 
//		Gọi functions insert ở DAO 
		if(userDAO.create(user)) {
			return null;
		}else {
			return "Đăng ký thất bại";
		}
	}
}
