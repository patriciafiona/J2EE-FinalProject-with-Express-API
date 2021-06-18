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

import main.entities.Product;
import main.service.Service;
import main.service.implementation.ProductsServiceImpl;

@WebServlet("/EmployeeProducts")
public class EmployeeProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeProducts() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeProducts");
		//check login status
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email") != null) {
			String email = session.getAttribute("email").toString();
			System.out.println("Login with email: "+ email);
			if(!email.isEmpty()) {
				Service ps = new ProductsServiceImpl();
				try {
					List<Product> listProduct = ps.findAll();
					
					request.setAttribute("listProduct", listProduct);
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
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeProducts");
		doGet(request, response);
	}

}
