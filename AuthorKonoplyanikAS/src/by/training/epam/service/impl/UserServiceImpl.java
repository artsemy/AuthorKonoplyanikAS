package by.training.epam.service.impl;

import by.training.epam.bean.User;
import by.training.epam.bean.UserStore;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.MenuDAO;
import by.training.epam.dao.UserDAO;
import by.training.epam.service.ServiceException;
import by.training.epam.service.UserService;
import by.training.epam.service.validator.UserValidator;

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
		UserValidator userValidator = new UserValidator();
		boolean result = userValidator.validateSignUp(user);
		if (!result) {
			return result;
		}
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			int walletId = userDAO.createWallet();
			user.setWalletId(walletId);
			user.setRoleId(2); //set user role
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
			int wallet = userDAO.readBalance(user.getWalletId());
			int id = user.getUserId();
			userStore = new UserStore(name, role, wallet, id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return userStore;
	}

	@Override
	public int readBalance(int userId) throws ServiceException {
		int balance;
		DAOFactory daoFactory;
		try {
			daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			int walletId = userDAO.readWalletId(userId);
			balance = userDAO.readBalance(walletId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return balance;
	}

	@Override
	public void updateWallet(int userId, int balance) throws ServiceException {
		DAOFactory daoFactory;
		try {
			daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			int walletId = userDAO.readWalletId(userId);
			userDAO.updateWallet(walletId, balance);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateWalletAddMoney(int userId, int money) throws ServiceException {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			int walletId = userDAO.readWalletId(userId);
			int freshBalance = userDAO.readBalance(walletId) + money;
			userDAO.updateWallet(walletId, freshBalance);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
}
