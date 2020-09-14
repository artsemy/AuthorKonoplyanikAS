package by.training.epam.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.OrderStore;
import by.training.epam.bean.UserStore;
import by.training.epam.service.OrderService;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;

public class FilterMainPage implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		try {
			addActiveOrderToRequest(req);
		} catch (ServiceException e) {
			//fix
		} finally {
			chain.doFilter(request, response);
		}
	}
	
	private void addActiveOrderToRequest(HttpServletRequest request) throws ServiceException {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute(ControllerConstant.USER_STORE) != null) {
			OrderStore orderStore = readActiveOrder(request);
			request.setAttribute(ControllerConstant.ACTIVE_ORDER_STORE, orderStore);
		}
	}
	
	private OrderStore readActiveOrder(HttpServletRequest request) throws ServiceException {
		HttpSession httpSession = request.getSession();
		UserStore userStore = (UserStore) httpSession.getAttribute(ControllerConstant.USER_STORE);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		OrderStore orderStore = orderService.readActiveOrder(userStore.getId());
		return orderStore;
	}

}
