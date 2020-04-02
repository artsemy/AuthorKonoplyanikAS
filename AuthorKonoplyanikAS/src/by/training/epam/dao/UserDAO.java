package by.training.epam.dao;

import by.training.epam.bean.User;

public interface UserDAO {
	
	public User readUser(User user) throws DAOException;
	
	public void createUser(User user) throws DAOException;
	
}
