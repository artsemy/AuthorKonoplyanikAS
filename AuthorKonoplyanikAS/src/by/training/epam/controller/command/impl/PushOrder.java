package by.training.epam.controller.command.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.Delivery;
import by.training.epam.bean.OrderStore;
import by.training.epam.bean.UserStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.OrderService;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;

public class PushOrder implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url;
		try {
			saveOrderToDB(request);
			updateSessionAttribute(request);
			url = ControllerConstant.MAIN_PAGE;
		} catch (ServiceException e) {
			url = ControllerConstant.ERROR_PAGE;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private void saveOrderToDB(HttpServletRequest request) throws ServiceException {
		HttpSession session = request.getSession();
		OrderStore orderStore = (OrderStore) session.getAttribute(ControllerConstant.ORDER_STORE);
		UserStore userStore = (UserStore) session.getAttribute(ControllerConstant.USER_STORE);
		setDelivery(request, orderStore);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		orderService.createOrder(orderStore, userStore);
	}
	
	private void setDelivery(HttpServletRequest request, OrderStore orderStore) {
		Delivery delivery = new Delivery();
		orderStore.setDelivery(delivery);
		delivery.setStatus("added");
		Date start = new Date();
		delivery.setStart(convertDate(start));
		String endDate = request.getParameter(ControllerConstant.DELIVERY_END_DATE_TIME);
		Date end;
		try {
			end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(endDate);
		} catch (ParseException e) {
			end = start;
		}
		delivery.setEnd(convertDate(end));
		String type = request.getParameter(ControllerConstant.DELIVERY_TYPE); //fix
		delivery.setType(type);
	}
	
	public java.sql.Date convertDate(Date date) {
		java.sql.Date res = new java.sql.Date(date.getTime());
		return res;
	}
	
	private void updateSessionAttribute(HttpServletRequest request) throws ServiceException {
		HttpSession session = request.getSession();
		session.removeAttribute(ControllerConstant.DRINK_STORE);
		session.removeAttribute(ControllerConstant.ORDER_STORE);
		UserStore userStore = (UserStore) session.getAttribute(ControllerConstant.USER_STORE);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		int balance = userService.readBalance(userStore.getId());
		userStore.setBalance(balance);
	}
	
}
