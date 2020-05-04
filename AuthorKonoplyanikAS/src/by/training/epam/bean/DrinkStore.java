package by.training.epam.bean;

import java.io.Serializable;
import java.util.List;

public class DrinkStore implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Drink drink;
	private List<DrinkIngredient> ingredients;
	
	public DrinkStore() {}


	public DrinkStore(Drink drink, List<DrinkIngredient> ingredients) {
		this.drink = drink;
		this.ingredients = ingredients;
	}


	public Drink getDrink() {
		return drink;
	}


	public void setDrink(Drink drink) {
		this.drink = drink;
	}


	public List<DrinkIngredient> getIngredients() {
		return ingredients;
	}


	public void setIngredients(List<DrinkIngredient> ingredients) {
		this.ingredients = ingredients;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drink == null) ? 0 : drink.hashCode());
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
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
		DrinkStore other = (DrinkStore) obj;
		if (drink == null) {
			if (other.drink != null)
				return false;
		} else if (!drink.equals(other.drink))
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "DrinkStore [drink=" + drink + ", ingredients=" + ingredients + "]";
	}
	
}
