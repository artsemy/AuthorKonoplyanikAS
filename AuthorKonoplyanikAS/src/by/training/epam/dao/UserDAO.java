package by.training.epam.dao;

import by.training.epam.bean.User;

public interface UserDAO {
	
	public User readUser(String name) throws DAOException;
	
	public void createUser(String name, String pass) throws DAOException;
	
}
