package by.training.epam.service;

public interface UserService {
	
	public void createUser(String name, String pass) throws ServiceException;
	public void readUser(String name, String pass) throws ServiceException;
	
}
