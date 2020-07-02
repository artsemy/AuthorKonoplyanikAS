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
import by.training.epam.bean.ExtraMenuItem;
import by.training.epam.bean.ExtraStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.MenuService;
import by.training.epam.service.ServiceException;
import by.training.epam.service.ServiceFactory;

public class PreAddIngredient implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addExtraToDrinkStore(request);
		countPrice(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.DRINK_PAGE);
		dispatcher.forward(request, response);
	}
	
	private void addExtraToDrinkStore(HttpServletRequest request) {
		DrinkStore drinkStore = getDrinkStoreFromSession(request);
		ExtraStore extraStore = readExtraStore(request);
		List<ExtraStore> list = drinkStore.getExtra();
		if (list == null) {
			list = new ArrayList<ExtraStore>();
			drinkStore.setExtra(list);
		}
		int id = list.size() + 1; //fix
		extraStore.setId(id);
		list.add(extraStore);
	}
	
	private DrinkStore getDrinkStoreFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		DrinkStore drinkStore = (DrinkStore) session.getAttribute(ControllerConstant.DRINK_STORE);
		return drinkStore;
	}
	
	private ExtraStore readExtraStore(HttpServletRequest request) {
		ExtraStore extraStore = new ExtraStore();
		ExtraMenuItem extraMenuItem;
		int extraMenuId = Integer.parseInt(request.getParameter(ControllerConstant.INGREDIENT_ID));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MenuService menuService = serviceFactory.getMenuService();
		try {
			extraMenuItem = menuService.readExtraMenuItem(extraMenuId);
		} catch (ServiceException e) {
			extraMenuItem = null;
		}
		extraStore.setExtraMenuItem(extraMenuItem);
		return extraStore;
	}
	
	private void countPrice(HttpServletRequest request) {
		HttpSession session = request.getSession();
		DrinkStore drinkStore = (DrinkStore) session.getAttribute(ControllerConstant.DRINK_STORE);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MenuService menuService = serviceFactory.getMenuService();
		int price = menuService.countPrice(drinkStore);
		request.setAttribute(ControllerConstant.PRICE, price);
	}

}
