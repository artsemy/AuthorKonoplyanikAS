package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.User;
import by.training.epam.controller.command.Command;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;

public class SignUp implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();
		User user = buildUser(login, password);
		boolean successful;
		try {
			successful = service.createUser(user);
		} catch (ServiceException e) {
			successful = false;
		}
		String url, res;
		if (successful) {
			url = "main.jsp";
			res = "successful";
			session.setAttribute("login", login);
		} else {
			url = "error.jsp";
			res = "something goes wrong";
		}
		request.setAttribute("result", res);
//		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//		dispatcher.forward(request, response);
		response.sendRedirect(url);
	}
	
	private User buildUser(String login, String password) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setRoleId(2); //user role
		user.setWalletId(3); //empty wallet
		return user;
	}

}
