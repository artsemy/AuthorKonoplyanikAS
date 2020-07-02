package by.training.epam.bean;

import java.io.Serializable;

public class ExtraStore implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private ExtraMenuItem extraMenuItem;
	
	public ExtraStore() {}

	public ExtraStore(int id, ExtraMenuItem extraMenuItem) {
		this.id = id;
		this.extraMenuItem = extraMenuItem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ExtraMenuItem getExtraMenuItem() {
		return extraMenuItem;
	}

	public void setExtraMenuItem(ExtraMenuItem extraMenuItem) {
		this.extraMenuItem = extraMenuItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((extraMenuItem == null) ? 0 : extraMenuItem.hashCode());
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
		ExtraStore other = (ExtraStore) obj;
		if (extraMenuItem == null) {
			if (other.extraMenuItem != null)
				return false;
		} else if (!extraMenuItem.equals(other.extraMenuItem))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExtraStore [id=" + id + ", extraMenuItem=" + extraMenuItem + "]";
	}

}
