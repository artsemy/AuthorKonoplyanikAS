package by.training.epam;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.OrderStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.service.OrderService;
import by.training.epam.service.ServiceFactory;

public class FilterMainPage implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		addActiveOrderToSession(req);
		chain.doFilter(request, response);
	}
	
	private void addActiveOrderToSession(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute(ControllerConstant.USER_STORE) != null) {
			OrderStore orderStore = readActiveOrder(request);
			request.setAttribute(ControllerConstant.ACTIVE_ORDER_STORE, orderStore);
		}
	}
	
	private OrderStore readActiveOrder(HttpServletRequest request) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		OrderStore orderStore = orderService.readLastOrder();
		return orderStore;
	}

}
