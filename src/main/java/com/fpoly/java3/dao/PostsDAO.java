package com.fpoly.java3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fpoly.java3.config.DatabaseConnection;
import com.fpoly.java3.entities.Category;
import com.fpoly.java3.entities.Posts;
import com.fpoly.java3.entities.User;

public class PostsDAO {

	public List<Posts> getList() {
		List<Posts> posts = new ArrayList<Posts>();
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "SELECT "
					+ "p.*, "
					+ "c.id AS category_id, c.name AS category_name, c.status AS category_status, "
					+ "u.id AS user_id, u.full_name, u.email, u.password_hash, u.phone, u.avatar, u.role, u.status AS user_status "
					+ "FROM posts p "
					+ "INNER JOIN categories c ON p.category_id = c.id "
					+ "INNER JOIN users u ON p.author_id = u.id";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getString("category_name"));
				category.setStatus(rs.getBoolean("category_status"));

				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setFullName(rs.getString("full_name"));
				user.setEmail(rs.getString("email"));
				user.setPasswordHash(rs.getString("password_hash"));
				user.setPhone(rs.getString("phone"));
				user.setAvatar(rs.getString("avatar"));
				user.setRole(rs.getInt("role"));
				user.setStatus(rs.getBoolean("user_status"));

				Posts post = new Posts();
				post.setId(rs.getInt("id"));
				post.setCategory(category);
				post.setUser(user);
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setThumbnail(rs.getString("thumbnail"));
				post.setViewCount(rs.getInt("view_count"));
				post.setStatus(rs.getInt("status"));

				posts.add(post);
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

		return posts;
	}

	public boolean create(Posts post) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "INSERT INTO posts(category_id, author_id, title, content, thumbnail, view_count, status) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, post.getCategory().getId());
			statement.setInt(2, post.getUser().getId());
			statement.setString(3, post.getTitle());
			statement.setString(4, post.getContent());
			statement.setString(5, post.getThumbnail());
			statement.setInt(6, post.getViewCount());
			statement.setInt(7, post.getStatus());

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

	public boolean update(Posts post) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "UPDATE posts SET "
					+ "category_id=?, author_id=?, title=?, content=?, thumbnail=?, view_count=?, status=? "
					+ "WHERE id=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, post.getCategory().getId());
			statement.setInt(2, post.getUser().getId());
			statement.setString(3, post.getTitle());
			statement.setString(4, post.getContent());
			statement.setString(5, post.getThumbnail());
			statement.setInt(6, post.getViewCount());
			statement.setInt(7, post.getStatus());
			statement.setInt(8, post.getId());

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
			String sql = "DELETE FROM posts WHERE id=?";

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

	public Posts getById(int id) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "SELECT "
					+ "p.*, "
					+ "c.id AS category_id, c.name AS category_name, c.status AS category_status, "
					+ "u.id AS user_id, u.full_name, u.email, u.password_hash, u.phone, u.avatar, u.role, u.status AS user_status "
					+ "FROM posts p "
					+ "INNER JOIN categories c ON p.category_id = c.id "
					+ "INNER JOIN users u ON p.author_id = u.id "
					+ "WHERE p.id=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getString("category_name"));
				category.setStatus(rs.getBoolean("category_status"));

				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setFullName(rs.getString("full_name"));
				user.setEmail(rs.getString("email"));
				user.setPasswordHash(rs.getString("password_hash"));
				user.setPhone(rs.getString("phone"));
				user.setAvatar(rs.getString("avatar"));
				user.setRole(rs.getInt("role"));
				user.setStatus(rs.getBoolean("user_status"));

				Posts post = new Posts();
				post.setId(rs.getInt("id"));
				post.setCategory(category);
				post.setUser(user);
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setThumbnail(rs.getString("thumbnail"));
				post.setViewCount(rs.getInt("view_count"));
				post.setStatus(rs.getInt("status"));

				connection.close();
				return post;
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