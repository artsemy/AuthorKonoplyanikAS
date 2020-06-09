package by.training.epam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.training.epam.bean.Delivery;
import by.training.epam.bean.Drink;
import by.training.epam.bean.DrinkIngredient;
import by.training.epam.bean.Order;
import by.training.epam.bean.OrderDrink;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.OrderDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;

public class OrderDAOImpl implements OrderDAO {
	
	private static final String INSERT_DRINK = "insert into `drink` values (?, ?)";
	private static final String INSERT_DRINK_INGREDIENT = "insert into `drink-has-ingredient` values (?, ?, ?, ?)";
	private static final String INSERT_ORDER = "insert into `order` values (?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_ORDER_DRINK = "insert into `order-has-drink` values (?, ?, ?, ?, ?)";
	private static final String INSERT_DELIVERY = "insert into `delivery` values (?, ?, ?, ?, ?)";
	
	private static final String SELECT_DRINK = "select * from `drink` where `drink-id` = ?";
	private static final String SELECT_DRINK_BY_ORDER = "select * from `order-has-drink` where `order-id` = ?";
	private static final String SELECT_DRINK_INGREDIENT = "select * from `drink-has-ingredient` where `drink-has-ingredient-id` = ?";
	private static final String SELECT_ORDER = "select * from `order` where `order-id` = ?";
	private static final String SELECT_ORDER_BY_USER = "select * from `order` where `user-id` = ?";
	private static final String SELECT_ORDER_DRINK = "select * from `order-has-drink` where `order-has-drink-id` = ?";
	private static final String SELECT_ORDER_DRINK_BY_ORDER = "select * from `order-has-drink` where `order-id` = ?";
	private static final String SELECT_DELIVERY = "select * from `delivery` where `delivery-id` = ?";
	
	private static final String DELETE_DRINK = "delete from `drink` where `drink-id` = ?";
	private static final String DELETE_DRINK_INGREDIENT = "delete from `drink-has-ingredient` where `drink-has-ingredient` = ?";
	private static final String DELETE_ORDER = "delete from `order` where `order-id` = ?";
	private static final String DELETE_ORDER_DRINK = "delete from `order-has-drink` where `order-has-drink-id` = ?";
	private static final String DELETE_DELIVERY = "delete from `delivery` where `delivery-id` = ?";

