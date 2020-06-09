package by.training.epam.dao;

import java.util.List;

import by.training.epam.bean.DrinkMenuItem;

public interface MenuDAO {
	
	public List<DrinkMenuItem> getMenu() throws DAOException;
	
}
