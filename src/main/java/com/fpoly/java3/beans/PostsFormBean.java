package com.fpoly.java3.beans;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostsFormBean {
	private String title;
	private String content;
	private int category;
	private int status;
	
//	Lỗi ở bean class sẽ trả về đối tượng error 
//	1. Sử dụng Map<String, String> => đối tượng chứa error 
//	2. Sử dụng Object class 
//	class Error {
//		String errTitle;
//		String errContent;
//		String errCategory;
//		String errStatus;
//	}
	
	public Map<String, String> getErrors(){
		Map<String, String> errors = new HashMap<String, String>();
		
//		isEmpty
//		isBlank => trim + empty
		if(this.title.isBlank()) {
			errors.put("errTitle", "Tiêu đề không được bỏ trống");
		}else if(this.title.length() < 10) {
			errors.put("errTitle", "Tiêu đề không được ít hơn 10 ký tự");
		}
		
//		Nội dung không được bỏ trống => ["Nội", "dung"]
//		Nội dung phải có ít nhất 5 từ 
//		errors.put("errContent", "....");
		
		if(this.content.isBlank()) {
			errors.put("errContent", "Nội dung không được bỏ trống");
		}else if(this.content.trim().split(" ").length < 5) {
			errors.put("errContent", "Nội dung phải có ít nhất 5 từ");
		}
		
//		String ==> null 
//		int ==> 0
		
		if(this.category < 1) {
			errors.put("errCategory", "Chưa chọn danh mục");
		}
		
		if(this.status == 0) {
			errors.put("errStatus", "Chưa chọn trạng thái");
		}
		
		return errors;
	}
//	{errTitle: "Tiêu đề không được bỏ trống"} => OBJECT
}

// Tiêu đề không được bỏ trống
// Tiêu đề phải có ít nhất 10 ký tự
// Nội dung không được bỏ trống
// Nội dung phải có ít nhất 5 từ 
// Danh mục bắt buộc chọn
// Trạng thái bắt buộc chọn
