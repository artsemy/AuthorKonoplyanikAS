package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.DrinkMenuItem;
import by.training.epam.bean.DrinkStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.MenuService;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;

public class PreAddDrink implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		saveDrinkStoreToSession(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.DRINK_PAGE);
		dispatcher.forward(request, response);
	}
	
	private void saveDrinkStoreToSession(HttpServletRequest request) {
		DrinkStore drinkStore = buildDrinkStore(request);
		HttpSession session = request.getSession();
		session.setAttribute(ControllerConstant.DRINK_STORE, drinkStore);
	}
	
	private DrinkStore buildDrinkStore(HttpServletRequest request) {
		DrinkMenuItem drinkMenuItem = readDrinkMenuItem(request);
		DrinkStore drinkStore = new DrinkStore();
		drinkStore.setDrinkMenuItem(drinkMenuItem);
		return drinkStore;
	}
	
	private DrinkMenuItem readDrinkMenuItem(HttpServletRequest request) {
		DrinkMenuItem drinkMenuItem;
		int drinkMenuId = Integer.parseInt(request.getParameter(ControllerConstant.COFFEE_ID));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MenuService menuService = serviceFactory.getMenuService();
		try {
			drinkMenuItem = menuService.readDrinkMenuItem(drinkMenuId);
		} catch (ServiceException e) {
			drinkMenuItem = null;
		}
		return drinkMenuItem;
	}
	
}
