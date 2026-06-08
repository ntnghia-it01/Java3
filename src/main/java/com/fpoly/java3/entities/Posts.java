package com.fpoly.java3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Posts {
	private int id;
    private Category category; // khoá ngoại category_id
//    Tránh trường hợp bị lỗi N+1 Query 
    private User user;
    private String title;
    private String content;
    private String thumbnail;
    private int viewCount;

    /**
     * status:
     * 1 - Pending
     * 2 - Daft // Chỉ hiện thị ở trang quản lý bài viết của user sở hữu 
     * 3 - Published
     */
    private int status;
}
