package by.training.epam.service;

import by.training.epam.service.impl.MenuServiceImpl;
import by.training.epam.service.impl.OrderServiceImpl;
import by.training.epam.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	UserService userService;
	OrderService orderService;
	MenuService menuService;
	
	private static ServiceFactory instance;
	
	private ServiceFactory() {
		userService = new UserServiceImpl();
		orderService = new OrderServiceImpl();
		menuService = new MenuServiceImpl();
	}
	
	public static synchronized ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	public OrderService getOrderService() {
		return orderService;
	}
	
	public MenuService getMenuService() {
		return menuService;
	}
}
