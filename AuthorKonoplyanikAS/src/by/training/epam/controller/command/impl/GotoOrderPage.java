package by.training.epam.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.bean.OrderStore;
import by.training.epam.controller.ControllerConstant;
import by.training.epam.controller.command.Command;
import by.training.epam.service.MenuService;
import by.training.epam.service.ServiceFactory;

public class GotoOrderPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		countPrice(request);
		String url = ControllerConstant.ORDER_PAGE;
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private void countPrice(HttpServletRequest request) {
		HttpSession session = request.getSession();
		OrderStore orderStore = (OrderStore) session.getAttribute(ControllerConstant.ORDER_STORE);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MenuService menuService = serviceFactory.getMenuService();
		int price = menuService.countPrice(orderStore);
		request.setAttribute(ControllerConstant.PRICE, price);
	}

}
