package by.training.epam.service;

import java.util.Map;

import by.training.epam.dao.DAOException;
import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.UserDAO;

public class UserServiceImpl implements UserService{
	
	@Override
	public void createUser(String name, String pass) throws ServiceException {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			Map<String, String> map = userDAO.getUsers();
			if (map.containsKey(name)) {
				throw new ServiceException("can't use this login");
			}
			userDAO.createUser(name, pass);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void readUser(String name, String pass) throws ServiceException {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			Map<String, String> map = userDAO.getUsers();
			if (!map.containsKey(name) || !pass.equals(map.get(name))) {
				throw new ServiceException("bad login or password");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
}
