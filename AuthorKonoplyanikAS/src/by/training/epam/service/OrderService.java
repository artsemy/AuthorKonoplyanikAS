package by.training.epam.service;

import by.training.epam.bean.Delivery;
import by.training.epam.bean.Drink;
import by.training.epam.bean.DrinkExtra;
import by.training.epam.bean.DrinkStore;
import by.training.epam.bean.Order;
import by.training.epam.bean.OrderStore;

public interface OrderService {
	
	public void addDrink(OrderStore orderStore, Drink drink);
	
	public void createOrder(OrderStore orderStore) throws ServiceException;
}
