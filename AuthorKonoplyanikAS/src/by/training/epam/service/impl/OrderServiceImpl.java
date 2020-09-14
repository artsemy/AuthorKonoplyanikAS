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
import by.training.epam.service.ServiceConstant;
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
				drinkExtra.setStatus(ServiceConstant.ADDED); //fix
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
		order.setStatus(ServiceConstant.ADDED); //fix
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
			createOrderDrink(orderId, drinkId, ServiceConstant.ADDED, orderDAO);
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
	public OrderStore readActiveOrder(int userId) throws ServiceException { //fix list need
		OrderStore orderStore = null;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			List<Order> orders = orderDAO.readOrderByUser(userId);
			for (Order order : orders) {
				if (order.getStatus().equals(ServiceConstant.ADDED)) {
					orderStore = buildOrderStore(order.getOrderId());
				}
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return orderStore;
	}
	
	public OrderStore buildOrderStore(int orderId) throws ServiceException {
		OrderStore orderStore = new OrderStore();
		DAOFactory daoFactory;
		try {
			daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			
			Order order = orderDAO.readOrder(orderId);
			Delivery delivery = orderDAO.readDelivery(order.getDeliveryId());
			List<DrinkStore> drinks = new ArrayList<DrinkStore>();
			List<OrderDrink> orderDrinks = orderDAO.readOrderDrinkByOrder(orderId);
			for (OrderDrink orderDrink : orderDrinks) {
				if(orderDrink.getStatus().equals(ServiceConstant.ADDED)) {
					DrinkStore drinkStore = buildDrinkStore(orderDrink.getDrinkId());
					drinks.add(drinkStore);
				}
			}
			if (drinks.isEmpty()) {
				orderDAO.updateOrderStatus(orderId, ServiceConstant.REMOVED);
				return null;
			}
			orderStore.setId(order.getOrderId());
			orderStore.setOrder(order);
			orderStore.setDelivery(delivery);
			orderStore.setDrinks(drinks);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return orderStore;
	}

	@Override
	public void updateOrderRemoveDrinkStatus(int orderId, int drinkId) throws ServiceException {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			orderDAO.updateOrderDrinkStatusByDrink(drinkId, ServiceConstant.REMOVED);
			orderDAO.updateDrinkExtraStatusByDrink(drinkId, ServiceConstant.REMOVED);
			orderDAO.updateOrderStatus(orderId, ServiceConstant.ADDED); //updated status?
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int countDrinkWithExtraPrice(int drinkId) throws ServiceException {
		int price = 0;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			MenuDAO menuDAO = daoFactory.getMenuDAO();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			price += countDrinkPrice(orderDAO, menuDAO, drinkId);
			price += countExtraOfDrinkPrice(orderDAO, menuDAO, drinkId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return price;
	}
	
	private int countDrinkPrice(OrderDAO orderDAO, MenuDAO menuDAO, int drinkId) throws DAOException {
		int price = 0;
		int drinkMenuId = orderDAO.readDrink(drinkId).getDrinkMenuId();
		price += menuDAO.readDrinkMenuItem(drinkMenuId).getPrice();
		return price;
	}
	
	private int countExtraOfDrinkPrice(OrderDAO orderDAO, MenuDAO menuDAO, int drinkId) throws DAOException {
		int price = 0;
		List<DrinkExtra> drinkExtras =  orderDAO.readDrinkExtra(drinkId);
		for (DrinkExtra drinkExtra : drinkExtras) {
			if (drinkExtra.getStatus().equals(ServiceConstant.ADDED)) {
				int extraId = drinkExtra.getExtraMenuId();
				price += menuDAO.readExtraMenuItem(extraId).getPrice();
			}
		}
		return price;
	}

	@Override
	public void recountOrderPriceByOrder(int orderId) throws ServiceException {
		int price = 0;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			MenuDAO menuDAO = daoFactory.getMenuDAO(); 
			List<OrderDrink> drinks = orderDAO.readOrderDrinkByOrder(orderId);
			for (OrderDrink orderDrink : drinks) {
				if (!orderDrink.getStatus().equals(ServiceConstant.REMOVED)) {
					int drinkId = orderDrink.getDrinkId();
					price += countDrinkPrice(orderDAO, menuDAO, drinkId);
					price += countExtraOfDrinkPrice(orderDAO, menuDAO, drinkId);
				}
			}
			orderDAO.updateOrderPrice(orderId, price);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public DrinkStore buildDrinkStore(int drinkId) throws ServiceException {
		DrinkStore drinkStore = new DrinkStore();
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			MenuDAO menuDAO = daoFactory.getMenuDAO();
			Drink drink = orderDAO.readDrink(drinkId);
			DrinkMenuItem drinkMenuItem = menuDAO.readDrinkMenuItem(drink.getDrinkMenuId());
			
			List<ExtraStore> extraStores = new ArrayList<ExtraStore>();
			List<DrinkExtra> drinkExtras =  orderDAO.readDrinkExtra(drinkId);
			for (DrinkExtra drinkExtra : drinkExtras) {
				if (drinkExtra.getStatus().equals(ServiceConstant.ADDED)) {
					ExtraStore extraStore = buildExtraStore(drinkExtra.getDrinkExtraId());
					extraStores.add(extraStore);
				}
			}
			
			drinkStore.setId(drinkId);
			drinkStore.setDrinkMenuItem(drinkMenuItem);
			drinkStore.setExtra(extraStores);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drinkStore;
	}
	
	@Override
	public ExtraStore buildExtraStore(int drinkExtraId) throws ServiceException {
		ExtraStore extraStore = new ExtraStore();
		try {
			extraStore.setId(drinkExtraId);
			DAOFactory daoFactory = DAOFactory.getInstance();
			MenuDAO menuDAO = daoFactory.getMenuDAO();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			DrinkExtra drinkExtra = orderDAO.readDrinkExtraById(drinkExtraId);
			ExtraMenuItem extraMenuItem = menuDAO.readExtraMenuItem(drinkExtra.getExtraMenuId());
			extraStore.setExtraMenuItem(extraMenuItem);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return extraStore;
	}

	@Override
	public void updateOrderRemoveExtraStatus(int drinkExtraId) throws ServiceException {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			orderDAO.updateDrinkExtraStatusById(drinkExtraId, ServiceConstant.REMOVED);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void recountOrderPriceByDrink(int drinkId) throws ServiceException {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			OrderDrink orderDrink = orderDAO.readOrderDrinkByDrink(drinkId);
			int orderId = orderDrink.getOrderId();
			recountOrderPriceByOrder(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
}
