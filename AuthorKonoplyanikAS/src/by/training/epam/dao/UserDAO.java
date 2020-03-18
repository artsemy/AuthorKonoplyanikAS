package by.training.epam.dao;

import java.util.Map;

public interface UserDAO {
	
	public Map<String, String> getUsers() throws DAOException;
	
	public void createUser(String name, String pass) throws DAOException;
	
}
