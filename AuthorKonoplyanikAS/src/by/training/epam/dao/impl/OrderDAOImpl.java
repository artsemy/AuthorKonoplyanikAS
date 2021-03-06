package by.training.epam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.training.epam.bean.Delivery;
import by.training.epam.bean.Drink;
import by.training.epam.bean.DrinkExtra;
import by.training.epam.bean.Order;
import by.training.epam.bean.OrderDrink;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.OrderDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.service.ServiceConstant;

public class OrderDAOImpl implements OrderDAO {
	
	private static final String INSERT_DRINK = "insert into `drink` values (?, ?)";
	private static final String INSERT_DRINK_EXTRA = "insert into `drink-has-extra` values (?, ?, ?, ?)";
	private static final String INSERT_ORDER = "insert into `order` values (?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_ORDER_DRINK = "insert into `order-has-drink` values (?, ?, ?, ?)";
	private static final String INSERT_DELIVERY = "insert into `delivery` values (?, ?, ?, ?, ?)";
	
	private static final String SELECT_DRINK = "select * from `drink` where `drink-id` = ?";
	private static final String SELECT_DRINK_BY_ORDER = "select * from `order-has-drink` where `order-id` = ?";
	private static final String SELECT_DRINK_EXTRA_BY_DRINK = "select * from `drink-has-extra` where `drink-id` = ?";
	private static final String SELECT_ORDER = "select * from `order` where `order-id` = ?";
	private static final String SELECT_ORDER_BY_USER = "select * from `order` where `user-id` = ?";
	private static final String SELECT_ORDER_DRINK = "select * from `order-has-drink` where `order-has-drink-id` = ?";
	private static final String SELECT_ORDER_DRINK_BY_ORDER = "select * from `order-has-drink` where `order-id` = ?";
	private static final String SELECT_DELIVERY = "select * from `delivery` where `delivery-id` = ?";

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
	public int createDrinkExtra(DrinkExtra drinkExtra) throws DAOException {
		int drinkExtraId = -1;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			drinkExtraId = getNextId(connection, "`drink-has-extra`", "`drink-has-extra-id`");
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DRINK_EXTRA);
			preparedStatement.setInt(1, drinkExtraId);
			preparedStatement.setInt(2, drinkExtra.getDrinkId());
			preparedStatement.setInt(3, drinkExtra.getExtraMenuId());
			preparedStatement.setString(4, drinkExtra.getStatus());
			preparedStatement.executeUpdate();
			connectionPool.closeConnection(connection, preparedStatement);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return drinkExtraId;
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
				drink.setDrinkId(resultSet.getInt(3));
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
	public List<DrinkExtra> readDrinkExtra(int drinkId) throws DAOException {
		List<DrinkExtra> ingredients = new ArrayList<DrinkExtra>();
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DRINK_EXTRA_BY_DRINK);
			preparedStatement.setInt(1, drinkId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				DrinkExtra drinkExtra = new DrinkExtra();
				drinkExtra.setDrinkExtraId(resultSet.getInt(1));
				drinkExtra.setDrinkId(resultSet.getInt(2));
				drinkExtra.setExtraMenuId(resultSet.getInt(3));
				drinkExtra.setStatus(resultSet.getString(4));
				ingredients.add(drinkExtra);
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
	public OrderDrink readOrderDrinkById(int orderDrinkId) throws DAOException {
		OrderDrink orderDrink = new OrderDrink();
//		try {
//			ConnectionPool connectionPool = ConnectionPool.getInstance();
//			Connection connection = connectionPool.takeConnection();
//			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_DRINK);
//			preparedStatement.setInt(1, orderDrinkId);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			if (!resultSet.next()) {
//				return null;
//			}
//			orderDrink.setOrderDrinkId(resultSet.getInt(1));
//			orderDrink.setOrderId(resultSet.getInt(2));
//			orderDrink.setDrinkId(resultSet.getInt(3));
//			orderDrink.setStatus(resultSet.getString(4));
//			orderDrink.setSize(resultSet.getInt(5));
//			connectionPool.closeConnection(connection, preparedStatement, resultSet);
//		}  catch (SQLException e) {
//			throw new DAOException("db problem", e);
//		} catch (ConnectionPoolException e) {
//			throw new DAOException("connection pool problem", e);
//		}
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
	
	private static final String READ_LAST_ORDER = "select * from `order` where `order-id` = ?";
	
	@Override
	public Order readLastOrder() throws DAOException {
		Order order = new Order();
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			int orderId = getNextId(connection, "`order`", "`order-id`") - 1; //fix
			PreparedStatement preparedStatement = connection.prepareStatement(READ_LAST_ORDER);
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
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		} 
		return order;
	}

	private static final String UPDATE_ORDER_DRINK_STATUS = "update `order-has-drink` set status = ? where `drink-id` = ?";
	
	@Override
	public void updateOrderDrinkStatusByDrink(int drinkId, String status) throws DAOException {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(UPDATE_ORDER_DRINK_STATUS);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, drinkId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		} finally {
			try {
				connectionPool.closeConnection(connection, preparedStatement);
			} catch (ConnectionPoolException e) {
				throw new DAOException("connection pool problem", e);
			}
		}
	}
	
