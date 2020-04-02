package by.training.epam.service;

import by.training.epam.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	UserService userService;
	
	private static ServiceFactory instance;
	
	private ServiceFactory() {
		userService = new UserServiceImpl();
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
	
}
