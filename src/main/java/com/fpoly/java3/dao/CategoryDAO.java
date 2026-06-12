package com.fpoly.java3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fpoly.java3.config.DatabaseConnection;
import com.fpoly.java3.entities.Category;

public class CategoryDAO {

	public List<Category> getList() {
		List<Category> categories = new ArrayList<Category>();
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "SELECT * FROM categories";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Category category = new Category();

				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setStatus(rs.getBoolean("status"));

				categories.add(category);
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

		return categories;
	}

	public boolean create(Category category) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "INSERT INTO categories(name, status) VALUES(?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, category.getName());
			statement.setBoolean(2, category.isStatus());

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

	public boolean update(Category category) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "UPDATE categories SET name=?, status=? WHERE id=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, category.getName());
			statement.setBoolean(2, category.isStatus());
			statement.setInt(3, category.getId());

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
			String sql = "DELETE FROM categories WHERE id=?";

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

	public Category getById(int id) {
		Connection connection = DatabaseConnection.connection();

		try {
			String sql = "SELECT * FROM categories WHERE id=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				Category category = new Category();

				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setStatus(rs.getBoolean("status"));

				connection.close();
				return category;
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