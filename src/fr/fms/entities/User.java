package fr.fms.entities;

public class User {

	/* ------------ ATTRIBUTES ------------ */
	
	private int idUser;
	private String loginUser;
	private String password;
	private boolean admin;

	/* ------------ CONSTRUCTORS ------------ */
	
	public User(int idUser, String loginUser, String password, boolean admin) {
		this.setIdUser(idUser);
		this.setLoginUser(loginUser);
		this.setPassword(password);
		this.setAdmin(admin);
	}
	
	public User(String loginUser, String password) {
		this(0, loginUser, password, false);
	}
	
	/* ------------ ACCESSORS ------------ */

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}		
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	/* ------------ METHODS ------------ */

	@Override
	public String toString() {
		return "User [loginUser=" + loginUser + ", password=" + password + ", admin=" + admin + "]";
	}
}
