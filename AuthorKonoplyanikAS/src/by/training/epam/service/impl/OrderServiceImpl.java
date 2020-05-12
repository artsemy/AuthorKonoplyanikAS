package by.training.epam.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.training.epam.bean.Delivery;
import by.training.epam.bean.Drink;
import by.training.epam.bean.DrinkIngredient;
import by.training.epam.bean.DrinkStore;
import by.training.epam.bean.Order;
import by.training.epam.bean.OrderStore;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.OrderDAO;
import by.training.epam.service.OrderService;
import by.training.epam.service.ServiceException;

public class OrderServiceImpl implements OrderService {

	@Override
	public void addDrink(OrderStore orderStore, Drink drink) {
		// TODO Auto-generated method stub
		
	}

	public int createOrder(OrderStore orderStore) throws ServiceException {
		try {
			DAOFactory factory = DAOFactory.getInstance();
			OrderDAO dao = factory.getOrderDAO();
			Order order = orderStore.getOrder();
			Delivery delivery = orderStore.getDelivery();
			List<DrinkStore> drinks = orderStore.getDrinks();
			int deliveryId = dao.createDelivery(delivery);
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return 0;
	}

}
