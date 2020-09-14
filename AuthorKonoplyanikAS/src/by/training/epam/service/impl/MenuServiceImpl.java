package by.training.epam.service.impl;

import java.util.List;

import by.training.epam.bean.DrinkMenuItem;
import by.training.epam.bean.DrinkStore;
import by.training.epam.bean.ExtraMenuItem;
import by.training.epam.bean.ExtraStore;
import by.training.epam.bean.Order;
import by.training.epam.bean.OrderStore;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.MenuDAO;
import by.training.epam.service.MenuService;
import by.training.epam.service.ServiceException;

public class MenuServiceImpl implements MenuService{

	@Override
	public List<DrinkMenuItem> getMenu() throws ServiceException {
		List<DrinkMenuItem> result = null;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			MenuDAO menuDAO = daoFactory.getMenuDAO();
			result = menuDAO.getMenu();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public ExtraMenuItem readExtraMenuItem(int extraMenuId) throws ServiceException {
		ExtraMenuItem extraMenuItem;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			MenuDAO menuDAO = daoFactory.getMenuDAO();
			extraMenuItem = menuDAO.readExtraMenuItem(extraMenuId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return extraMenuItem;
	}

	@Override
	public DrinkMenuItem readDrinkMenuItem(int drinkMenuId) throws ServiceException {
		DrinkMenuItem drinkMenuItem;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			MenuDAO menuDAO = daoFactory.getMenuDAO();
			drinkMenuItem = menuDAO.readDrinkMenuItem(drinkMenuId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drinkMenuItem;
	}

	@Override
	public int countPrice(OrderStore orderStore) { //fix status
		int price = 0;
		if (orderStore != null) {
			List<DrinkStore> drinks = orderStore.getDrinks();
			if(drinks != null) {
				for (DrinkStore drinkStore : drinks) {
					price += countPrice(drinkStore);
				}
			}
		}
		return price;
	}

	@Override
	public int countPrice(DrinkStore drinkStore) { //fix status
		int price = 0;
		if (drinkStore != null) {
			price += drinkStore.getDrinkMenuItem().getPrice();
			List<ExtraStore> extras = drinkStore.getExtra();
			if(extras != null) {
				for (ExtraStore extra : extras) {
					price += extra.getExtraMenuItem().getPrice();
				}
			}
		}
		return price;
	}

	@Override
	public void setOrderPrice(OrderStore orderStore, int price) {
		if(orderStore != null) {
			Order order = orderStore.getOrder();
			if(order == null) {
				order = new Order();
				orderStore.setOrder(order);
			}
			order.setPrice(price);
		}
	}

}
