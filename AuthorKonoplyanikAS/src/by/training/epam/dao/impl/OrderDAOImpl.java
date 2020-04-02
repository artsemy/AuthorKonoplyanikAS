package by.training.epam.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.training.epam.bean.Delivery;
import by.training.epam.bean.Drink;
import by.training.epam.bean.DrinkIngredient;
import by.training.epam.bean.Order;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.OrderDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public int createDrink(Drink drink) throws DAOException {
		int drinkMenuId = drink.getDrinkMenuId();
		int drinkId = -1;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			drinkId = getNextId(connection, "drink", "drink-id");
			Statement statement = connection.createStatement();
			String sqlRequest = "insert into drink values (" + drinkId + ", " + drinkMenuId + ")";
			statement.execute(sqlRequest);
			connectionPool.closeConnection(connection, statement);
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
			Statement statement = connection.createStatement();
			String sqlRequest = "insert into `drink-has-ingredient` values (" + drinkIngredientId + ", "
					+ drinkIngredient.getDrinkId() + ", " + drinkIngredient.getPortionId() + ", " 
					+ drinkIngredient.getPortionAmount() + ")";
			statement.execute(sqlRequest);
			connectionPool.closeConnection(connection, statement);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return drinkIngredientId;
	}
	
	@Override
	public int createOrderDrink(int orderId, int drinkId, String status, int size) throws DAOException {
		int orderDrinkId = -1;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			orderDrinkId = getNextId(connection, "`order-has-drink`", "`order-has-drink-id`");
			Statement statement = connection.createStatement();
			String sqlRequest = "insert into `order-has-drink` values (" + orderDrinkId + ", "
					+ orderId + ", " + drinkId + ", `" + status + "`, " + size + ")";
			statement.execute(sqlRequest);
			connectionPool.closeConnection(connection, statement);
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
			PreparedStatement preparedStatement = connection.prepareStatement("insert into `order` values (?, ?, ?, ?, ?, ?, ?)");
			orderId = getNextId(connection, "order", "order-id");
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
			PreparedStatement preparedStatement = connection.prepareStatement("insert into `delivery` values (?, ?, ?, ?, ?)");
			deliveryId = getNextId(connection, "delivery", "delivery-id");
			preparedStatement.setInt(1, deliveryId);
			preparedStatement.setString(2, delivery.getType());
			preparedStatement.setString(3, delivery.getStatus());
			preparedStatement.setDate(4, delivery.getStart());
			preparedStatement.setDate(5, delivery.getEnd());
			preparedStatement.executeUpdate();
			connectionPool.closeConnection(connection, preparedStatement);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return deliveryId;
	}
	
	private int getNextId(Connection connection, String tableName, String columnName) throws SQLException {
		int nextId = -1;
		String sqlRequest = "select max(?) from ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
		preparedStatement.setString(1, columnName);
		preparedStatement.setString(2, tableName);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		resultSet.close();
		preparedStatement.close();
		nextId = resultSet.getInt(1) + 1;
		return nextId;
	}
	
//	@Override
//	public int getNextId(String tableName, String columnName) throws DAOException {
//		int nextId = -1;
//		try {
//			ConnectionPool connectionPool = ConnectionPool.getInstance();
//			Connection connection = connectionPool.takeConnection();
//			String sqlRequest = "select max(?) from ?";
//			PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
//			preparedStatement.setString(1, columnName);
//			preparedStatement.setString(2, tableName);
//			preparedStatement.executeUpdate();
//			connectionPool.closeConnection(connection, preparedStatement);
//		} catch (SQLException e) {
//			throw new DAOException("db problem", e);
//		} catch (ConnectionPoolException e) {
//			throw new DAOException("connection pool problem", e);
//		}
//		return nextId;
//	}
	
}
