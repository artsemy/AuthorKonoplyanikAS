package by.training.epam.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.Drink;
import by.training.epam.bean.DrinkIngredient;
import by.training.epam.bean.DrinkStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.OrderService;
import by.training.epam.service.ServiceFactory;

public class PreAddDrink implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		saveDrinkStoreToSession(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.ADD_DRINK_PAGE);
		dispatcher.forward(request, response);
	}
	
	private void saveDrinkStoreToSession(HttpServletRequest request) {
		DrinkStore drinkStore = buildDrinkStore(request);
		HttpSession session = request.getSession();
		session.setAttribute(ControllerConstant.DRINK_STORE, drinkStore);
	}
	
	private DrinkStore buildDrinkStore(HttpServletRequest request) {
		Drink drink = buildDrink(request);
		DrinkStore drinkStore = new DrinkStore();
		drinkStore.setDrink(drink);
		return drinkStore;
	}
	
	private Drink buildDrink(HttpServletRequest request) {
		Drink drink = new Drink();
		int drinkMenuId = Integer.parseInt(request.getParameter(ControllerConstant.COFFEE_ID));
		drink.setDrinkMenuId(drinkMenuId);
		return drink;
	}
	
}
