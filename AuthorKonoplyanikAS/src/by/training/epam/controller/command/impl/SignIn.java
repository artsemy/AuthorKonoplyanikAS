package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.controller.command.Command;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;

public class SignIn implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();
		boolean successful;
		try {
			successful = service.readUser(name, pass);
		} catch (ServiceException e) {
			successful = false;
		}
		String res = successful ? "welcome back" : "error";
		session.setAttribute("name", name);
		request.setAttribute("result", res);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/home.jsp");
		dispatcher.forward(request, response);
	}
	
}
