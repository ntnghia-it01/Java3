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
	
	public boolean emailExist(String email) {
		Connection connection = DatabaseConnection.connection();
		try {
			String sql = "SELECT * FROM users WHERE email=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			
			ResultSet resultSet = statement.executeQuery();
			
			boolean check = resultSet.next();
			
			connection.close();
			
			return check;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean phoneExist(String phone) {
		
		Connection connection = DatabaseConnection.connection();
		try {
			String sql = "SELECT * FROM users WHERE phone=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, phone);
			
			ResultSet resultSet = statement.executeQuery();
			
			boolean check = resultSet.next();
			
			connection.close();
			
			return check;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(User user) {
		Connection connection = DatabaseConnection.connection();
		try {
			String sql = "UPDATE users SET "
					+ "full_name=?, email=?, password_hash=?, phone=?, avatar=?, role=?, status=? "
					+ "WHERE id=?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getFullName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPasswordHash());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getAvatar());
			statement.setInt(6, user.getRole());
			statement.setBoolean(7, user.isStatus());
			statement.setInt(8, user.getId());
			
			int check = statement.executeUpdate();
			
			connection.close();
			
			if (check == 0) {
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

	public boolean delete(int id) {
		Connection connection = DatabaseConnection.connection();
		try {
			String sql = "DELETE FROM users WHERE id=?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			int check = statement.executeUpdate();
			
			connection.close();
			
			if (check == 0) {
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

	public User getById(int id) {
		Connection connection = DatabaseConnection.connection();
		try {
			String sql = "SELECT * FROM users WHERE id=?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				User user = new User();
				
				user.setId(rs.getInt("id"));
				user.setFullName(rs.getString("full_name"));
				user.setEmail(rs.getString("email"));
				user.setPasswordHash(rs.getString("password_hash"));
				user.setPhone(rs.getString("phone"));
				user.setAvatar(rs.getString("avatar"));
				user.setRole(rs.getInt("role"));
				user.setStatus(rs.getBoolean("status"));
				
				connection.close();
				return user;
			}
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return null;
	}
}
