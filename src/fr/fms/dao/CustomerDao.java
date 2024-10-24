package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Customer;

public class CustomerDao implements Dao<Customer> {

	@Override
	public boolean create(Customer obj) {
		String sqlQuery = "INSERT INTO T_Customers(UserLogin, FirstName, LastName, Email, Address, Phone) VALUES(?, ?, ?, ?, ?, ?);";

		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setString(1, obj.getUserLogin());
			ps.setString(2, obj.getFirstName());
			ps.setString(3, obj.getLastName());
			ps.setString(4, obj.getEmail());
			ps.setString(5, obj.getAddress());
			ps.setString(6, obj.getPhone());

			if (ps.executeUpdate() == 1) {
				
				// Avoir directement le nouvel ID mis à jour dans l'objet
				try (Statement statement = connection.createStatement()) {
					String str = "SELECT LAST_INSERT_ID() as id;";
					ResultSet rs = statement.executeQuery(str);

					if (rs.next())
						obj.setIdCustomer(rs.getInt(1));
				} catch (SQLException e) {
					logger.severe("pb sql sur la lecture d'un client " + e.getMessage());
				}
				
				return true;
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur la création d'un client");
		}

		return false;
	}

	@Override
	public Customer read(int id) {
		try (Statement statement = connection.createStatement()) {
			String str = "SELECT * FROM T_Customers where IdCustomer=" + id + ";";
			ResultSet rs = statement.executeQuery(str);

			if (rs.next())
				return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'un client " + e.getMessage());
		}

		return null;
	}

	@Override
	public boolean update(Customer obj) {
		String str = "UPDATE T_Customers SET UserLogin = ?, FirstName = ?, LastName = ?, Email = ?, Address = ?, Phone = ? WHERE IdFormation= "
				+ obj.getIdCustomer() + ";";

		try (PreparedStatement ps = connection.prepareStatement(str)) {
			ps.setString(1, obj.getUserLogin());
			ps.setString(2, obj.getFirstName());
			ps.setString(3, obj.getLastName());
			ps.setString(4, obj.getEmail());
			ps.setString(5, obj.getAddress());
			ps.setString(6, obj.getPhone());

			if (ps.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la mise à jour d'un client " + e.getMessage());
		}

		return false;
	}

	@Override
	public boolean delete(Customer obj) {
		try (Statement statement = connection.createStatement()) {
			String str = "DELETE FROM T_Customers where IdUser=" + obj.getIdCustomer() + ";";

			if (statement.executeUpdate(str) == 1)
				return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la suppression d'un client ");
		}

		return false;
	}

	@Override
	public ArrayList<Customer> readAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		String strSql = "SELECT * FROM T_Customers";

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(strSql)) {
				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsUserLogin = resultSet.getString(2);
					String rsFirstName = resultSet.getString(3);
					String rsLastName = resultSet.getString(4);
					String rsEmail = resultSet.getString(5);
					String rsAddress = resultSet.getString(6);
					String rsPhone = resultSet.getString(7);

					customers
							.add(new Customer(rsId, rsUserLogin, rsFirstName, rsLastName, rsEmail, rsAddress, rsPhone));
				}
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur le renvoi de la liste des clients");
		}
		return customers;
	}

	public Customer findCustomerByEmail(String email) {
		String strSql = "SELECT * FROM T_Customers where Email=?;";
		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setString(1, email);
			try (ResultSet resultSet = ps.executeQuery()) {
				if (resultSet.next()) {
					return new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
							resultSet.getString(7));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
