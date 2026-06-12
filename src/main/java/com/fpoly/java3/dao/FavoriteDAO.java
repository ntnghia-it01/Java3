package com.fpoly.java3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fpoly.java3.config.DatabaseConnection;
import com.fpoly.java3.entities.Category;
import com.fpoly.java3.entities.Favorite;
import com.fpoly.java3.entities.Posts;
import com.fpoly.java3.entities.User;

public class FavoriteDAO {

	public List<Favorite> getList() {
		List<Favorite> favorites = new ArrayList<Favorite>();
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "SELECT "
					+ "f.id AS favorite_id, "
					+ "u.id AS user_id, u.full_name, u.email, u.password_hash, u.phone, u.avatar, u.role, u.status AS user_status, "
					+ "p.id AS post_id, p.title, p.content, p.thumbnail, p.view_count, p.status AS post_status, "
					+ "c.id AS category_id, c.name AS category_name, c.status AS category_status "
					+ "FROM favorites f "
					+ "INNER JOIN users u ON f.user_id = u.id "
					+ "INNER JOIN posts p ON f.news_id = p.id "
					+ "INNER JOIN categories c ON p.category_id = c.id";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Favorite favorite = mapFavorite(rs);
				favorites.add(favorite);
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

		return favorites;
	}

	public List<Favorite> getListByUserId(int userId) {
		List<Favorite> favorites = new ArrayList<Favorite>();
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "SELECT "
					+ "f.id AS favorite_id, "
					+ "u.id AS user_id, u.full_name, u.email, u.password_hash, u.phone, u.avatar, u.role, u.status AS user_status, "
					+ "p.id AS post_id, p.title, p.content, p.thumbnail, p.view_count, p.status AS post_status, "
					+ "c.id AS category_id, c.name AS category_name, c.status AS category_status "
					+ "FROM favorites f "
					+ "INNER JOIN users u ON f.user_id = u.id "
					+ "INNER JOIN posts p ON f.news_id = p.id "
					+ "INNER JOIN categories c ON p.category_id = c.id "
					+ "WHERE f.user_id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Favorite favorite = mapFavorite(rs);
				favorites.add(favorite);
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

		return favorites;
	}

	public boolean create(Favorite favorite) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "INSERT INTO favorites(user_id, news_id) VALUES(?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, favorite.getUser().getId());
			statement.setInt(2, favorite.getPosts().getId());

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
			String sql = "DELETE FROM favorites WHERE id = ?";

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

	public boolean deleteByUserAndPost(int userId, int postId) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "DELETE FROM favorites WHERE user_id = ? AND news_id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			statement.setInt(2, postId);

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

	public Favorite getById(int id) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "SELECT "
					+ "f.id AS favorite_id, "
					+ "u.id AS user_id, u.full_name, u.email, u.password_hash, u.phone, u.avatar, u.role, u.status AS user_status, "
					+ "p.id AS post_id, p.title, p.content, p.thumbnail, p.view_count, p.status AS post_status, "
					+ "c.id AS category_id, c.name AS category_name, c.status AS category_status "
					+ "FROM favorites f "
					+ "INNER JOIN users u ON f.user_id = u.id "
					+ "INNER JOIN posts p ON f.news_id = p.id "
					+ "INNER JOIN categories c ON p.category_id = c.id "
					+ "WHERE f.id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				Favorite favorite = mapFavorite(rs);

				connection.close();
				return favorite;
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

	public boolean favoriteExist(int userId, int postId) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "SELECT * FROM favorites WHERE user_id = ? AND news_id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			statement.setInt(2, postId);

			ResultSet rs = statement.executeQuery();

			boolean check = rs.next();

			connection.close();

			return check;

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return false;
	}

	private Favorite mapFavorite(ResultSet rs) throws SQLException {
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
		posts.setContent(rs.getString("content"));
		posts.setThumbnail(rs.getString("thumbnail"));
		posts.setViewCount(rs.getInt("view_count"));
		posts.setStatus(rs.getInt("post_status"));

		Favorite favorite = new Favorite();
		favorite.setId(rs.getInt("favorite_id"));
		favorite.setUser(user);
		favorite.setPosts(posts);

		return favorite;
	}
}