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
		if (request.getParameter("sign_up") != null) {
			command = "sign_up";
		} else if (request.getParameter("sign_in") != null) {
			command = "sign_in";
		} else {
			command = "sign_out";
		}
		return command;
	}
	
}
