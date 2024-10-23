package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.User;

public class UserDao implements Dao<User> {

	@Override
	public boolean create(User obj) {
		String sqlQuery = "INSERT INTO T_Users(Login, Password, IsAdmin) VALUES(?, ?, ?);";

		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setString(1, obj.getLoginUser());
			ps.setString(2, obj.getPassword());
			ps.setBoolean(3, obj.isAdmin());

			if (ps.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la création d'un user");
		}

		return false;
	}

	@Override
	public User read(int id) {
		try (Statement statement = connection.createStatement()) {
			String str = "SELECT * FROM T_Users where IdUser = " + id + ";";
			ResultSet rs = statement.executeQuery(str);

			if (rs.next())
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'un user " + e.getMessage());
		}

		return null;
	}

	@Override
	public boolean update(User obj) {
		String str = "UPDATE T_Users set Login = ?, Password = ?, IsAdmin = ? WHERE IdUser = " + obj.getIdUser() + ";";

		try (PreparedStatement ps = connection.prepareStatement(str)) {
			ps.setString(1, obj.getLoginUser());
			ps.setString(2, obj.getPassword());
			ps.setBoolean(3, obj.isAdmin());

			if (ps.executeUpdate(str) == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la mise à jour d'un user " + e.getMessage());
		}

		return false;
	}

	@Override
	public boolean delete(User obj) {
		try (Statement statement = connection.createStatement()) {
			String str = "DELETE FROM T_Users where IdUser = " + obj.getIdUser() + ";";

			if (statement.executeUpdate(str) == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la suppression d'un user ");
		}

		return false;
	}

	@Override
	public ArrayList<User> readAll() {
		ArrayList<User> users = new ArrayList<User>();
		String strSql = "SELECT * FROM T_Users";

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(strSql)) {
				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsLogin = resultSet.getString(2);
					String rsPassword = resultSet.getString(3);
					boolean rsIsAdmin = resultSet.getBoolean(4);

					users.add(new User(rsId, rsLogin, rsPassword, rsIsAdmin));
				}
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur le renvoi de la liste des utilisateurs");
		}
		return users;
	}

	public User findUserByCredentials(String login, String password) {
		String str = "SELECT * FROM T_Users where Login = ? and Password = ?;";
		try (PreparedStatement ps = connection.prepareStatement(str)) {
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
		} catch (SQLException e) {
			logger.severe("pb sql sur renvoi d'un utilisateur à partir des credentials ");
		}
		return null;
	}

	public User findUserByLogin(String login) {
		String str = "SELECT * FROM T_Users where Login = ?;";
		try (PreparedStatement ps = connection.prepareStatement(str)) {
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
		} catch (SQLException e) {
			logger.severe("pb sql sur renvoi d'un utilisateur à partir du login ");
		}
		return null;
	}
}
