package by.training.epam.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.epam.controller.command.Command;
import by.training.epam.controller.command.CommandFactory;

public class Controller {
	
	public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cName = parceCommand(request);
		CommandFactory factory = new CommandFactory();
		Command command = factory.getCommand(cName);
		command.execute(request, response);
	}
	
	private String parceCommand(HttpServletRequest request) {
		String command = null;
		if (request.getParameter(ControllerConstant.SIGN_UP) != null) {
			command = ControllerConstant.SIGN_UP;
		} else if (request.getParameter(ControllerConstant.SIGN_IN) != null) {
			command = ControllerConstant.SIGN_IN;
		} else if (request.getParameter(ControllerConstant.SIGN_OUT) != null) {
			command = ControllerConstant.SIGN_OUT;
		} else if (request.getParameter(ControllerConstant.LOCALE) != null) {
			command = ControllerConstant.LOCALE;
		} else if (request.getParameter(ControllerConstant.ADD_DRINK) != null) {
			command = ControllerConstant.ADD_DRINK;
		} else if (request.getParameter(ControllerConstant.ADD_INGREDIENT) != null) {
			command = ControllerConstant.ADD_INGREDIENT;
		} else if (request.getParameter(ControllerConstant.ADD_DRINK_TO_ORDER) != null) {
			command = ControllerConstant.ADD_DRINK_TO_ORDER;
		} else if (request.getParameter(ControllerConstant.PUSH_ORDER) != null) {
			command = ControllerConstant.PUSH_ORDER;
		} else {
			command = ControllerConstant.ERROR;
		}
		return command;
	}
	
}
