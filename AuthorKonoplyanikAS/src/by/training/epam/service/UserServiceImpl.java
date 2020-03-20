package by.training.epam.service;

import by.training.epam.bean.User;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.UserDAO;

public class UserServiceImpl implements UserService{
	
	@Override
	public boolean createUser(String name, String pass) throws ServiceException {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			User user = userDAO.readUser(name);
			if (user != null) {
				return false;
			}
			userDAO.createUser(name, pass);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	@Override
	public boolean readUser(String name, String pass) throws ServiceException {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			User user = userDAO.readUser(name);
			if (user == null || !pass.equals(user.getPassword())) {
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}
	
}
