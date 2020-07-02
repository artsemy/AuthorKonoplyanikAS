package by.training.epam.bean;

import java.io.Serializable;
import java.util.List;

public class DrinkStore implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private DrinkMenuItem drinkMenuItem;
	private List<ExtraStore> extra;
	
	public DrinkStore() {}

	public DrinkStore(int id, DrinkMenuItem drinkMenuItem, List<ExtraStore> extra) {
		this.id = id;
		this.drinkMenuItem = drinkMenuItem;
		this.extra = extra;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DrinkMenuItem getDrinkMenuItem() {
		return drinkMenuItem;
	}

	public void setDrinkMenuItem(DrinkMenuItem drinkMenuItem) {
		this.drinkMenuItem = drinkMenuItem;
	}

	public List<ExtraStore> getExtra() {
		return extra;
	}

	public void setExtra(List<ExtraStore> extra) {
		this.extra = extra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drinkMenuItem == null) ? 0 : drinkMenuItem.hashCode());
		result = prime * result + ((extra == null) ? 0 : extra.hashCode());
		result = prime * result + id;
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
		if (drinkMenuItem == null) {
			if (other.drinkMenuItem != null)
				return false;
		} else if (!drinkMenuItem.equals(other.drinkMenuItem))
			return false;
		if (extra == null) {
			if (other.extra != null)
				return false;
		} else if (!extra.equals(other.extra))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DrinkStore [id=" + id + ", drinkMenuItem=" + drinkMenuItem + ", extra=" + extra + "]";
	}

}
