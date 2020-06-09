package by.training.epam.service.impl;

import java.util.List;

import by.training.epam.bean.DrinkMenuItem;
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

}
