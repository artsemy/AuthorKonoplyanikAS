package by.training.epam.service.impl;

import by.training.epam.bean.Delivery;
import by.training.epam.bean.Drink;
import by.training.epam.bean.DrinkIngredient;
import by.training.epam.bean.Order;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.OrderDAO;
import by.training.epam.service.OrderService;
import by.training.epam.service.ServiceException;

public class OrderServiceImpl implements OrderService {
	
	public int createOrder(Order order) throws ServiceException {
		int orderId = -1;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			orderId = orderDAO.createOrder(order);
			order.setOrderId(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return orderId;
	}
	
	public int createDelivery(Delivery delivery) throws ServiceException {
		int deliveryId = -1;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			deliveryId = orderDAO.createDelivery(delivery);
			delivery.setDeliveryId(deliveryId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return deliveryId;
	}
	
	public int createDrink(Drink drink) throws ServiceException {
		int drinkId = -1;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			drinkId = orderDAO.createDrink(drink);
			drink.setDrinkId(drinkId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drinkId;
	}
	
	public int createDrinkInredient(DrinkIngredient drinkIngredient) throws ServiceException {
		int drinkIngredientId = -1;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			drinkIngredientId = orderDAO.createDrinkIngredient(drinkIngredient);
			drinkIngredient.setDrinkIngredientId(drinkIngredientId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drinkIngredientId;
	}
	
}
