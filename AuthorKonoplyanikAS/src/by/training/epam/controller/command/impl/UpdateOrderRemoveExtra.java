package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.DrinkStore;
import by.training.epam.bean.ExtraStore;
import by.training.epam.bean.UserStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.OrderService;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;

public class UpdateOrderRemoveExtra implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url;
		try {
			int removedExtraPrice = countRemovedExtraPrice(request);
			updateDrinkExtraStatus(request);
			updateOrderPrice(request);
			updateUserWallet(request, removedExtraPrice);
			setParams(request);
			url = ControllerConstant.UPDATE_ORDER_DRINK_PAGE;
		} catch (ServiceException e) {
			url = ControllerConstant.ERROR_PAGE;
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void updateDrinkExtraStatus(HttpServletRequest request) throws ServiceException {
		int extraId = Integer.parseInt(request.getParameter(ControllerConstant.EXTRA_ID_TO_REMOVE));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		orderService.updateOrderRemoveExtraStatus(extraId);
	}
	
	private void updateOrderPrice(HttpServletRequest request) throws ServiceException {
		int drinkId = Integer.parseInt(request.getParameter(ControllerConstant.DRINK_ID_TO_UPDATE));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		orderService.recountOrderPriceByDrink(drinkId);
	}
	
	private void setParams(HttpServletRequest request) throws ServiceException {
		int drinkId = Integer.parseInt(request.getParameter(ControllerConstant.DRINK_ID_TO_UPDATE));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		DrinkStore drinkStore = orderService.buildDrinkStore(drinkId);
		request.setAttribute(ControllerConstant.DRINK_STORE_TO_UPDATE, drinkStore);
	}
	
	private int countRemovedExtraPrice(HttpServletRequest request) throws ServiceException {
		int price = 0;
		int extraId = Integer.parseInt(request.getParameter(ControllerConstant.EXTRA_ID_TO_REMOVE));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		ExtraStore extraStore = orderService.buildExtraStore(extraId);
		price = extraStore.getExtraMenuItem().getPrice();
		return price;
	}
	
	private void updateUserWallet(HttpServletRequest request, int removedExtraPrice) throws ServiceException {
		HttpSession httpSession = request.getSession();
		UserStore userStore = (UserStore) httpSession.getAttribute(ControllerConstant.USER_STORE);
		int userId = userStore.getId();
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		userService.updateWalletAddMoney(userId, removedExtraPrice);
		int updatedBalance = userService.readBalance(userId);
		userStore.setBalance(updatedBalance);
	}

}
