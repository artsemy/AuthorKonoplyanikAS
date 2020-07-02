package by.training.epam.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import by.training.epam.bean.Delivery;
import by.training.epam.bean.Drink;
import by.training.epam.bean.DrinkExtra;
import by.training.epam.bean.DrinkMenuItem;
import by.training.epam.bean.DrinkStore;
import by.training.epam.bean.ExtraStore;
import by.training.epam.bean.Order;
import by.training.epam.bean.OrderDrink;
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
	
	@Override
	public void createOrder(OrderStore orderStore) throws ServiceException {
		try {
			DAOFactory factory = DAOFactory.getInstance();
			OrderDAO orderDAO = factory.getOrderDAO();
			Order order = orderStore.getOrder();
			order = new Order(); //fix
			orderStore.setOrder(order); //fix
			Delivery delivery = orderStore.getDelivery();
			List<DrinkStore> drinks = orderStore.getDrinks();
			
			createDelivery(delivery, orderDAO); //+
			createOrder(delivery.getDeliveryId(), order, orderDAO); //+
			for (DrinkStore drinkStore : drinks) {
				addDrinkStore(drinkStore, orderDAO);
			}
			
			addOrderDrink(orderStore, orderDAO);
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	private void addDrinkStore(DrinkStore drinkStore, OrderDAO orderDAO) throws DAOException {
		DrinkMenuItem drinkMenuItem = drinkStore.getDrinkMenuItem();
		Drink drink = new Drink();
		drink.setDrinkMenuId(drinkMenuItem.getDrinkMenuId());
		List<ExtraStore> list = drinkStore.getExtra();
		createDrink(drink, orderDAO);
		drinkStore.setId(drink.getDrinkId()); //fix
		for (ExtraStore extraStore : list) {
			DrinkExtra drinkExtra = new DrinkExtra();
			drinkExtra.setDrinkId(drink.getDrinkId());
			drinkExtra.setExtraMenuId(extraStore.getExtraMenuItem().getExtraMenuId());
			drinkExtra.setStatus("added"); //fix
			createIngredient(drinkExtra, orderDAO);
		}
	}
	
	private void createDrink(Drink drink, OrderDAO orderDAO) throws DAOException {
		int drinkId = -1;
		while(drinkId == -1) {
			drinkId = orderDAO.createDrink(drink);
		}
		drink.setDrinkId(drinkId);
	}
	
	private void createIngredient(DrinkExtra drinkIngredient, OrderDAO orderDAO) throws DAOException {
		int drinkIngredientId = -1;
		while (drinkIngredientId == -1) {
			drinkIngredientId = orderDAO.createDrinkIngredient(drinkIngredient);
		}
	}
	
	private void createDelivery(Delivery delivery, OrderDAO orderDAO) throws DAOException {
		int deliveryId = -1;
		while(deliveryId == -1) {
			deliveryId = orderDAO.createDelivery(delivery);
		}
		delivery.setDeliveryId(deliveryId);
	}
	
	private void createOrder(int deliveryId, Order order, OrderDAO orderDAO) throws DAOException {
		java.util.Date date2 = new java.util.Date(); //fix
		Date date =  new Date(date2.getTime()); //fix
		order.setOpenDate(date); //fix
		order.setCloseDate(date); //fix
		order.setPrice(333); //fix
		order.setStatus("start"); //fix
		order.setDeliveryId(deliveryId); //fix
		order.setUserId(1); //fix
		
		int orderId = -1;
		while(orderId == -1) {
			orderId = orderDAO.createOrder(order);
		}
		order.setOrderId(orderId);
	}
	
	private void addOrderDrink(OrderStore orderStore, OrderDAO orderDAO) throws DAOException {
		int orderId = orderStore.getOrder().getOrderId(); //fix
		List<DrinkStore> list = orderStore.getDrinks();
		for (DrinkStore drinkStore : list) {
			int drinkId = drinkStore.getId();
			createOrderDrink(orderId, drinkId, "start", orderDAO);
		}
	}
	
	private void createOrderDrink(int orderId, int drinkId, String status, OrderDAO orderDAO) throws DAOException {
		OrderDrink orderDrink = new OrderDrink();
		orderDrink.setOrderId(orderId);
		orderDrink.setDrinkId(drinkId);
		orderDrink.setStatus(status);
		
		int orderDrinkId = -1;
		while(orderDrinkId == -1) {
			orderDrinkId = orderDAO.createOrderDrink(orderDrink);
		}
	}

}
