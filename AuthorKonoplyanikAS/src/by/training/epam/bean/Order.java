package by.training.epam.bean;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int orderId;
	private int userId;
	private int deliveryId;
	private int price;
	private String status;
	private Date openDate;
	private Date closeDate;
	
	public Order() {}

	public Order(int orderId, int userId, int deliveryId, int price, String status, Date openDate, Date closeDate) {
		this.orderId = orderId;
		this.userId = userId;
		this.deliveryId = deliveryId;
		this.price = price;
		this.status = status;
		this.openDate = openDate;
		this.closeDate = closeDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((closeDate == null) ? 0 : closeDate.hashCode());
		result = prime * result + deliveryId;
		result = prime * result + ((openDate == null) ? 0 : openDate.hashCode());
		result = prime * result + orderId;
		result = prime * result + price;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + userId;
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
		Order other = (Order) obj;
		if (closeDate == null) {
			if (other.closeDate != null)
				return false;
		} else if (!closeDate.equals(other.closeDate))
			return false;
		if (deliveryId != other.deliveryId)
			return false;
		if (openDate == null) {
			if (other.openDate != null)
				return false;
		} else if (!openDate.equals(other.openDate))
			return false;
		if (orderId != other.orderId)
			return false;
		if (price != other.price)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", deliveryId=" + deliveryId + ", price=" + price
				+ ", status=" + status + ", openDate=" + openDate + ", closeDate=" + closeDate + "]";
	}

}
