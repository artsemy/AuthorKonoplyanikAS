package by.training.epam.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.DrinkStore;
import by.training.epam.bean.ExtraStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;

public class RemoveIngredient implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		removeExtra(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.DRINK_PAGE);
		dispatcher.forward(request, response);
	}
	
	private void removeExtra(HttpServletRequest request) {
		int extraStoreId = Integer.parseInt(request.getParameter(ControllerConstant.INGREDIENT_REMOVE_ID));
		HttpSession httpSession = request.getSession();
		DrinkStore drinkStore = (DrinkStore) httpSession.getAttribute(ControllerConstant.DRINK_STORE);
		List<ExtraStore> list = drinkStore.getExtra();
		for (ExtraStore extraStore : list) {
			if (extraStore.getId() == extraStoreId) {
				list.remove(extraStore);
				break;
			}
		}
	}

}
