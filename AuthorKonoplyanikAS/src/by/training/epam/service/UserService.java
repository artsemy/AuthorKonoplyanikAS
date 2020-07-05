package by.training.epam.service;

import by.training.epam.bean.User;
import by.training.epam.bean.UserStore;

public interface UserService {
	
	public boolean createUser(User user) throws ServiceException;
	public User readUser(User user) throws ServiceException;
	public UserStore buildUserStore(User user) throws ServiceException;
	
	public int readBalance(int userId) throws ServiceException;
	
}
