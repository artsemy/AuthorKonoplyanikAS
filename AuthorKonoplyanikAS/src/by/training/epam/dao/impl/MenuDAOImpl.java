package by.training.epam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.training.epam.bean.DrinkMenuItem;
import by.training.epam.bean.ExtraMenuItem;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.MenuDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;

public class MenuDAOImpl implements MenuDAO {
	
	public static final String READ_MENU = "select * from `drink-menu`";
	public static final String READ_EXTRA_MENU_ITEM = "select * from `extra-menu` where `extra-menu-id` = ?";
	public static final String READ_DRINK_MENU_ITEM = "select * from `drink-menu` where `drink-menu-id` = ?";

	@Override
	public List<DrinkMenuItem> getMenu() throws DAOException {
		List<DrinkMenuItem> drinkMenuItems = new ArrayList<DrinkMenuItem>();
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(READ_MENU);
			while (resultSet.next()) {
				int drinkMenuId = resultSet.getInt(1);
				String title = resultSet.getString(2);
				int price = resultSet.getInt(3);
				DrinkMenuItem drinkMenuItem = new DrinkMenuItem(drinkMenuId, title, price);
				drinkMenuItems.add(drinkMenuItem);
			}
			connectionPool.closeConnection(connection, statement, resultSet);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		}
		return drinkMenuItems;
	}

	@Override
	public ExtraMenuItem readExtraMenuItem(int extraMenuId) throws DAOException {
		ExtraMenuItem extraMenuItem;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(READ_EXTRA_MENU_ITEM);
			preparedStatement.setInt(1, extraMenuId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			extraMenuItem = new ExtraMenuItem(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		}
		return extraMenuItem;
	}

	@Override
	public DrinkMenuItem readDrinkMenuItem(int drinkMenuId) throws DAOException {
		DrinkMenuItem drinkMenuItem;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(READ_DRINK_MENU_ITEM);
			preparedStatement.setInt(1, drinkMenuId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			drinkMenuItem = new DrinkMenuItem(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		}
		return drinkMenuItem;
	}
	
}
