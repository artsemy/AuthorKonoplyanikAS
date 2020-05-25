package by.training.epam.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderStore implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Order order;
	private Delivery delivery;
	private List<DrinkStore> drinks;
	
	public OrderStore() {
		drinks = new ArrayList<DrinkStore>();
	}

	public OrderStore(Order order, Delivery delivery, List<DrinkStore> drinks) {
		this.order = order;
		this.delivery = delivery;
		this.drinks = drinks;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public List<DrinkStore> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<DrinkStore> drinks) {
		this.drinks = drinks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((delivery == null) ? 0 : delivery.hashCode());
		result = prime * result + ((drinks == null) ? 0 : drinks.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		OrderStore other = (OrderStore) obj;
		if (delivery == null) {
			if (other.delivery != null)
				return false;
		} else if (!delivery.equals(other.delivery))
			return false;
		if (drinks == null) {
			if (other.drinks != null)
				return false;
		} else if (!drinks.equals(other.drinks))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderStore [order=" + order + ", delivery=" + delivery + ", drinks=" + drinks + "]";
	}

}
