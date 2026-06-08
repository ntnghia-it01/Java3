package com.fpoly.java3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fpoly.java3.config.DatabaseConnection;
import com.fpoly.java3.entities.User;

public class UserDAO {
//	Trong DAO sẽ có những functions gì?
	
	public List<User> getList(){
		List<User> users = new ArrayList<User>();
		Connection connection = DatabaseConnection.connection();
		try {
			String sql = "SELECT * FROM users";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				User user = new User();
				int id = rs.getInt("id");
				String name = rs.getString("full_name");
				String email = rs.getString("email");
				String password = rs.getString("password_hash");
				String phone = rs.getString("phone");
				String avatar = rs.getString("avatar");
				int role = rs.getInt("role");
				boolean status = rs.getBoolean("status");
				user.setId(id);
				user.setFullName(name);
				user.setEmail(email);
				user.setPasswordHash(password);
				user.setPhone(phone);
				user.setAvatar(avatar);
				user.setRole(role);
				user.setStatus(status);
				
				users.add(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public boolean create(User user) {
		Connection connection = DatabaseConnection.connection();
		try {
			String insert = "INSERT INTO "
					+ "users(full_name, email, password_hash, phone, avatar, role, status) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)"; 
			
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, user.getFullName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPasswordHash());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getAvatar());
			statement.setInt(6, user.getRole());
			statement.setBoolean(7, user.isStatus());
			
			int check = statement.executeUpdate();
			
			connection.close();
			
			if(check == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		
	}
	
//	public boolean update(User user) {
//		
//	}
//	
//	public boolean delete(User user) {
//		
//	}
//	
//	public User getById(int id) {
//		
//	}
}
