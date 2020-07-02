package by.training.epam.bean;

import java.io.Serializable;

public class DrinkExtra implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int drinkExtraId;
	private int drinkId;
	private int extraMenuId;
	private String status;
	
	public DrinkExtra() {}

	public DrinkExtra(int drinkExtraId, int drinkId, int extraMenuId, String status) {
		this.drinkExtraId = drinkExtraId;
		this.drinkId = drinkId;
		this.extraMenuId = extraMenuId;
		this.status = status;
	}

	public int getDrinkExtraId() {
		return drinkExtraId;
	}

	public void setDrinkExtraId(int drinkExtraId) {
		this.drinkExtraId = drinkExtraId;
	}

	public int getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(int drinkId) {
		this.drinkId = drinkId;
	}

	public int getExtraMenuId() {
		return extraMenuId;
	}

	public void setExtraMenuId(int extraMenuId) {
		this.extraMenuId = extraMenuId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + drinkExtraId;
		result = prime * result + drinkId;
		result = prime * result + extraMenuId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		DrinkExtra other = (DrinkExtra) obj;
		if (drinkExtraId != other.drinkExtraId)
			return false;
		if (drinkId != other.drinkId)
			return false;
		if (extraMenuId != other.extraMenuId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DrinkExtra [drinkExtraId=" + drinkExtraId + ", drinkId=" + drinkId + ", extraMenuId=" + extraMenuId
				+ ", status=" + status + "]";
	}
	
}
