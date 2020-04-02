package by.training.epam.bean;

import java.io.Serializable;

public class Drink implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int drinkId;
	private int drinkMenuId;
	
	public Drink() {}

	public Drink(int drinkId, int drinkMenuId) {
		this.drinkId = drinkId;
		this.drinkMenuId = drinkMenuId;
	}

	public int getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(int drinkId) {
		this.drinkId = drinkId;
	}

	public int getDrinkMenuId() {
		return drinkMenuId;
	}

	public void setDrinkMenuId(int drinkMenuId) {
		this.drinkMenuId = drinkMenuId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + drinkId;
		result = prime * result + drinkMenuId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Drink other = (Drink) obj;
		if (drinkId != other.drinkId)
			return false;
		if (drinkMenuId != other.drinkMenuId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Drink [drinkId=" + drinkId + ", drinkMenuId=" + drinkMenuId + "]";
	}

}
