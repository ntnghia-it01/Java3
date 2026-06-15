package com.fpoly.java3.beans;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginBean {
	private String email;
	private String password;
	
	public Map<String, String> getErrors(){
		Map<String, String> errors = new HashMap<String, String>();
		
		if(!this.email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			errors.put("errEmail", "Email không đúng định dạng");
		}
		
		if(this.password.trim().length() < 6) {
			errors.put("errPassword", "Mật khẩu có tối thiểu 6 ký tự");
		}
		
		return errors;
	}
}
