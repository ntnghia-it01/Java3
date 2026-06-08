package com.fpoly.java3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private int id;
    private String fullName;
    private String email;
    private String passwordHash;
    private String phone;
    private String avatar;

    /**
     * role:
     * 1 - User
     * 2 - Editor
     * 3 - Admin
     */
    private int role;
    private boolean status;
}
