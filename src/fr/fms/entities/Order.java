package fr.fms.entities;

import java.sql.Date;

public class Order {

	/* ------------ ATTRIBUTES ------------ */
	
	private int idOrder;
	private Date date;
	private double totalPrice;
	private boolean paymentOK;
	private int idCustomer;
	
	/* ------------ CONSTRUCTORS ------------ */
	
	public Order(int idOrder, Date date, double totalPrice, boolean paymentOK, int idCustomer) {
		this.setIdOrder(idOrder);
		this.setDate(date);
		this.setTotalPrice(totalPrice);
		this.setPaymentOK(paymentOK);
		this.setIdCustomer(idCustomer);
	}	
	
	/* ------------ ACCESSORS ------------ */
	
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public boolean isPaymentOK() {
		return paymentOK;
	}
	public void setPaymentOK(boolean paymentOK) {
		this.paymentOK = paymentOK;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	
	/* ------------ METHODS ------------ */

	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", date=" + date + ", totalPrice=" + totalPrice + ", paymentOK="
				+ paymentOK + ", CustomerID=" + idCustomer + "]";
	}
}
