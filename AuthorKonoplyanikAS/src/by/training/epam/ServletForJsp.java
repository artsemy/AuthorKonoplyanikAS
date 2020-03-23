package by.training.epam;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.controller.Controller;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;

public class ServletForJsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletForJsp() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<String> set = funk();
		request.setAttribute("array", set);
		Controller c = new Controller();
		c.run(request, response);
	}
	
	public static Set<String> funk() {
		Set<String> set = new TreeSet<String>();
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.takeConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from `drink-menu`");
			while (rs.next()) {
				set.add(rs.getString(2));
			}
		} catch (ConnectionPoolException | SQLException e) {
			set = null;
		}
		return set;
	}

}
