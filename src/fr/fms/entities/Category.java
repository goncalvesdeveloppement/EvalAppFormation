package fr.fms.entities;

public class Category {
	
	/* ------------ ATTRIBUTES ------------ */
	
	private int idCategory;
	private String title;
	
	/* ------------ CONSTRUCTORS ------------ */
	
	public Category(int idCategory, String title) {
		this.setTitle(title);
		this.setIdCategory(idCategory);
	}
	
	/* ------------ ACCESSORS ------------ */
	
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	/* ------------ METHODS ------------ */

	@Override
	public String toString() {
		return "Category [idCategory=" + idCategory + ", title=" + title + "]";
	}	
}
