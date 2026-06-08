package com.fpoly.java3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
	private int id;
    private User user;
    private Posts posts;
    private int content;
    private boolean status;
}
