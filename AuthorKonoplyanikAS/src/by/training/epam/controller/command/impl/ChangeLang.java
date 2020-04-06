package by.training.epam.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.controller.command.Command;

public class ChangeLang implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = parceLang(request);
		session.setAttribute("locale", lang);
		String url = request.getServletPath();
//		RequestDispatcher dispatcher = request.getRequestDispatcher();
//		dispatcher.forward(request, response);
		String s = request.getHeader("referer");
		response.sendRedirect(s);
	}
	
	private String parceLang(HttpServletRequest request) {
		String lang = "en";
		if (request.getParameter("lang_ru") != null) {
			lang = "ru";
		}
		return lang;
	}

}
