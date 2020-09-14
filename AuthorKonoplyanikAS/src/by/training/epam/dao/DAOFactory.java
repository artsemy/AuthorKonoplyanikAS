package by.training.epam.dao;

import by.training.epam.dao.impl.MenuDAOImpl;
import by.training.epam.dao.impl.OrderDAOImpl;
import by.training.epam.dao.impl.UserDAOImpl;

public class DAOFactory {
	
	private UserDAO userDAO;
	private MenuDAO menuDAO;
	private OrderDAO orderDAO;
	
	private static DAOFactory instance;
	
	private DAOFactory() throws DAOException {
		userDAO = new UserDAOImpl();
		menuDAO = new MenuDAOImpl();
		orderDAO =  new OrderDAOImpl();
	}
	
	public static synchronized DAOFactory getInstance() throws DAOException  {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public MenuDAO getMenuDAO() {
		return menuDAO;
	}
	
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}
	
}
