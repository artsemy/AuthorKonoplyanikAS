package by.training.epam.dao;

import java.util.List;

import by.training.epam.bean.DrinkMenuItem;
import by.training.epam.bean.ExtraMenuItem;

public interface MenuDAO {
	
	public List<DrinkMenuItem> getMenu() throws DAOException;
	public ExtraMenuItem readExtraMenuItem(int extraMenuId) throws DAOException;
	public DrinkMenuItem readDrinkMenuItem(int drinkMenuId) throws DAOException;
	
}
