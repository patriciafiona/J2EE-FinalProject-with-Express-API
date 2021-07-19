package main.servlets.employee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.Category;
import main.service.Service;
import main.service.implementation.CategoriesServiceImpl;

@WebServlet("/EmployeeCategoryEdit")
public class EmployeeCategoryEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeCategoryEdit() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeCategoryEdit");
		//check login status
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email") != null && session.getAttribute("user_status") != null) {
			String email = session.getAttribute("email").toString();
			String u_stat = session.getAttribute("user_status").toString();
			System.out.println("Login with email: "+ email);
			if(!email.isEmpty() && u_stat.equals("1")) {
				String URI = request.getRequestURI();
				int id = Integer.valueOf(URI.substring(URI.lastIndexOf('/') + 1) );
				
				Service cs = new CategoriesServiceImpl();
				try {
					Category cat = cs.findById(id);
					request.setAttribute("category", cat);
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
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeCategoryEdit");
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		Service cs = new CategoriesServiceImpl();
		if(id > 0 && !name.isEmpty()) {
			try {
				if(cs.update(new Category(id, name)) == 1) {
					System.out.println("Edit Category Success");
				}else {
					System.out.println("Edit Category Failed");
				}
				response.sendRedirect(request.getContextPath()+"/EmployeeCategories?status=editSuccess");  
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Category Edit Form Still Empty");
			response.sendRedirect(request.getContextPath()+"/EmployeeCategories?status=editFailed");  
		}
		
	}

}
