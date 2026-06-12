package com.fpoly.java3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fpoly.java3.config.DatabaseConnection;
import com.fpoly.java3.entities.Category;
import com.fpoly.java3.entities.Comment;
import com.fpoly.java3.entities.Posts;
import com.fpoly.java3.entities.User;

public class CommentDAO {

	public List<Comment> getList() {
		List<Comment> comments = new ArrayList<Comment>();
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "SELECT "
					+ "cm.id AS comment_id, cm.content AS comment_content, cm.status AS comment_status, "
					+ "u.id AS user_id, u.full_name, u.email, u.password_hash, u.phone, u.avatar, u.role, u.status AS user_status, "
					+ "p.id AS post_id, p.title, p.content AS post_content, p.thumbnail, p.view_count, p.status AS post_status, "
					+ "c.id AS category_id, c.name AS category_name, c.status AS category_status "
					+ "FROM comments cm "
					+ "INNER JOIN users u ON cm.user_id = u.id "
					+ "INNER JOIN posts p ON cm.news_id = p.id "
					+ "INNER JOIN categories c ON p.category_id = c.id";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Comment comment = mapComment(rs);
				comments.add(comment);
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

		return comments;
	}

	public List<Comment> getListByPostId(int postId) {
		List<Comment> comments = new ArrayList<Comment>();
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "SELECT "
					+ "cm.id AS comment_id, cm.content AS comment_content, cm.status AS comment_status, "
					+ "u.id AS user_id, u.full_name, u.email, u.password_hash, u.phone, u.avatar, u.role, u.status AS user_status, "
					+ "p.id AS post_id, p.title, p.content AS post_content, p.thumbnail, p.view_count, p.status AS post_status, "
					+ "c.id AS category_id, c.name AS category_name, c.status AS category_status "
					+ "FROM comments cm "
					+ "INNER JOIN users u ON cm.user_id = u.id "
					+ "INNER JOIN posts p ON cm.news_id = p.id "
					+ "INNER JOIN categories c ON p.category_id = c.id "
					+ "WHERE cm.news_id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, postId);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Comment comment = mapComment(rs);
				comments.add(comment);
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

		return comments;
	}

	public List<Comment> getListByUserId(int userId) {
		List<Comment> comments = new ArrayList<Comment>();
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "SELECT "
					+ "cm.id AS comment_id, cm.content AS comment_content, cm.status AS comment_status, "
					+ "u.id AS user_id, u.full_name, u.email, u.password_hash, u.phone, u.avatar, u.role, u.status AS user_status, "
					+ "p.id AS post_id, p.title, p.content AS post_content, p.thumbnail, p.view_count, p.status AS post_status, "
					+ "c.id AS category_id, c.name AS category_name, c.status AS category_status "
					+ "FROM comments cm "
					+ "INNER JOIN users u ON cm.user_id = u.id "
					+ "INNER JOIN posts p ON cm.news_id = p.id "
					+ "INNER JOIN categories c ON p.category_id = c.id "
					+ "WHERE cm.user_id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Comment comment = mapComment(rs);
				comments.add(comment);
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

		return comments;
	}

	public boolean create(Comment comment) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "INSERT INTO comments(user_id, news_id, content, status) VALUES(?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, comment.getUser().getId());
			statement.setInt(2, comment.getPosts().getId());
			statement.setString(3, comment.getContent());
			statement.setBoolean(4, comment.isStatus());

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

	public boolean update(Comment comment) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "UPDATE comments SET user_id=?, news_id=?, content=?, status=? WHERE id=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, comment.getUser().getId());
			statement.setInt(2, comment.getPosts().getId());
			statement.setString(3, comment.getContent());
			statement.setBoolean(4, comment.isStatus());
			statement.setInt(5, comment.getId());

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
			String sql = "DELETE FROM comments WHERE id = ?";

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

	public Comment getById(int id) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "SELECT "
					+ "cm.id AS comment_id, cm.content AS comment_content, cm.status AS comment_status, "
					+ "u.id AS user_id, u.full_name, u.email, u.password_hash, u.phone, u.avatar, u.role, u.status AS user_status, "
					+ "p.id AS post_id, p.title, p.content AS post_content, p.thumbnail, p.view_count, p.status AS post_status, "
					+ "c.id AS category_id, c.name AS category_name, c.status AS category_status "
					+ "FROM comments cm "
					+ "INNER JOIN users u ON cm.user_id = u.id "
					+ "INNER JOIN posts p ON cm.news_id = p.id "
					+ "INNER JOIN categories c ON p.category_id = c.id "
					+ "WHERE cm.id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				Comment comment = mapComment(rs);

				connection.close();
				return comment;
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

	private Comment mapComment(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setFullName(rs.getString("full_name"));
		user.setEmail(rs.getString("email"));
		user.setPasswordHash(rs.getString("password_hash"));
		user.setPhone(rs.getString("phone"));
		user.setAvatar(rs.getString("avatar"));
		user.setRole(rs.getInt("role"));
		user.setStatus(rs.getBoolean("user_status"));

		Category category = new Category();
		category.setId(rs.getInt("category_id"));
		category.setName(rs.getString("category_name"));
		category.setStatus(rs.getBoolean("category_status"));

		Posts posts = new Posts();
		posts.setId(rs.getInt("post_id"));
		posts.setCategory(category);
		posts.setUser(user);
		posts.setTitle(rs.getString("title"));
		posts.setContent(rs.getString("post_content"));
		posts.setThumbnail(rs.getString("thumbnail"));
		posts.setViewCount(rs.getInt("view_count"));
		posts.setStatus(rs.getInt("post_status"));

		Comment comment = new Comment();
		comment.setId(rs.getInt("comment_id"));
		comment.setUser(user);
		comment.setPosts(posts);
		comment.setContent(rs.getString("comment_content"));
		comment.setStatus(rs.getBoolean("comment_status"));

		return comment;
	}
}