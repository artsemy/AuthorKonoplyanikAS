package by.training.epam.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.DrinkIngredient;
import by.training.epam.bean.DrinkStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;

public class RemoveIngredient implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		removeIngredient(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.DRINK_PAGE);
		dispatcher.forward(request, response);
	}
	
	private void removeIngredient(HttpServletRequest request) {
		int portionId = Integer.parseInt(request.getParameter(ControllerConstant.INGREDIENT_REMOVE_ID));
		HttpSession httpSession = request.getSession();
		DrinkStore drinkStore = (DrinkStore) httpSession.getAttribute(ControllerConstant.DRINK_STORE);
		List<DrinkIngredient> list = drinkStore.getIngredients();
		DrinkIngredient ingredient = new DrinkIngredient();
		ingredient.setPortionId(portionId);
		list.remove(ingredient);
	}

}
