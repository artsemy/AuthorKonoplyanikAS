package by.training.epam.service;

import by.training.epam.bean.User;

public interface UserService {
	
	public boolean createUser(User user) throws ServiceException;
	public boolean readUser(String login, String password) throws ServiceException;
	
}
