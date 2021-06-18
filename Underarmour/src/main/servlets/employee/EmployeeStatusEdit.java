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

@WebServlet("/EmployeeStatusEdit")
public class EmployeeStatusEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeStatusEdit() {
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
				String URI = request.getRequestURI();
				int id = Integer.valueOf(URI.substring(URI.lastIndexOf('/') + 1) );
				
				Service ss = new StatusServiceImpl();
				try {
					UserStatus status = ss.findById(id);
					request.setAttribute("status", status);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
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
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		Service ss = new StatusServiceImpl();
		if(id > 0 && !name.isEmpty()) {
			try {
				if(ss.update(new UserStatus(id, name)) == 1) {
					System.out.println("Edit Status Success");
					response.sendRedirect(request.getContextPath()+"/EmployeeStatus?status=editSuccess"); 
				}else {
					System.out.println("Edit Status Failed");
					response.sendRedirect(request.getContextPath()+"/EmployeeStatus?status=editFailed"); 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Status Edit Form Still Empty");
			response.sendRedirect(request.getContextPath()+"/EmployeeStatus?status=editFailed");  
		}
		
	}

}
