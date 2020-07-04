package by.training.epam.service;

import by.training.epam.bean.OrderStore;
import by.training.epam.bean.UserStore;

public interface OrderService {
	
	public void createOrder(OrderStore orderStore, UserStore userStore) throws ServiceException;
	
	public OrderStore readLastOrder(); //fix
	
}
