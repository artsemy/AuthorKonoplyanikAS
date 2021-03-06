package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.OrderStore;
import by.training.epam.bean.UserStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.OrderService;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;

public class GotoUpdateOrderPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url;
		try {
			setOrderStore(request);
			url = ControllerConstant.UPDATE_ORDER_PAGE;
		} catch (ServiceException e) {
			url = ControllerConstant.ERROR_PAGE;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private void setOrderStore(HttpServletRequest request) throws ServiceException {
		HttpSession httpSession = request.getSession();
		UserStore userStore = (UserStore) httpSession.getAttribute(ControllerConstant.USER_STORE);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		OrderStore orderStore = orderService.readActiveOrder(userStore.getId());
		request.setAttribute(ControllerConstant.ACTIVE_ORDER_STORE, orderStore);
	}

}
