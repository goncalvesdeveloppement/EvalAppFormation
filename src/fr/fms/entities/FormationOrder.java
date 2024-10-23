package fr.fms.entities;

public class FormationOrder {

	/* ------------ ATTRIBUTES ------------ */
	
	private int idFormationOrder;
	private int idFormation;
	private int idOrder;
	private int quantity;
	
	/* ------------ CONSTRUCTORS ------------ */

	public FormationOrder(int idFormationOrder, int idFormation, int idOrder, int quantity) {
		this.setIdFormationOrder(idFormationOrder);
		this.setIdFormation(idFormation);
		this.setIdOrder(idOrder);
		this.setQuantity(quantity);
	}
	
	/* ------------ ACCESSORS ------------ */

	public int getIdFormationOrder() {
		return idFormationOrder;
	}
	public void setIdFormationOrder(int idFormationOrder) {
		this.idFormationOrder = idFormationOrder;
	}
	public int getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/* ------------ METHODS ------------ */
	
	@Override
	public String toString() {
		return "FormationOrder [idFormationOrder=" + idFormationOrder + ", idFormation=" + idFormation + ", idOrder="
				+ idOrder + ", quantity=" + quantity + "]";
	}
}
