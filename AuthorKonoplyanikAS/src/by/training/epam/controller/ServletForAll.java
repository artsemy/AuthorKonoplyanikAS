package by.training.epam.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.epam.controller.command.Command;
import by.training.epam.controller.command.CommandFactory;

public class ServletForAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletForAll() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		start(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		start(request, response);
	}
	
	private void start(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sCommand = request.getParameter(ControllerConstant.COMMAND);
		CommandFactory factory = CommandFactory.getInstance();
		Command command = factory.getCommand(sCommand);
		command.execute(request, response);
	}

}
