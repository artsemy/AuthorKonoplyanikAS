package by.training.epam;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.controller.Controller;

public class ServletForJsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletForJsp() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		String command;
		if (request.getParameter("log_in") != null) {
			command = "LOG_IN";
		} else if (request.getParameter("sing_in") != null){
			command = "SIGN_IN";
		} else {
			command = "SIGN_OUT";
		}
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		Controller controller = new Controller();
		String res = controller.run(command, name, pass);
		session.setAttribute("name", name);
		request.setAttribute("result", res);
		if (command.equals("SIGN_OUT")) {
			session.removeAttribute("name");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/home.jsp");
		dispatcher.forward(request, response);
		
	}

}
