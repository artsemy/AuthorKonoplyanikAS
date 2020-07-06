package by.training.epam.dao;

import java.util.List;

import by.training.epam.bean.Delivery;
import by.training.epam.bean.Drink;
import by.training.epam.bean.DrinkExtra;
import by.training.epam.bean.Order;
import by.training.epam.bean.OrderDrink;

public interface OrderDAO {
	
	public int createDrink(Drink drink) throws DAOException;
	public int createDrinkExtra(DrinkExtra drinkIngredient) throws DAOException;
	public int createOrder(Order order) throws DAOException;
	public int createOrderDrink(OrderDrink orderDrink) throws DAOException;
	public int createDelivery(Delivery delivery) throws DAOException;
	
	public Drink readDrink(int drinkId) throws DAOException;
	public List<Drink> readDrinkByOrder(int orderId) throws DAOException;
	public List<DrinkExtra> readDrinkExtra(int drinkId) throws DAOException;
	public Order readOrder(int orderId) throws DAOException;
	public List<Order> readOrderByUser(int userId) throws DAOException;
	public OrderDrink readOrderDrink(int orderDrinkId) throws DAOException;
	public List<OrderDrink> readOrderDrinkByOrder(int orderId) throws DAOException;
	public Delivery readDelivery(int deliveryId) throws DAOException;
	
	public Order readLastOrder() throws DAOException;
	
}
