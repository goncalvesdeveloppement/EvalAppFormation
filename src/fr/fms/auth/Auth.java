package fr.fms.auth;

import fr.fms.dao.CustomerDao;
import fr.fms.dao.Dao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.UserDao;
import fr.fms.entities.Customer;
import fr.fms.entities.User;

public class Auth {
	private Dao<Customer> cdao = DaoFactory.getCustomerDao();
	private Dao<User> udao = DaoFactory.getUserDao();
	
	public int existUser(String log, String pwd) {
		User user = ((UserDao)udao).findUserByCredentials(log,pwd);
		if(user != null )	return user.getIdUser();
		return 0;
	}
	
	public int existUser(String log) {
		User user = ((UserDao)udao).findUserByLogin(log);
		if(user != null )	return user.getIdUser();
		return 0;
	}

	public Customer existCustomerByEmail(String email) {
		return ((CustomerDao)cdao).findCustomerByEmail(email);
	}

	public void addUser(String email, String password) {
		udao.create(new User(email, password));		
	}

	public boolean addCustomer(Customer customer) {
		return cdao.create(customer);		
	}
}
