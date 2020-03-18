package by.training.epam.controller;

import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;

public class Controller {
	
	public String run(String responce, String name, String pass) {
		String str = "";
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		if (responce.equals("LOG_IN")) {
			try {
				userService.createUser(name, pass);
			} catch (ServiceException e) {
				return e.getMessage();
			}
			str = "hello newbie";
		} else if (responce.equals("SIGN_IN")){
			try {
				userService.readUser(name, pass);
			} catch (ServiceException e) {
				return e.getMessage();
			}
			str = "welcome back";
		} else {
			str = "bye, bye";
		}
		return str;
	}
		
}
