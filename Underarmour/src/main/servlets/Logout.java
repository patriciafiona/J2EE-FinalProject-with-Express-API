package main.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.User;
import main.service.Service;
import main.service.implementation.UserServiceImpl;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Logout---------------------------------------------------------doGet()");
		HttpSession session = request.getSession(false);
		
		//update login Status to 0
		String email = session.getAttribute("email").toString();
		UserServiceImpl us = new UserServiceImpl();
		try {
			User userData = us.findUserByEmail(email);
			us.update(userData.getId(), 0);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		//clear session
	    if (session != null) {
	        session.invalidate();
	    }
	    
	    //back to login page
	    response.sendRedirect("./CustomerHome");  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Logout---------------------------------------------------------doPost()");
	}

}
