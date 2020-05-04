package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;

public class ChangeLang implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = parceLang(request);
		session.setAttribute(ControllerConstant.SA_LOCALE, lang);
		String url = request.getParameter(ControllerConstant.PAGE_URL);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private String parceLang(HttpServletRequest request) {
		String lang = ControllerConstant.EN;
		if (request.getParameter(ControllerConstant.LANG_RU) != null) {
			lang = ControllerConstant.RU;
		}
		return lang;
	}

}
