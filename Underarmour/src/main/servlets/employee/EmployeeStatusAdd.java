package main.servlets.employee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.UserStatus;
import main.service.Service;
import main.service.implementation.StatusServiceImpl;

@WebServlet("/EmployeeStatusAdd")
public class EmployeeStatusAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeStatusAdd() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeStatus");
		//check login status
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email") != null) {
			String email = session.getAttribute("email").toString();
			System.out.println("Login with email: "+ email);
			if(!email.isEmpty()) {
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
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeStatus");
		String name = request.getParameter("name");
		Service ss = new StatusServiceImpl();
		if(!name.isEmpty()) {
			try {
				if(ss.insert(new UserStatus(name)) == 1) {
					System.out.println("Add Status Success");
					response.sendRedirect(request.getContextPath()+"/EmployeeStatus?status=addSuccess"); 
				}else {
					System.out.println("Add Status Failed");
					response.sendRedirect(request.getContextPath()+"/EmployeeStatus?status=addFailed"); 
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/EmployeeStatus");  
		}
		
	}

}
