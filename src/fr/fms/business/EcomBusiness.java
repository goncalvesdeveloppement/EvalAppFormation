package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Category;
import fr.fms.entities.Formation;

public interface EcomBusiness {
	public void addToCart(Formation formation);
	public void removeFromCart(int id);
	public ArrayList<Formation> getCart();	
	public int order(int idUser);	
	public ArrayList<Formation> readFormations();
	public Formation readOneFormation(int id);	
	public ArrayList<Category> readCategories();
	public ArrayList<Formation> readFormationsByCatId(int idCat);
}
