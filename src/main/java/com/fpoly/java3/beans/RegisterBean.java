package com.fpoly.java3.beans;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterBean {
	private String email;
	private String password;
	private String name;
	private String phone;
	
	public Map<String, String> getErrors(){
		Map<String, String> errors = new HashMap<String, String>();
		
		if(!this.email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			errors.put("errEmail", "Email không đúng định dạng");
		}
		
		if(this.password.trim().length() < 6) {
			errors.put("errPassword", "Mật khẩu có tối thiểu 6 ký tự");
		}
		
		if(this.name.isBlank()) {
			errors.put("errName", "Tên không được bỏ trống");
		}
		
		if(!this.phone.matches("^0\\d{9}$")) {
			errors.put("errPhone", "Số điện thoại không đúng định dạng");
		}
		
		return errors;
	}
} 