package fr.fms;
import fr.fms.dao.Dao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.FormationDao;
import fr.fms.entities.Formation;
//import fr.fms.entities.Category;
//import fr.fms.entities.Customer;
//import fr.fms.entities.FormationOrder;
//import fr.fms.entities.Order;
//import fr.fms.entities.User;

public class TestDaoComponents {

	public static void main(String[] args) {
		Dao<Formation> fdao = DaoFactory.getFormationDao();
//		HashMap<Integer, Integer> cart;
//		Dao<Category> kdao = DaoFactory.getCategoryDao();
//		Dao<Order> odao = DaoFactory.getOrderDao();
//		Dao<FormationOrder> xdao = DaoFactory.getFormationOrderDao();
//		Dao<Customer> cdao = DaoFactory.getCustomerDao();
//		Dao<User> udao = DaoFactory.getUserDao();

		// FORMATION
		System.out.println(fdao.readAll()); // OK
		
		Formation formation = fdao.read(2); // OK
		System.out.println(formation);
		
		//formation.setDuration(999);
		System.out.println(formation);
		
		//fdao.update(formation); // OK
		Formation formation_n = new Formation(0, "Le dev agressif comme un python", "Vous avalerez la concurrence !", 250, false, 100, 1);
		//fdao.create(formation_n); // OK
		
		//fdao.delete(formation);
		
		System.out.println(formation_n.getIdFormation()); // OK

		System.out.println(((FormationDao) fdao).readAllByCat(8)); // OK
		System.out.println(((FormationDao) fdao).readAllByRemoteWork(true)); // OK
		System.out.println(((FormationDao) fdao).readAllByKeyWord("uml")); // OK
	}

}
