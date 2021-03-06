package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.UserStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;

public class SignOut implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearSessionAttribute(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.MAIN_PAGE);
		dispatcher.forward(request, response);
	}
	
	private void clearSessionAttribute(HttpServletRequest request) {
		HttpSession session =  request.getSession();
		session.removeAttribute(ControllerConstant.USER_STORE);
	}

}
