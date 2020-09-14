package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.epam.bean.DrinkStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.OrderService;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;

public class GotoUpdateOrderDrinkPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url;
		try {
			DrinkStore drinkStore = buildDrinkStore(request);
			setParam(request, drinkStore);
			url = ControllerConstant.UPDATE_ORDER_DRINK_PAGE;
		} catch (ServiceException e) {
			url = ControllerConstant.ERROR_PAGE;
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private DrinkStore buildDrinkStore(HttpServletRequest request) throws ServiceException {
		int drinkId = Integer.parseInt(request.getParameter(ControllerConstant.DRINK_ID_TO_UPDATE));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		DrinkStore drinkStore = orderService.buildDrinkStore(drinkId);
		return drinkStore;
	}
	
	private void setParam(HttpServletRequest request, DrinkStore drinkStore) {
		request.setAttribute(ControllerConstant.DRINK_STORE_TO_UPDATE, drinkStore);
	}

}
