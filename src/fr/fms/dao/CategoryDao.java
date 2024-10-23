package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Category;

public class CategoryDao implements Dao<Category> {

	@Override
	public boolean create(Category obj) {
		String sqlQuery = "INSERT INTO T_Categories(Title) VALUES(?);";

		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setString(1, obj.getTitle());

			if (ps.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la création d'une catégorie");
		}

		return false;
	}

	@Override
	public Category read(int id) {
		try (Statement statement = connection.createStatement()) {
			String str = "SELECT * FROM T_Categories where IdCategory=" + id + ";";
			ResultSet rs = statement.executeQuery(str);

			if (rs.next())
				return new Category(rs.getInt(1), rs.getString(2));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'une catégorie " + e.getMessage());
		}

		return null;
	}

	@Override
	public boolean update(Category obj) {
		String str = "UPDATE T_Categories set Title = ? WHERE idFormation=" + obj.getIdCategory() + ";";
		try (PreparedStatement ps = connection.prepareStatement(str)) {
			ps.setString(1, obj.getTitle());

			if (ps.executeUpdate(str) == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la mise à jour d'une catégorie " + e.getMessage());
		}

		return false;
	}

	@Override
	public boolean delete(Category obj) {
		try (Statement statement = connection.createStatement()) {
			String str = "DELETE FROM T_Categories where IdUser=" + obj.getIdCategory() + ";";

			if (statement.executeUpdate(str) == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la suppression d'une catégorie ");
		}

		return false;
	}

	@Override
	public ArrayList<Category> readAll() {
		ArrayList<Category> categories = new ArrayList<Category>();
		String strSql = "SELECT * FROM T_Categories";

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(strSql)) {
				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsTitle = resultSet.getString(2);

					categories.add(new Category(rsId, rsTitle));
				}
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur le renvoi de la liste des utilisateurs");
		}
		return categories;
	}

}
