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
	public OrderDrink readOrderDrinkById(int orderDrinkId) throws DAOException;
	public List<OrderDrink> readOrderDrinkByOrder(int orderId) throws DAOException;
	public Delivery readDelivery(int deliveryId) throws DAOException;
	public DrinkExtra readDrinkExtraById(int drinkExtraId) throws DAOException;
	
	public Order readLastOrder() throws DAOException;
	public OrderDrink readOrderDrinkByDrink(int drinkId) throws DAOException;
	
	public void updateOrderDrinkStatusByDrink(int drinkId, String status) throws DAOException;
	public void updateDrinkExtraStatusByDrink(int drinkId, String status) throws DAOException;
	public void updateDrinkExtraStatusById(int drinkExtraId, String status) throws DAOException;
	public void updateOrderStatus(int orderId, String status) throws DAOException;
	public void updateOrderPrice(int orderId, int price) throws DAOException;

	
}
