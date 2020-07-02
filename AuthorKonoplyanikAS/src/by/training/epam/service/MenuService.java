package by.training.epam.service;

import java.util.List;

import by.training.epam.bean.DrinkMenuItem;
import by.training.epam.bean.DrinkStore;
import by.training.epam.bean.ExtraMenuItem;
import by.training.epam.bean.OrderStore;

public interface MenuService {
	
	public List<DrinkMenuItem> getMenu() throws ServiceException;
	public ExtraMenuItem readExtraMenuItem(int extraMenuId) throws ServiceException;
	public DrinkMenuItem readDrinkMenuItem(int drinkMenuId) throws ServiceException;
	
	public int countPrice(OrderStore orderStore);
	public int countPrice(DrinkStore drinkStore);
	public void setOrderPrice(OrderStore orderStore, int price);

}
