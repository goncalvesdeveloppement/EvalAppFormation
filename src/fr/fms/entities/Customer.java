package fr.fms.entities;

public class Customer {

	/* ------------ ATTRIBUTES ------------ */
	
	private int idCustomer;
	private String userLogin;
	private String lastName;
	private String firstName;
	private String email;
	private String address;
	private String phone;
	
	/* ------------ CONSTRUCTORS ------------ */
	
	public Customer(int idCustomer, String userLogin, String lastName, String firstName, String email, String address,
			String phone) {
		this.setIdCustomer(idCustomer);
		this.setUserLogin(userLogin);
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setEmail(email);
		this.setAddress(address);
		this.setPhone(phone);
	}	
	
	/* ------------ ACCESSORS ------------ */

	public int getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/* ------------ METHODS ------------ */

	@Override
	public String toString() {
		return "Customer [idCustomer=" + idCustomer + ", userLogin=" + userLogin + ", lastName=" + lastName
				+ ", firstName=" + firstName + ", email=" + email + ", address=" + address + ", phone=" + phone + "]";
	}
}
