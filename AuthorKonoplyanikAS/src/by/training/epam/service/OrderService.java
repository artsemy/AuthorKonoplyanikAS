package by.training.epam.service;

import by.training.epam.bean.DrinkStore;
import by.training.epam.bean.ExtraStore;
import by.training.epam.bean.OrderStore;
import by.training.epam.bean.UserStore;

public interface OrderService {
	
	public void createOrder(OrderStore orderStore, UserStore userStore) throws ServiceException;
	
	public OrderStore readActiveOrder(int userId) throws ServiceException; //fix
	
	public void updateOrderRemoveDrinkStatus(int orderId, int drinkId) throws ServiceException;
	
	public void updateOrderRemoveExtraStatus(int extraId) throws ServiceException;
	
	public int countDrinkWithExtraPrice(int drinkId) throws ServiceException;
	
	public void recountOrderPriceByOrder(int orderId) throws ServiceException;
	
	public void recountOrderPriceByDrink(int drinkId) throws ServiceException;
	
	public DrinkStore buildDrinkStore(int drinkId) throws ServiceException;
	
	public ExtraStore buildExtraStore(int drinkExtraId) throws ServiceException;
	
}
