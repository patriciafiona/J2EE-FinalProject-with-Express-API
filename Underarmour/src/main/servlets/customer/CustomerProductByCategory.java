package main.servlets.customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.entities.Product;
import main.service.implementation.ProductsServiceImpl;

@WebServlet("/CustomerProductByCategory")
public class CustomerProductByCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CustomerProductByCategory() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("doGet---------------------------------------------------------------------------------------CustomerMen");
    	String category = request.getParameter("category");
    	request.setAttribute("category", category);
    	
    	ProductsServiceImpl ps = new ProductsServiceImpl();
		try {
			List<Product> listProduct = ps.findByCategory(category);
			request.setAttribute("listProduct", listProduct);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    	
    	request.setAttribute("pagina", request.getRequestURI());
		request.getRequestDispatcher("/customerIndex.jsp").include(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------CustomerMen");
		doGet(request, response);
	}

}
