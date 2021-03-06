package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.User;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;

public class SignUp implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();
		User user = readInput(request);
		boolean successful;
		try {
			successful = service.createUser(user);
		} catch (ServiceException e) {
			successful = false;
		}
		String url;
		if (successful) {
			url = ControllerConstant.MAIN_PAGE;
			session.setAttribute(ControllerConstant.SA_LOGIN, user.getName());
		} else {
			url = ControllerConstant.ERROR_PAGE;
		}
		response.sendRedirect(url);
	}
	
	private User readInput(HttpServletRequest request) {
		String login = request.getParameter(ControllerConstant.LOGIN);
		String password = request.getParameter(ControllerConstant.PASSWORD);
		String name = request.getParameter(ControllerConstant.NAME);
		User user = buildUser(login, password, name);
		return user;
	}
	
	private User buildUser(String login, String password, String name) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setName(name);
//		user.setRoleId(2); //user role
//		user.setWalletId(3); //empty wallet
		return user;
	}

}
