package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;

public class AddDrink implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter(ControllerConstant.COFFEE_ID);
		request.setAttribute(ControllerConstant.COFFEE_ID, id);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.ADD_DRINK_PAGE);
		dispatcher.forward(request, response);
	}

}
