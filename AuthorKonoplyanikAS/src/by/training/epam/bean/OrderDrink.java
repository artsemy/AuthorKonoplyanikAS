package by.training.epam.bean;

import java.io.Serializable;

public class OrderDrink implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int orderDrinkId;
	private int orderId;
	private int drinkId;
	private String status;
	private int size;
	
	public OrderDrink() {}

	public OrderDrink(int orderDrinkId, int orderId, int drinkId, String status, int size) {
		this.orderDrinkId = orderDrinkId;
		this.orderId = orderId;
		this.drinkId = drinkId;
		this.status = status;
		this.size = size;
	}

	public int getOrderDrinkId() {
		return orderDrinkId;
	}

	public void setOrderDrinkId(int orderDrinkId) {
		this.orderDrinkId = orderDrinkId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(int drinkId) {
		this.drinkId = drinkId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + drinkId;
		result = prime * result + orderDrinkId;
		result = prime * result + orderId;
		result = prime * result + size;
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
		OrderDrink other = (OrderDrink) obj;
		if (drinkId != other.drinkId)
			return false;
		if (orderDrinkId != other.orderDrinkId)
			return false;
		if (orderId != other.orderId)
			return false;
		if (size != other.size)
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
		return "OrderDrink [orderDrinkId=" + orderDrinkId + ", orderId=" + orderId + ", drinkId=" + drinkId
				+ ", status=" + status + ", size=" + size + "]";
	}
	
}
