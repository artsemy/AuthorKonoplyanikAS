package by.training.epam.dao;

import by.training.epam.bean.Delivery;
import by.training.epam.bean.Drink;
import by.training.epam.bean.DrinkIngredient;
import by.training.epam.bean.Order;

public interface OrderDAO {
	
	public int createDrink(Drink drink) throws DAOException;
	public int createDrinkIngredient(DrinkIngredient drinkIngredient) throws DAOException;
	public int createOrder(Order order) throws DAOException;
	public int createOrderDrink(int orderId, int drinkId, String status, int size) throws DAOException;
	public int createDelivery(Delivery delivery) throws DAOException;
//	public int getNextId(String tableName, String columnName) throws DAOException;
	
}
