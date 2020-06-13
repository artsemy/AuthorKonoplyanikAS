package by.training.epam.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.epam.controller.command.Command;
import by.training.epam.controller.command.CommandFactory;

public class Controller {
	
	public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cName = request.getParameter(ControllerConstant.COMMAND);
		CommandFactory factory = new CommandFactory();
		Command command = factory.getCommand(cName);
		command.execute(request, response);
	}
	
}
