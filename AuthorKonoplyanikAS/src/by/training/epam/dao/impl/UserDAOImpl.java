package by.training.epam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.training.epam.bean.User;
import by.training.epam.dao.DAOException;
import by.training.epam.dao.UserDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;

public class UserDAOImpl implements UserDAO {
	
	private static final String READ_USER = "select * from user where login = ? and password = ?";
	private static final String CREATE_USER = "insert into user values(?, ?, ?, ?, ?)";
	
	@Override
	public User readUser(User user) throws DAOException {
		User resultUser = null;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(READ_USER);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				resultUser = new User(set.getInt(1), set.getInt(2), set.getInt(3), 
						set.getString(4), set.getString(5));
			}
			connectionPool.closeConnection(connection, statement, set);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return resultUser;
	}

	@Override
	public void createUser(User user) throws DAOException {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_USER);
			Statement st = connection.createStatement(); //auto increment
			ResultSet rs = st.executeQuery("select max(`user-id`) from user");
			rs.next();
			int id = rs.getInt(1) + 1;
			user.setUserId(id);
			statement.setInt(1, user.getUserId());
			statement.setInt(2, user.getRoleId());
			statement.setInt(3, user.getWalletId());
			statement.setString(4, user.getLogin());
			statement.setString(3, user.getPassword());
			statement.executeUpdate();
			rs.close();
			st.close();
			connectionPool.closeConnection(connection, statement);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
	}
		
}
