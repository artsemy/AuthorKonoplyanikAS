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
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.OrderService;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;

public class PushOrder implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			saveOrderToDB(request);
		} catch (ServiceException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
		setRequestAttribute(request); //fix
		clearSessionAttribute(request);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.MAIN_PAGE);
		dispatcher.forward(request, response);
	}
	
	private void saveOrderToDB(HttpServletRequest request) throws ServiceException {
		HttpSession session = request.getSession();
		OrderStore orderStore = (OrderStore) session.getAttribute(ControllerConstant.ORDER_STORE);
		buildDelivery(request);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		orderService.createOrder(orderStore);
	}
	
	private void buildDelivery(HttpServletRequest request) {
		HttpSession session = request.getSession();
		OrderStore orderStore = (OrderStore) session.getAttribute(ControllerConstant.ORDER_STORE);
		orderStore.setDelivery(new Delivery()); // fix
		Delivery delivery = orderStore.getDelivery();
		delivery.setStatus("start");
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
	
	private void clearSessionAttribute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(ControllerConstant.DRINK_STORE);
		session.removeAttribute(ControllerConstant.ORDER_STORE);
	}
	
	private void setRequestAttribute(HttpServletRequest request) { //fix
		HttpSession session = request.getSession();
		OrderStore orderStore = (OrderStore) session.getAttribute(ControllerConstant.ORDER_STORE);
		request.setAttribute("active_order_store", orderStore);
	}
	
}
