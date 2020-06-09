package by.training.epam.bean;

import java.io.Serializable;

public class DrinkMenuItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int drinkMenuId;
	private String title;
	private int price;
	
	public DrinkMenuItem() {}

	public DrinkMenuItem(int drinkMenuId, String title, int price) {
		super();
		this.drinkMenuId = drinkMenuId;
		this.title = title;
		this.price = price;
	}

	public int getDrinkMenuId() {
		return drinkMenuId;
	}

	public void setDrinkMenuId(int drinkMenuId) {
		this.drinkMenuId = drinkMenuId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + drinkMenuId;
		result = prime * result + price;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		DrinkMenuItem other = (DrinkMenuItem) obj;
		if (drinkMenuId != other.drinkMenuId)
			return false;
		if (price != other.price)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DrinkMenuItem [drinkMenuId=" + drinkMenuId + ", title=" + title + ", price=" + price + "]";
	}
	
}
