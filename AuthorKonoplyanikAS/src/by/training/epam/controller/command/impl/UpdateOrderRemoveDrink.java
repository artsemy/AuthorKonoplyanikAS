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
import by.training.epam.service.MenuService;
import by.training.epam.service.OrderService;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;

public class UpdateOrderRemoveDrink implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url;
		try {
			int removedDrinkPrice = countRemovedDrinkPrice(request);
			updateUnitStatus(request);
			updateOrderPrice(request);
			updateUserWallet(request, removedDrinkPrice);
			updateParams(request);
			url = ControllerConstant.UPDATE_ORDER_PAGE;
		} catch (ServiceException e) {
			url = ControllerConstant.ERROR_PAGE;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private int countRemovedDrinkPrice(HttpServletRequest request) throws ServiceException {
		int drinkId = Integer.parseInt(request.getParameter(ControllerConstant.DRINK_ID_TO_REMOVE));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		int price = orderService.countDrinkWithExtraPrice(drinkId);
		return price;
	}
	
	private void updateUnitStatus(HttpServletRequest request) throws ServiceException {
		int drinkId = Integer.parseInt(request.getParameter(ControllerConstant.DRINK_ID_TO_REMOVE));
		int orderId = Integer.parseInt(request.getParameter(ControllerConstant.ORDER_ID_TO_UPDATE));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		orderService.updateOrderRemoveDrinkStatus(orderId, drinkId);
	}
	
	private void updateUserWallet(HttpServletRequest request, int removedDrinkPrice) throws ServiceException {
		HttpSession httpSession = request.getSession();
		UserStore userStore = (UserStore) httpSession.getAttribute(ControllerConstant.USER_STORE);
		int userId = userStore.getId();
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		userService.updateWalletAddMoney(userId, removedDrinkPrice);
		int updatedBalance = userService.readBalance(userId);
		userStore.setBalance(updatedBalance);
	}
	
	private void updateOrderPrice(HttpServletRequest request) throws ServiceException {
		int orderId = Integer.parseInt(request.getParameter(ControllerConstant.ORDER_ID_TO_UPDATE));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		orderService.recountOrderPriceByOrder(orderId);
	}
	
	private void updateParams(HttpServletRequest request) throws ServiceException {
		HttpSession httpSession = request.getSession();
		UserStore userStore = (UserStore) httpSession.getAttribute(ControllerConstant.USER_STORE);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		OrderStore orderStore = orderService.readActiveOrder(userStore.getId());
		request.setAttribute(ControllerConstant.ACTIVE_ORDER_STORE, orderStore);
	}

}
