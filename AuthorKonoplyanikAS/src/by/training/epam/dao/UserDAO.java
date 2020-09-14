package by.training.epam.dao;

import by.training.epam.bean.User;

public interface UserDAO {
	
	public User readUser(String login) throws DAOException;
	public User readUser(String login, String password) throws DAOException;
	public void createUser(User user) throws DAOException;
	
	public String readRole(int roleId) throws DAOException;
	public int readBalance(int walletId) throws DAOException;
	
	public int readWalletId(int userId) throws DAOException;
	public void updateWallet(int walletId, int balance) throws DAOException;
	public int createWallet() throws DAOException;
	
}
