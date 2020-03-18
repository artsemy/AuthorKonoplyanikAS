package by.training.epam.dao;

public class DAOFactory {
	
	UserDAO userDAO;
	
	private static DAOFactory instance;
	
	private DAOFactory() throws DAOException {
		userDAO = new UserDAOImpl();
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
	
}
