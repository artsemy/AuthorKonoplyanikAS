package by.training.epam.dao;

import by.training.epam.bean.User;

public interface UserDAO {
	
	public User readUser(String login) throws DAOException;
	public User readUser(String login, String password) throws DAOException;
	public void createUser(User user) throws DAOException;
	
	public String readRole(int roleId) throws DAOException;
	public int readWallet(int walletId) throws DAOException;
	
}
