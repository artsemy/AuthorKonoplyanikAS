package by.training.epam.service;

import java.util.List;

import by.training.epam.bean.DrinkMenuItem;

public interface MenuService {
	
	public List<DrinkMenuItem> getMenu() throws ServiceException;

}
