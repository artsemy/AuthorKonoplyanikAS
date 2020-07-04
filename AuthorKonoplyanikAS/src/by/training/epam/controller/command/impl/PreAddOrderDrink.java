package by.training.epam.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
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

public class PreAddOrderDrink implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		saveOrderStoreToSession(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.MAIN_PAGE);
		dispatcher.forward(request, response);
	}
	
	private void saveOrderStoreToSession(HttpServletRequest request) {
		OrderStore orderStore = getOrderStoreFromSession(request);
		DrinkStore drinkStore = getDrinkStoreFromSession(request);
		List<DrinkStore> drinks = orderStore.getDrinks();
		if (drinks == null) {
			drinks = new ArrayList<DrinkStore>();
			orderStore.setDrinks(drinks);
		}
		int id = getNextId(drinks);
		drinkStore.setId(id);
		drinks.add(drinkStore);
	}
	
	private OrderStore getOrderStoreFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		OrderStore orderStore = (OrderStore) session.getAttribute(ControllerConstant.ORDER_STORE);
		if (orderStore == null) {
			orderStore = new OrderStore();
			session.setAttribute(ControllerConstant.ORDER_STORE, orderStore);
		}
		return orderStore;
	}
	
	private DrinkStore getDrinkStoreFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		DrinkStore drinkStore = (DrinkStore) session.getAttribute(ControllerConstant.DRINK_STORE);
		return drinkStore;
	}
	
	private int getNextId(List<DrinkStore> list) {
		return list.get(list.size()).getId() + 1;
	}
	
}
