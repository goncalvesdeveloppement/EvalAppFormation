package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.FormationOrder;

public class FormationOrderDao implements Dao<FormationOrder> {

	@Override
	public boolean create(FormationOrder obj) {
		String sqlQuery = "INSERT INTO T_FormationOrders(IdFormation, IdOrder, Quantity) VALUES(?, ?, ?);";

		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setInt(1, obj.getIdFormation());
			ps.setInt(2, obj.getIdOrder());

			if (ps.executeUpdate() == 1) {
				
				// Avoir directement le nouvel ID mis à jour dans l'objet
				try (Statement statement = connection.createStatement()) {
					String str = "SELECT LAST_INSERT_ID() as id;";
					ResultSet rs = statement.executeQuery(str);

					if (rs.next())
						obj.setIdFormationOrder(rs.getInt(1));
				} catch (SQLException e) {
					logger.severe("pb sql sur la lecture d'une F/O " + e.getMessage());
				}
				
				return true;
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur la création d'une F/O");
		}

		return false;
	}

	@Override
	public FormationOrder read(int id) {
		try (Statement statement = connection.createStatement()) {
			String str = "SELECT * FROM T_FormationOrders where IdFormationOrder=" + id + ";";
			ResultSet rs = statement.executeQuery(str);

			if (rs.next())
				return new FormationOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'une F/O " + e.getMessage());
		}

		return null;
	}

	@Override
	public boolean update(FormationOrder obj) {
		String str = "UPDATE T_FormationOrders SET IdFormation = ?, IdOrder = ?, Quantity = ? WHERE idFormationOrder = " + obj.getIdFormationOrder() + ";";

		try (PreparedStatement ps = connection.prepareStatement(str)) {
			ps.setInt(1, obj.getIdFormation());
			ps.setInt(2, obj.getIdOrder());
			ps.setInt(3, obj.getQuantity());

			if (ps.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la mise à jour d'une F/O " + e.getMessage());
		}

		return false;
	}

	@Override
	public boolean delete(FormationOrder obj) {
		try (Statement statement = connection.createStatement()) {
			String str = "DELETE FROM T_FormationOrders where IdFormationOrder = " + obj.getIdFormationOrder() + ";";

			if (statement.executeUpdate(str) == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la suppression d'une F/O ");
		}

		return false;
	}

	@Override
	public ArrayList<FormationOrder> readAll() {
		ArrayList<FormationOrder> formationOrders = new ArrayList<FormationOrder>();
		String strSql = "SELECT * FROM T_FormationOrders";

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(strSql)) {
				while (resultSet.next()) {
					int rsIdFormationOrder = resultSet.getInt(1);
					int rsIdFormation = resultSet.getInt(2);
					int rsIdOrder = resultSet.getInt(3);
					int rsQuantity = resultSet.getInt(4);
					
					formationOrders.add(new FormationOrder(rsIdFormationOrder, rsIdFormation, rsIdOrder, rsQuantity));
				}
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur le renvoi de la liste des F/O");
		}
		return formationOrders;
	}

}
