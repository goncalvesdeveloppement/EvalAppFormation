package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Formation;

public class FormationDao implements Dao<Formation> {

	@Override
	public boolean create(Formation obj) {
		String sqlQuery = "INSERT INTO T_Formations(Name, Description, Duration, IsRemoteWork, Price, IdCategory) VALUES(?, ?, ?, ?, ?, ?);";

		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getDescription());
			ps.setInt(3, obj.getDuration());
			ps.setBoolean(4, obj.isRemoteWork());
			ps.setDouble(5, obj.getPrice());
			ps.setInt(6, obj.getIdCategory());

			if (ps.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la création d'une formation");
		}

		return false;
	}

	@Override
	public Formation read(int id) {
		try (Statement statement = connection.createStatement()) {
			String str = "SELECT * FROM T_Formations where IdFormation=" + id + ";";
			ResultSet rs = statement.executeQuery(str);

			if (rs.next())
				return new Formation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5),
						rs.getDouble(6), rs.getInt(6));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'une formation " + e.getMessage());
		}

		return null;
	}

	@Override
	public boolean update(Formation obj) {
		String str = "UPDATE T_Formations set Name = ?, Description = ?, Duration = ?, IsRemoteWork = ?, Price = ?, IdCategory = ? WHERE idFormation=" + obj.getIdFormation() + ";";

		try (PreparedStatement ps = connection.prepareStatement(str)) {
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getDescription());
			ps.setInt(3, obj.getDuration());
			ps.setBoolean(4, obj.isRemoteWork());
			ps.setDouble(5, obj.getPrice());
			ps.setInt(6, obj.getIdCategory());
			
			if (ps.executeUpdate(str) == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la mise à jour d'une formation " + e.getMessage());
		}

		return false;
	}

	@Override
	public boolean delete(Formation obj) {
		try (Statement statement = connection.createStatement()) {
			String str = "DELETE FROM T_Formations where IdUser=" + obj.getIdFormation() + ";";

			if (statement.executeUpdate(str) == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la suppression d'une formation ");
		}

		return false;
	}

	@Override
	public ArrayList<Formation> readAll() {
		ArrayList<Formation> formations = new ArrayList<Formation>();
		String strSql = "SELECT * FROM T_Formations";

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(strSql)) {
				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsName = resultSet.getString(2);
					String rsDescription = resultSet.getString(3);
					int rsDuration = resultSet.getInt(4);
					boolean rsRemoteWork = resultSet.getBoolean(5);
					double rsPrice = resultSet.getDouble(6);
					int rsIdCategory = resultSet.getInt(7);

					formations.add((new Formation(rsId, rsName, rsDescription, rsDuration, rsRemoteWork, rsPrice,
							rsIdCategory)));
				}
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur le renvoi de la liste des utilisateurs");
		}
		return formations;
	}
}
