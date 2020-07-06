package by.training.epam.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import by.training.epam.bean.Delivery;
import by.training.epam.bean.Drink;
import by.training.epam.bean.DrinkExtra;
import by.training.epam.bean.DrinkMenuItem;
import by.training.epam.bean.DrinkStore;
import by.training.epam.bean.ExtraMenuItem;
import by.training.epam.bean.ExtraStore;
import by.training.epam.bean.Order;
import by.training.epam.bean.OrderDrink;
import by.training.epam.bean.OrderStore;
import by.training.epam.bean.UserStore;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.MenuDAO;
import by.training.epam.dao.OrderDAO;
import by.training.epam.dao.UserDAO;
import by.training.epam.service.OrderService;
import by.training.epam.service.ServiceException;

public class OrderServiceImpl implements OrderService {
	
	@Override
	public void createOrder(OrderStore orderStore, UserStore userStore) throws ServiceException {
		if (userStore == null || orderStore == null) {
			throw new ServiceException();
		}
		try {
			DAOFactory factory = DAOFactory.getInstance();
			OrderDAO orderDAO = factory.getOrderDAO();
			UserDAO userDAO = factory.getUserDAO();
			
			int balance = checkWallet(orderStore, userStore);
			int walletId = userDAO.readWalletId(userStore.getId());
			userDAO.updateWallet(walletId, balance);
			
			Order order = orderStore.getOrder();
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
	
	private int checkWallet(OrderStore orderStore, UserStore userStore) throws ServiceException {
		int balance = userStore.getBalance();
		int price = orderStore.getOrder().getPrice();
		if (balance < price) {
			throw new ServiceException();
		}
		int resultWallet = balance - price;
		return resultWallet;
	}
	
	private void addDrinkStore(DrinkStore drinkStore, OrderDAO orderDAO) throws DAOException {
		DrinkMenuItem drinkMenuItem = drinkStore.getDrinkMenuItem();
		Drink drink = new Drink();
		drink.setDrinkMenuId(drinkMenuItem.getDrinkMenuId());
		List<ExtraStore> list = drinkStore.getExtra();
		createDrink(drink, orderDAO);
		drinkStore.setId(drink.getDrinkId()); //fix
		if(list != null) {
			for (ExtraStore extraStore : list) {
				DrinkExtra drinkExtra = new DrinkExtra();
				drinkExtra.setDrinkId(drink.getDrinkId());
				drinkExtra.setExtraMenuId(extraStore.getExtraMenuItem().getExtraMenuId());
				drinkExtra.setStatus("added"); //fix
				createIngredient(drinkExtra, orderDAO);
			}
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
			drinkIngredientId = orderDAO.createDrinkExtra(drinkIngredient);
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

	@Override
	public OrderStore readLastOrder() {
		OrderStore orderStore = new OrderStore();
		try {
			setOrder(orderStore);
			setDelivery(orderStore);
			setListDrinkStore(orderStore);
		} catch (DAOException e) {
			orderStore = null;//fix
		}
		return orderStore;
	}
	
	private void setOrder(OrderStore orderStore) throws DAOException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		Order order = orderDAO.readLastOrder(); //fix
		orderStore.setOrder(order);
		orderStore.setId(order.getOrderId()); // +/-
	}
	
	private void setDelivery(OrderStore orderStore) throws DAOException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		int deliveryId = orderStore.getOrder().getDeliveryId();
		Delivery delivery = orderDAO.readDelivery(deliveryId);
		orderStore.setDelivery(delivery);
	}
	
	private void setListDrinkStore(OrderStore orderStore) throws DAOException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		MenuDAO menuDAO = daoFactory.getMenuDAO();
		int orderId = orderStore.getOrder().getOrderId();
		List<Drink> DrinkIdList = orderDAO.readDrinkByOrder(orderId);
		for (Drink drink : DrinkIdList) {
			Drink fullDrink = orderDAO.readDrink(drink.getDrinkId());
			drink.setDrinkMenuId(fullDrink.getDrinkMenuId());
		}
		List<DrinkStore> drinks = new ArrayList<DrinkStore>();
		for (Drink drink : DrinkIdList) {
			DrinkStore drinkStore = new DrinkStore();
			DrinkMenuItem drinkMenuItem = new DrinkMenuItem();
			drinkMenuItem =	menuDAO.readDrinkMenuItem(drink.getDrinkMenuId());
			drinkStore.setId(drink.getDrinkId());
			drinkStore.setDrinkMenuItem(drinkMenuItem);
			setListExtraStore(drinkStore);
			drinks.add(drinkStore);
		}
		orderStore.setDrinks(drinks);
	}
	
	private void setListExtraStore(DrinkStore drinkStore) throws DAOException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		MenuDAO menuDAO = daoFactory.getMenuDAO();
		int drinkId = drinkStore.getId();
		List<DrinkExtra> drinkExtras = orderDAO.readDrinkExtra(drinkId);
		List<ExtraStore> extra = new ArrayList<ExtraStore>();
		for (DrinkExtra drinkExtra : drinkExtras) {
			ExtraStore extraStore = new ExtraStore();
			ExtraMenuItem extraMenuItem = new ExtraMenuItem();
			extraMenuItem = menuDAO.readExtraMenuItem(drinkExtra.getExtraMenuId());
			extraStore.setId(drinkExtra.getDrinkExtraId());
			extraStore.setExtraMenuItem(extraMenuItem);
			extra.add(extraStore);
		}
		drinkStore.setExtra(extra);
	}
	
}
