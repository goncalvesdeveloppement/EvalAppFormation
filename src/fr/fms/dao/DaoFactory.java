package fr.fms.dao;
import fr.fms.entities.Formation;
import fr.fms.entities.FormationOrder;
import fr.fms.entities.Category;
import fr.fms.entities.Customer;
import fr.fms.entities.Order;
import fr.fms.entities.User;

public class DaoFactory {
	
	public static Dao<Formation> getFormationDao() {
		return new FormationDao();		
	}
	
	public static Dao<FormationOrder> getFormationOrderDao() {
		return new FormationOrderDao();
	}
	
 	public static Dao<Category> getCategoryDao() {
 		return new CategoryDao();
 	}
 	
 	public static Dao<Order> getOrderDao() {
 		return new OrderDao();
 	}
 	
 	public static Dao<User> getUserDao() {
 		return new UserDao();
 	}
 	
 	public static Dao<Customer> getCustomerDao() {
 		return new CustomerDao();
 	}
 	
 	
}
