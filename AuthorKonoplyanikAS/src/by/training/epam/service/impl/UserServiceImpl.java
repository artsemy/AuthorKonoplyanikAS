package by.training.epam.service.impl;

import by.training.epam.bean.User;
import by.training.epam.bean.UserStore;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.UserDAO;
import by.training.epam.service.ServiceException;
import by.training.epam.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Override
	public User readUser(User user) throws ServiceException {
		User resultUser = null;
		if (user.getLogin() == null || user.getPassword() == null) {
			return resultUser;
		}
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			resultUser = userDAO.readUser(user.getLogin(), user.getPassword());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return resultUser;
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

	@Override
	public UserStore buildUserStore(User user) throws ServiceException {
		UserStore userStore = null;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			String name = user.getName();
			String role = userDAO.readRole(user.getRoleId());
			int wallet = userDAO.readWallet(user.getWalletId());
			userStore = new UserStore(name, role, wallet);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return userStore;
	}
	
}
