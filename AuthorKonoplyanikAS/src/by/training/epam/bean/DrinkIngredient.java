package by.training.epam.bean;

import java.io.Serializable;

public class DrinkIngredient implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int drinkIngredientId;
	private int drinkId;
	private int portionId;
	private int portionAmount;
	
	public DrinkIngredient() {}
	
	public DrinkIngredient(int drinkIngredientId, int drinkId, int portionId, int portionAmount) {
		this.drinkIngredientId = drinkIngredientId;
		this.drinkId = drinkId;
		this.portionId = portionId;
		this.portionAmount = portionAmount;
	}

	public int getDrinkIngredientId() {
		return drinkIngredientId;
	}

	public void setDrinkIngredientId(int drinkIngredientId) {
		this.drinkIngredientId = drinkIngredientId;
	}

	public int getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(int drinkId) {
		this.drinkId = drinkId;
	}

	public int getPortionId() {
		return portionId;
	}

	public void setPortionId(int portionId) {
		this.portionId = portionId;
	}

	public int getPortionAmount() {
		return portionAmount;
	}

	public void setPortionAmount(int portionAmount) {
		this.portionAmount = portionAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + drinkId;
		result = prime * result + drinkIngredientId;
		result = prime * result + portionAmount;
		result = prime * result + portionId;
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
		DrinkIngredient other = (DrinkIngredient) obj;
		if (drinkId != other.drinkId)
			return false;
		if (drinkIngredientId != other.drinkIngredientId)
			return false;
		if (portionAmount != other.portionAmount)
			return false;
		if (portionId != other.portionId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DrinkIngredient [drinkIngredientId=" + drinkIngredientId + ", drinkId=" + drinkId + ", portionId="
				+ portionId + ", portionAmount=" + portionAmount + "]";
	}

}
