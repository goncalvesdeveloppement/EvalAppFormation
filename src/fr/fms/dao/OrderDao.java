package fr.fms.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Order;

public class OrderDao implements Dao<Order> {

	@Override
	public boolean create(Order obj) {
		String sqlQuery = "INSERT INTO T_Orders(Date, TotalPrice, PaymentOK, IdCustomer) VALUES(?, ?, ?, ?);";

		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setDate(1, obj.getDate());
			ps.setDouble(2, obj.getTotalPrice());
			ps.setBoolean(3, obj.isPaymentOK());
			ps.setInt(4, obj.getIdCustomer());

			if (ps.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la création d'une commande");
		}

		return false;
	}

	@Override
	public Order read(int id) {
		try (Statement statement = connection.createStatement()) {
			String str = "SELECT * FROM T_Orders where IdOrder=" + id + ";";
			ResultSet rs = statement.executeQuery(str);

			if (rs.next())
				return new Order(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getBoolean(4), rs.getInt(5));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'une commande " + e.getMessage());
		}

		return null;
	}

	@Override
	public boolean update(Order obj) {
		String str = "UPDATE T_Orders set Date = ?, TotalPrice = ?, PaymentOK = ? where idFormation=" + obj.getIdOrder()
				+ ";";

		try (PreparedStatement ps = connection.prepareStatement(str)) {
			ps.setDate(1, obj.getDate());
			ps.setDouble(2, obj.getTotalPrice());
			ps.setBoolean(3, obj.isPaymentOK());

			if (ps.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la mise à jour d'une commande " + e.getMessage());
		}

		return false;
	}

	@Override
	public boolean delete(Order obj) {
		try (Statement statement = connection.createStatement()) {
			String str = "DELETE FROM T_Orders where IdUser=" + obj.getIdOrder() + ";";

			if (statement.executeUpdate(str) == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la suppression d'une commande ");
		}

		return false;
	}

	@Override
	public ArrayList<Order> readAll() {
		ArrayList<Order> orders = new ArrayList<Order>();
		String strSql = "SELECT * FROM T_Orders";

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(strSql)) {
				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					Date rsDate = resultSet.getDate(2);
					Double rsTotalPrice = resultSet.getDouble(3);
					boolean rsPaymentOK= resultSet.getBoolean(4);
					int rsIdCustomer = resultSet.getInt(5);

					orders.add(new Order(rsId, rsDate, rsTotalPrice, rsPaymentOK, rsIdCustomer));
				}
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur le renvoi de la liste des commandes");
		}
		return orders;
	}

}
