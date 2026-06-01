package com.fpoly.java3.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Thêm các thuộc tính vào class này
// - Họ và tên
// - Địa chỉ
// - SĐT
// Có hàm xây dựng không tham số
// Có hàm xây dựng đầy đủ tham số
// Có Getter và Setter 

// Nhận dữ liệu từ request của người dùng (Client)
// Kiểm tra các dữ liệu đầu vào từ phía người dùng (Client)

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserBean {
	private String name;
	private String address;
	private String phone;
}
