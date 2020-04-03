package by.training.epam.service.impl;

import by.training.epam.bean.User;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.UserDAO;
import by.training.epam.service.ServiceException;
import by.training.epam.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Override
	public boolean readUser(String login, String password) throws ServiceException {
		if (login == null || password == null) {
			return false;
		}
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			User resultUser = userDAO.readUser(login, password);
			if (resultUser == null) {
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}
	
	@Override
	public boolean createUser(User user) throws ServiceException {
		if (user == null) {
			return false;
		}
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			User resultUser = userDAO.readUser(user.getLogin());
			if (resultUser != null) {
				return false;
			}
			userDAO.createUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}
	
}
