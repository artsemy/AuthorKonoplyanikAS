package by.training.epam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.training.epam.bean.User;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;

public class UserDAOImpl implements UserDAO{
	
	@Override
	public User readUser(String name) throws DAOException {
		User user = null;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			String req = "select * from user where name = ?"; 
			PreparedStatement statement = connection.prepareStatement(req);
			statement.setString(1, name);
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				user = new User(set.getInt(1), set.getString(2), set.getString(3));
			}
			connectionPool.closeConnection(connection, statement, set);
		} catch (SQLException e) {
			throw new DAOException("db problem", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("connection pool problem", e);
		}
		return user;
	}

	@Override
	public void createUser(String name, String pass) throws DAOException {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			String req = "insert into user values(?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(req);
			Statement st = connection.createStatement(); //autoincrement
			ResultSet rs = st.executeQuery("select max(`user-id`) from user");
			rs.next();
			int id = rs.getInt(1) + 1;
			statement.setInt(1, id);
			statement.setString(2, name);
			statement.setString(3, pass);
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
