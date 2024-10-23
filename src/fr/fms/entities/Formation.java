package fr.fms.entities;

public class Formation {

	/* ------------ ATTRIBUTES ------------ */

	private int idFormation;
	private String name;
	private String description;
	private int duration;
	private boolean remoteWork;
	private double price;
	private int idCategory;

	/* ------------ CONSTRUCTORS ------------ */

	public Formation(int idFormation, String name, String description, int duration, boolean isRemoteWork, double price,
			int idCategory) {
		this.setIdFormation(idFormation);
		this.setName(name);
		this.setDescription(description);
		this.setDuration(duration);
		this.setRemoteWork(isRemoteWork);
		this.setPrice(price);;
		this.setIdCategory(idCategory);
	}
	
	/* ------------ ACCESSORS ------------ */

	public int getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isRemoteWork() {
		return remoteWork;
	}

	public void setRemoteWork(boolean isRemoteWork) {
		this.remoteWork = isRemoteWork;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	
	/* ------------ METHODS ------------ */

	@Override
	public String toString() {
		return "Formation [idFormation=" + idFormation + ", name=" + name + ", description=" + description
				+ ", duration=" + duration + ", remoteWork=" + remoteWork + ", price=" + price + ", idCategory="
				+ idCategory + "]";
	}
}
