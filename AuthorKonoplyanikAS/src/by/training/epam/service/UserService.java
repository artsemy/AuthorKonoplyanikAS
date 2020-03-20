package by.training.epam.service;

public interface UserService {
	
	public boolean createUser(String name, String pass) throws ServiceException;
	public boolean readUser(String name, String pass) throws ServiceException;
	
}
