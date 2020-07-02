package by.training.epam.service;

import java.util.List;

import by.training.epam.bean.DrinkMenuItem;
import by.training.epam.bean.ExtraMenuItem;

public interface MenuService {
	
	public List<DrinkMenuItem> getMenu() throws ServiceException;
	public ExtraMenuItem readExtraMenuItem(int extraMenuId) throws ServiceException;
	public DrinkMenuItem readDrinkMenuItem(int drinkMenuId) throws ServiceException;

}
