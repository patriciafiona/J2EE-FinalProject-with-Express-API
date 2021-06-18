package main.servlets.employee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.service.Service;
import main.service.implementation.CategoriesServiceImpl;

@WebServlet("/EmployeeCategoryDelete")
public class EmployeeCategoryDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeCategoryDelete() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeCategoryDelete");
		System.out.println("ID: "+ request.getParameter("categoryId"));
		String URI = request.getRequestURI();
		int id = Integer.valueOf(URI.substring(URI.lastIndexOf('/') + 1) );
		
		Service cs = new CategoriesServiceImpl();
		try {
			if(cs.delete(id) == 1) {
				response.sendRedirect(request.getContextPath()+"/EmployeeCategories?status=deleteSuccess");  
			}else{
				response.sendRedirect(request.getContextPath()+"/EmployeeCategories?status=deleteFailed");  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeCategoryDelete");
		doGet(request, response);
	}

}
