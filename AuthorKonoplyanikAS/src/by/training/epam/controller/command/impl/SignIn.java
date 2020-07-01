package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.User;
import by.training.epam.bean.UserStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;

public class SignIn implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = buildUser(request);
		User userDB = readUser(user);
		String url;
		if (userDB != null) {
			url = ControllerConstant.MAIN_PAGE;
			HttpSession session =  request.getSession();
			UserStore userStore = buildUserStore(userDB);
			session.setAttribute(ControllerConstant.USER_STORE, userStore);
		} else {
			url = ControllerConstant.ERROR_PAGE;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private User buildUser(HttpServletRequest request) {
		User user = new User();
		String login = request.getParameter(ControllerConstant.LOGIN);
		String password = request.getParameter(ControllerConstant.PASSWORD);
		user.setLogin(login);
		user.setPassword(password);
		return user;
	}
	
	private User readUser(User user) {
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();
		User resultUser;
		try {
			resultUser = service.readUser(user);
		} catch (ServiceException e) {
			resultUser = null;
		}
		return resultUser;
	}
	
	private UserStore buildUserStore(User user) {
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();
		UserStore userStore;
		try {
			userStore = service.buildUserStore(user);
		} catch (ServiceException e) {
			userStore = null;
		}
		return userStore;
	}
	
}
