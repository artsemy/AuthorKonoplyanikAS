package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.controller.command.Command;

public class SignOut implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		session.removeAttribute("login");
//		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
//		dispatcher.forward(request, response);
		response.sendRedirect("main.jsp");
	}

}