	@Override
	public int createDrink(Drink drink) throws DAOException {
		int drinkId = -1;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			drinkId = getNextId(connection, "drink", "`drink-id`");
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DRINK);
			preparedStatement.setInt(1, drinkId);
			preparedStatement.setInt(2, drink.getDrinkMenuId());
			preparedStatement.executeUpdate();
			connectionPool.closeConnection(connection, preparedStatement);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return drinkId;
	}
	
	@Override
	public int createDrinkIngredient(DrinkIngredient drinkIngredient) throws DAOException {
		int drinkIngredientId = -1;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			drinkIngredientId = getNextId(connection, "`drink-has-ingredient`", "`drink-has-ingredient-id`");
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DRINK_INGREDIENT);
			preparedStatement.setInt(1, drinkIngredientId);
			preparedStatement.setInt(2, drinkIngredient.getDrinkId());
			preparedStatement.setInt(3, drinkIngredient.getPortionId());
			preparedStatement.setInt(4, drinkIngredient.getPortionAmount());
			preparedStatement.executeUpdate();
			connectionPool.closeConnection(connection, preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return drinkIngredientId;
	}
	
	@Override
	public int createOrderDrink(OrderDrink orderDrink) throws DAOException {
		int orderDrinkId = -1;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			orderDrinkId = getNextId(connection, "`order-has-drink`", "`order-has-drink-id`");
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_DRINK);
			preparedStatement.setInt(1, orderDrinkId);
			preparedStatement.setInt(2, orderDrink.getOrderId());
			preparedStatement.setInt(3, orderDrink.getDrinkId());
			preparedStatement.setString(4, orderDrink.getStatus());
			preparedStatement.setInt(5, orderDrink.getSize());
			preparedStatement.executeUpdate();
			connectionPool.closeConnection(connection, preparedStatement);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return orderDrinkId;
	}
	
	@Override
	public int createOrder(Order order) throws DAOException {
		int orderId = -1;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
			orderId = getNextId(connection, "`order`", "`order-id`");
			preparedStatement.setInt(1, orderId);
			preparedStatement.setInt(2, order.getUserId());
			preparedStatement.setInt(3, order.getDeliveryId());
			preparedStatement.setInt(4, order.getPrice());
			preparedStatement.setString(5, order.getStatus());
			preparedStatement.setDate(6, order.getOpenDate());
			preparedStatement.setDate(7, order.getCloseDate());
			preparedStatement.executeUpdate();
			connectionPool.closeConnection(connection, preparedStatement);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return orderId;
	}

	@Override
	public int createDelivery(Delivery delivery) throws DAOException {
		int deliveryId = -1;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DELIVERY);
			deliveryId = getNextId(connection, "delivery", "`delivery-id`");
			preparedStatement.setInt(1, deliveryId);
			preparedStatement.setString(2, delivery.getType());
			preparedStatement.setString(3, delivery.getStatus());
			preparedStatement.setDate(4, delivery.getStart());
			preparedStatement.setDate(5, delivery.getEnd());
			preparedStatement.executeUpdate();
			connectionPool.closeConnection(connection, preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return deliveryId;
	}
	
	private int getNextId(Connection connection, String tableName, String columnName) throws SQLException {
		int nextId = -1;
		String sqlRequest = "select max(columnName) from tableName";
		sqlRequest = sqlRequest.replaceFirst("columnName", columnName);
		sqlRequest = sqlRequest.replaceFirst("tableName", tableName);
		PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
//		preparedStatement.setString(1, columnName); //fix
//		preparedStatement.setString(2, tableName); //fix
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		nextId = resultSet.getInt(1) + 1;
		resultSet.close();
		preparedStatement.close();
		return nextId;
	}

	@Override
	public Drink readDrink(int drinkId) throws DAOException {
		Drink drink = new Drink();
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DRINK);
			preparedStatement.setInt(1, drinkId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			drink.setDrinkId(resultSet.getInt(1));
			drink.setDrinkMenuId(resultSet.getInt(2));
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}  catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return drink;
	}
	
	@Override
	public List<Drink> readDrinkByOrder(int orderId) throws DAOException {
		List<Drink> drinks = new ArrayList<Drink>();
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DRINK_BY_ORDER);
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Drink drink = new Drink();
				drink.setDrinkId(resultSet.getInt(1));
				drink.setDrinkMenuId(resultSet.getInt(2));
				drinks.add(drink);
			}
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}  catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return drinks;
	}

	@Override
	public List<DrinkIngredient> readDrinkIngredient(int drinkId) throws DAOException {
		List<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DRINK_INGREDIENT);
			preparedStatement.setInt(1, drinkId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				DrinkIngredient drinkIngredient = new DrinkIngredient();
				drinkIngredient.setDrinkIngredientId(resultSet.getInt(1));
				drinkIngredient.setDrinkId(resultSet.getInt(2));
				drinkIngredient.setPortionId(resultSet.getInt(3));
				drinkIngredient.setPortionAmount(resultSet.getInt(4));
				ingredients.add(drinkIngredient);
			}
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}  catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return ingredients;
	}

	@Override
	public Order readOrder(int orderId) throws DAOException {
		Order order = new Order();
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER);
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			order.setOrderId(resultSet.getInt(1));
			order.setUserId(resultSet.getInt(2));
			order.setDeliveryId(resultSet.getInt(3));
			order.setPrice(resultSet.getInt(4));
			order.setStatus(resultSet.getString(5));
			order.setOpenDate(resultSet.getDate(6));
			order.setCloseDate(resultSet.getDate(7));
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}  catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return order;
	}
	
	@Override
	public List<Order> readOrderByUser(int userId) throws DAOException {
		List<Order> orders = new ArrayList<Order>();
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_USER);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt(1));
				order.setUserId(resultSet.getInt(2));
				order.setDeliveryId(resultSet.getInt(3));
				order.setPrice(resultSet.getInt(4));
				order.setStatus(resultSet.getString(5));
				order.setOpenDate(resultSet.getDate(6));
				order.setCloseDate(resultSet.getDate(7));
				orders.add(order);
			}
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}  catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return orders;
	}

	@Override
	public OrderDrink readOrderDrink(int orderDrinkId) throws DAOException {
		OrderDrink orderDrink = new OrderDrink();
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_DRINK);
			preparedStatement.setInt(1, orderDrinkId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			orderDrink.setOrderDrinkId(resultSet.getInt(1));
			orderDrink.setOrderId(resultSet.getInt(2));
			orderDrink.setDrinkId(resultSet.getInt(3));
			orderDrink.setStatus(resultSet.getString(4));
			orderDrink.setSize(resultSet.getInt(5));
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}  catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return orderDrink;
	}
	
	@Override
	public List<OrderDrink> readOrderDrinkByOrder(int orderId) throws DAOException {
		List<OrderDrink> orderDrinks = new ArrayList<OrderDrink>();
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_DRINK_BY_ORDER);
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderDrink orderDrink = new OrderDrink();
				orderDrink.setOrderDrinkId(resultSet.getInt(1));
				orderDrink.setOrderId(resultSet.getInt(2));
				orderDrink.setDrinkId(resultSet.getInt(3));
				orderDrink.setStatus(resultSet.getString(4));
				orderDrink.setSize(resultSet.getInt(5));
				orderDrinks.add(orderDrink);
			}
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}  catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return orderDrinks;
	}

	@Override
	public Delivery readDelivery(int deliveryId) throws DAOException {
		Delivery delivery = new Delivery();
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELIVERY);
			preparedStatement.setInt(1, deliveryId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			delivery.setDeliveryId(resultSet.getInt(1));
			delivery.setType(resultSet.getString(2));
			delivery.setStatus(resultSet.getString(3));
			delivery.setStart(resultSet.getDate(4));
			delivery.setEnd(resultSet.getDate(5));
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}  catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return delivery;
	}

	@Override
	public boolean deleteDrink(int drinkId) throws DAOException {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DRINK);
			preparedStatement.setInt(1, drinkId);
			if (preparedStatement.executeUpdate() == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return true;
	}

	@Override
	public boolean deleteDrinkIngredient(int drinkIngredientId) throws DAOException {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DRINK_INGREDIENT);
			preparedStatement.setInt(1, drinkIngredientId);
			if (preparedStatement.executeUpdate() == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return true;
	}

	@Override
	public boolean deleteOrder(int orderId) throws DAOException {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER);
			preparedStatement.setInt(1, orderId);
			if (preparedStatement.executeUpdate() == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return true;
	}

	@Override
	public boolean deleteOrderDrink(int orderDrinkId) throws DAOException {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_DRINK);
			preparedStatement.setInt(1, orderDrinkId);
			if (preparedStatement.executeUpdate() == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return true;
	}

	@Override
	public boolean deleteDelivery(int deliveryId) throws DAOException {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DELIVERY);
			preparedStatement.setInt(1, deliveryId);
			if (preparedStatement.executeUpdate() == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return true;
	}

}
