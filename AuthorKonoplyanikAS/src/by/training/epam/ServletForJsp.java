package by.training.epam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.epam.controller.Controller;

public class ServletForJsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletForJsp() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Controller c = new Controller();
		c.run(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Controller c = new Controller();
		c.run(request, response);
	}

}
