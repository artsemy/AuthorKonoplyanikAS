package by.training.epam.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.training.epam.bean.DrinkMenuItem;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.MenuDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;

public class MenuDAOImpl implements MenuDAO {
	
	public static final String READ_MENU = "select * from `drink-menu`";

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
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		}
		return drinkMenuItems;
	}
	
}
