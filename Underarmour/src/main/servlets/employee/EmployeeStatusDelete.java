package main.servlets.employee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.service.Service;
import main.service.implementation.StatusServiceImpl;

@WebServlet("/EmployeeStatusDelete")
public class EmployeeStatusDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EmployeeStatusDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeStatusDelete");
		System.out.println("ID: "+ request.getParameter("statusId"));
		String URI = request.getRequestURI();
		int id = Integer.valueOf(URI.substring(URI.lastIndexOf('/') + 1) );
		
		Service ss = new StatusServiceImpl();
		try {
			if(ss.delete(id) == 1) {
				response.sendRedirect(request.getContextPath()+"/EmployeeStatus?status=deleteSuccess"); 
			}else {
				response.sendRedirect(request.getContextPath()+"/EmployeeStatus?status=deleteFailed"); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeStatusDelete");
		doGet(request, response);
	}

}
