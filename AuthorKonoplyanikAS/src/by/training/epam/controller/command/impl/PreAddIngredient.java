package by.training.epam.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.DrinkIngredient;
import by.training.epam.bean.DrinkStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;

public class PreAddIngredient implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addIngredientToDrinkStore(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.ADD_DRINK_PAGE);
		dispatcher.forward(request, response);
	}
	
	private void addIngredientToDrinkStore(HttpServletRequest request) {
		DrinkStore drinkStore = getDrinkStore(request);
		DrinkIngredient drinkIngredient = buildDrinkIngredient(request);
		drinkStore.getIngredients().add(drinkIngredient);
	}
	
	private DrinkStore getDrinkStore(HttpServletRequest request) {
		HttpSession session = request.getSession();
		DrinkStore drinkStore = (DrinkStore) session.getAttribute(ControllerConstant.DRINK_STORE);
		return drinkStore;
	}
	
	private DrinkIngredient buildDrinkIngredient(HttpServletRequest request) {
		int ingredientId = Integer.parseInt(request.getParameter(ControllerConstant.INGREDIENT_ID));
		DrinkIngredient drinkIngredient = new DrinkIngredient();
		drinkIngredient.setDrinkIngredientId(ingredientId);
		return drinkIngredient;
	}

}
