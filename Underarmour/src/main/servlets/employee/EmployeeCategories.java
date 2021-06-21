package main.servlets.employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.Category;
import main.service.Service;
import main.service.implementation.CategoriesServiceImpl;

@WebServlet("/EmployeeCategories")
public class EmployeeCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeCategories() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeCategories");
		//check login status
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email") != null && session.getAttribute("user_status") != null) {
			String email = session.getAttribute("email").toString();
			String u_stat = session.getAttribute("user_status").toString();
			System.out.println("Login with email: "+ email);
			if(!email.isEmpty() && u_stat.equals("1")) {
				Service ss = new CategoriesServiceImpl();
				try {
					List<Category> listCategory = ss.findAll();
					
					request.setAttribute("listCategory", listCategory);
				} catch (SQLException e) {
					e.printStackTrace();
				}		
				
				if(request.getAttribute("addCategoryStatus") != null) {
					request.setAttribute("addCategoryStatus", request.getAttribute("addCategoryStatus"));
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
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeCategories");
		doGet(request, response);
	}

}
