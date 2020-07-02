package by.training.epam.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.DrinkStore;
import by.training.epam.bean.OrderStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.MenuService;
import by.training.epam.service.ServiceFactory;

public class RemoveDrink implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		removeDrink(request);
		countPrice(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.ORDER_PAGE);
		dispatcher.forward(request, response);
	}
	
	private void removeDrink(HttpServletRequest request) {
		int drinkStoreId = Integer.parseInt(request.getParameter(ControllerConstant.DRINK_REMOVE_ID));
		HttpSession session = request.getSession();
		OrderStore orderStore = (OrderStore) session.getAttribute(ControllerConstant.ORDER_STORE);
		List<DrinkStore> list = orderStore.getDrinks();
		for (DrinkStore drinkStore : list) {
			if (drinkStore.getId() == drinkStoreId) {
				list.remove(drinkStore);
				break;
			}
		}
	}
	
	private void countPrice(HttpServletRequest request) {
		HttpSession session = request.getSession();
		OrderStore orderStore = (OrderStore) session.getAttribute(ControllerConstant.ORDER_STORE);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MenuService menuService = serviceFactory.getMenuService();
		int price = menuService.countPrice(orderStore);
		menuService.setOrderPrice(orderStore, price);
		request.setAttribute(ControllerConstant.PRICE, price);
	}

}
