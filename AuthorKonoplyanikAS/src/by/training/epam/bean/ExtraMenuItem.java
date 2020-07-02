package by.training.epam.bean;

import java.io.Serializable;

public class ExtraMenuItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int extraMenuId;
	private String title;
	private int price;
	
	public ExtraMenuItem() {}
	
	public ExtraMenuItem(int extraMenuId, String title, int price) {
		this.extraMenuId = extraMenuId;
		this.title = title;
		this.price = price;
	}

	public int getExtraMenuId() {
		return extraMenuId;
	}

	public void setExtraMenuId(int extraMenuId) {
		this.extraMenuId = extraMenuId;
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
		result = prime * result + extraMenuId;
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
		ExtraMenuItem other = (ExtraMenuItem) obj;
		if (extraMenuId != other.extraMenuId)
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
		return "ExtraMenuItem [extraMenuId=" + extraMenuId + ", title=" + title + ", price=" + price + "]";
	}
	
}
