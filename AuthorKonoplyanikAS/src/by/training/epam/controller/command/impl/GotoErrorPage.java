package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;

public class GotoErrorPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.ERROR_PAGE);
		dispatcher.forward(request, response);
	}

}
