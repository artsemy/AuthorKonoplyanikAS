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
		String res = "bye, bye";
		request.setAttribute("result", res);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/home.jsp");
		dispatcher.forward(request, response);
	}

}
