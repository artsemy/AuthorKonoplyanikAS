package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;

public class SignIn implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		String login = request.getParameter(ControllerConstant.LOGIN);
		String password = request.getParameter(ControllerConstant.PASSWORD);
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();
		boolean successful;
		try {
			successful = service.readUser(login, password);
		} catch (ServiceException e) {
			successful = false;
		}
		String url;
		if (successful) {
			url = ControllerConstant.MAIN_PAGE;
			session.setAttribute(ControllerConstant.SA_LOGIN, login);
		} else {
			url = ControllerConstant.ERROR_PAGE;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
}