	private static final String UPDATE_DRINK_EXTRA_STATUS = "update `drink-has-extra` set status = ? where `drink-id` = ?";

	@Override
	public void updateDrinkExtraStatusByDrink(int drinkId, String status) throws DAOException {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(UPDATE_DRINK_EXTRA_STATUS);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, drinkId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		} finally {
			try {
				connectionPool.closeConnection(connection, preparedStatement);
			} catch (ConnectionPoolException e) {
				throw new DAOException("connection pool problem", e);
			}
		}
	}
	
	private static final String UPDATE_ORDER_STATUS = "update `order` set status = ? where `order-id` = ?";

	@Override
	public void updateOrderStatus(int orderId, String status) throws DAOException {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, orderId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		} finally {
			try {
				connectionPool.closeConnection(connection, preparedStatement);
			} catch (ConnectionPoolException e) {
				throw new DAOException("connection pool problem", e);
			}
		}
	}

	private static final String UPDATE_ORDER_PRICE = "update `order` set `price` = ? where `order-id` = ?";
	
	@Override
	public void updateOrderPrice(int orderId, int price) throws DAOException {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(UPDATE_ORDER_PRICE);
			preparedStatement.setInt(1, price);
			preparedStatement.setInt(2, orderId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		} finally {
			try {
				connectionPool.closeConnection(connection, preparedStatement);
			} catch (ConnectionPoolException e) {
				throw new DAOException("connection pool problem", e);
			}
		}
	}
	
	private static final String SELECT_DRINK_EXTRA_BY_ID = "select * from `drink-has-extra` where `drink-has-extra-id` = ?";

	@Override
	public DrinkExtra readDrinkExtraById(int drinkExtraId) throws DAOException {
		DrinkExtra drinkExtra = new DrinkExtra();
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_DRINK_EXTRA_BY_ID);
			preparedStatement.setInt(1, drinkExtraId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			drinkExtra.setDrinkExtraId(resultSet.getInt(1));
			drinkExtra.setDrinkId(resultSet.getInt(2));
			drinkExtra.setExtraMenuId(resultSet.getInt(3));
			drinkExtra.setStatus(resultSet.getString(4));
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		} finally {
			try {
				connectionPool.closeConnection(connection, preparedStatement, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException("connection pool problem", e);
			}
		}
		return drinkExtra;
	}
	
	private static final String UPDATE_DRINK_EXTRA_STATUS_BY_ID = "update `drink-has-extra` set `status` = ? "
			+ "where `drink-has-extra-id` = ?";

	@Override
	public void updateDrinkExtraStatusById(int drinkExtraId, String status) throws DAOException {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(UPDATE_DRINK_EXTRA_STATUS_BY_ID);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, drinkExtraId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		} finally {
			try {
				connectionPool.closeConnection(connection, preparedStatement);
			} catch (ConnectionPoolException e) {
				throw new DAOException("connection pool problem", e);
			}
		}
	}
	
	private static final String SELECT_ORDER_DRINK_BY_DRINK = "select * from `order-has-drink` where `drink-id` = ?";

	@Override
	public OrderDrink readOrderDrinkByDrink(int drinkId) throws DAOException {
		OrderDrink orderDrink = new OrderDrink();
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_ORDER_DRINK_BY_DRINK);
			preparedStatement.setInt(1, drinkId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			orderDrink.setOrderDrinkId(resultSet.getInt(1));
			orderDrink.setOrderId(resultSet.getInt(2));
			orderDrink.setDrinkId(resultSet.getInt(3));
			orderDrink.setStatus(resultSet.getString(4));
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		} finally {
			try {
				connectionPool.closeConnection(connection, preparedStatement);
			} catch (ConnectionPoolException e) {
				throw new DAOException("connection pool problem", e);
			}
		}
		return orderDrink;
	}

}
