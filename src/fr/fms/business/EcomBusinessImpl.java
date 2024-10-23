package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.fms.dao.Dao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.FormationDao;
import fr.fms.entities.Formation;
import fr.fms.entities.Category;
import fr.fms.entities.Customer;
import fr.fms.entities.Order;
import fr.fms.entities.FormationOrder;
import fr.fms.entities.User;

public class EcomBusinessImpl {
	private HashMap<Integer, Integer> cart;
	private Dao<Formation> fdao = DaoFactory.getFormationDao();
	private Dao<Category> kdao = DaoFactory.getCategoryDao();
	private Dao<Order> odao = DaoFactory.getOrderDao();
	private Dao<FormationOrder> xdao = DaoFactory.getFormationOrderDao();
	//private Dao<Customer> cdao = DaoFactory.getCustomerDao();
	private Dao<User> udao = DaoFactory.getUserDao();

	public EcomBusinessImpl() {
		this.cart = new HashMap<Integer, Integer>();
	}

	public void addToCart(Formation formation) {
		int idFormation = formation.getIdFormation();
		int numberInCart = cart.get(idFormation);

		cart.put(idFormation, ++numberInCart);
	}

	public void removeFromCart(int id) {
		int numberInCart = cart.get(id);
		cart.put(id, --numberInCart);

		if (numberInCart == 0)
			cart.remove(id);
	}

	public ArrayList<Formation> getCart() {
		ArrayList<Formation> cartList = new ArrayList<Formation>();

		for (Map.Entry<Integer, Integer> pair : cart.entrySet()) {
			for (int i = 0; i < pair.getValue(); i++) {
				cartList.add(fdao.read(pair.getKey()));
			}
		}

		return cartList;
	}

	public int order(int idUser) {
		if (udao.read(idUser) != null) {
			double totalPrice = getTotal();

			Order order = new Order(totalPrice, idUser);

			if (odao.create(order)) {
				for (Map.Entry<Integer, Integer> pair : cart.entrySet()) {
					xdao.create(new FormationOrder(0, pair.getKey(), order.getIdOrder(), pair.getValue()));
				}

				return order.getIdOrder();
			}
		}

		return 0;
	}

	public ArrayList<Formation> readFormations() {
		return fdao.readAll();
	}

	public Formation readOneFormation(int id) {
		return fdao.read(id);
	}

	public ArrayList<Category> readCategories() {
		return kdao.readAll();
	}

	public ArrayList<Formation> readFormationsByCatId(int idCat) {
		return ((FormationDao) fdao).readAllByCat(idCat);
	}

	public ArrayList<Formation> readFormationsByKeyWords(String keyword) {
		return ((FormationDao) fdao).readAllByKeyWord(keyword);
	}

	public ArrayList<Formation> readFormationsByRemoteWork(boolean remoteWork) {
		return ((FormationDao) fdao).readAllByRemoteWork(remoteWork);
	}

	public boolean isCartEmpty() {
		return cart.isEmpty();
	}

	public void clearCart() {
		cart.clear();
	}

	public Category readOneCategory(int id) {
		return kdao.read(id);
	}

	public double getTotal() {
		double total = 0;

		for (Formation form : getCart()) {
			total += form.getPrice();
		}

		return total;
	}
}
