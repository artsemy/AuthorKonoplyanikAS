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
	
	private static final String READ_USER_LOG = "select * from user where login = ?";
	private static final String READ_USER_LOG_PASS = "select * from user where login = ? and password = ?";
	private static final String READ_USER_ROLE = "select title from role where `role-id` = ?";
	private static final String READ_USER_WALLET = "select balance from wallet where `wallet-id` = ?";
	
	private static final String CREATE_USER = "insert into user values(?, ?, ?, ?, ?, ?)";
	private static final String CREATE_WALLET = "insert into wallet values(?, ?)"; //add
	
	private static final String READ_WALLET_ID = "select `wallet-id` from user where `user-id` = ?";
	private static final String UPDATE_WALLET = "update wallet set balance = ? where `wallet-id` = ?";
	
	@Override
	public User readUser(String login) throws DAOException {
		User resultUser = null;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(READ_USER_LOG);
			statement.setString(1, login);
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				resultUser = new User(set.getInt(1), set.getInt(2), set.getInt(3), 
						set.getString(4), set.getString(5), set.getString(6));
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
	public User readUser(String login, String password) throws DAOException {
		User resultUser = null;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(READ_USER_LOG_PASS);
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				resultUser = new User(set.getInt(1), set.getInt(2), set.getInt(3), 
						set.getString(4), set.getString(5), set.getString(6));
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
			statement.setString(5, user.getPassword());
			statement.setString(6, user.getName());
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

	@Override
	public String readRole(int roleId) throws DAOException {
		String role = null;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(READ_USER_ROLE);
			statement.setInt(1, roleId);
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				role = set.getString(1);
			}
			connectionPool.closeConnection(connection, statement, set);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return role;
	}

	@Override
	public int readBalance(int walletId) throws DAOException {
		int wallet = 0;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(READ_USER_WALLET);
			statement.setInt(1, walletId);
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				wallet = set.getInt(1);
			}
			connectionPool.closeConnection(connection, statement, set);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return wallet;
	}
	
	

	@Override
	public int readWalletId(int userId) throws DAOException {
		int walletId = -1; //fix
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(READ_WALLET_ID);
			statement.setInt(1, userId);
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				walletId = set.getInt(1);
			}
			connectionPool.closeConnection(connection, statement, set);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return walletId;
	}

	@Override
	public void updateWallet(int walletId, int balance) throws DAOException {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_WALLET);
			statement.setInt(1, balance);
			statement.setInt(2, walletId);
			statement.executeUpdate();
			connectionPool.closeConnection(connection, statement);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
	}
		
}
