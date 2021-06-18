package main.servlets.employee;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.User;
import main.service.Service;
import main.service.implementation.UserServiceImpl;

@WebServlet("/EmployeeHome")
public class EmployeeHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeHome() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeHome");
		//check login status
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email") != null) {
			String email = session.getAttribute("email").toString();
			System.out.println("Login with email: "+ email);
			if(!email.isEmpty()) {
				UserServiceImpl us = new UserServiceImpl();
				try {
					User userData = us.findUserByEmail(email);
					
					request.setAttribute("userData", userData);
				} catch (SQLException e) {
					e.printStackTrace();
				}				
				
				//get Today date
				SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
				request.setAttribute("todayDate", sdf.format(new Date()));
				
				request.setAttribute("pagina", request.getRequestURI());
				request.getRequestDispatcher("/employeeIndex.jsp").include(request, response); 
			}else {
				System.out.println("Need to Login First");
				request.getRequestDispatcher("/loginRegister.jsp").include(request, response);
			}
		}else {
			System.out.println("Need to Login First");
			response.sendRedirect(request.getContextPath()+"/EmployeeLogin");  
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeHome");
		doGet(request, response);
	}

}
