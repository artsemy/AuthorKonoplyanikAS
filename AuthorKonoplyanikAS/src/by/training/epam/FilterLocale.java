package by.training.epam;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.epam.controller.ControllerConstant;

public class FilterLocale implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session.getAttribute(ControllerConstant.SA_LOCALE) == null) {
			session.setAttribute(ControllerConstant.SA_LOCALE, ControllerConstant.EN);
		}
		chain.doFilter(request, response);
	}

}
