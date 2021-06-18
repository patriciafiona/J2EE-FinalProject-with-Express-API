package main.servlets.customer;

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
import main.service.implementation.ProductsServiceImpl;

@WebServlet("/CustomerBottoms")
public class CustomerBottoms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CustomerBottoms() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("doGet---------------------------------------------------------------------------------------CustomersBottoms");
    	String category = request.getParameter("category");
    	request.setAttribute("category", category);
    	
    	String tag = request.getParameter("tag");
    	request.setAttribute("tag", tag);
    	
    	ProductsServiceImpl ps = new ProductsServiceImpl();
		try {
			List<Product> listProduct = ps.findByTagCategory(category, tag);
			request.setAttribute("listProduct", listProduct);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    	
    	request.setAttribute("pagina", request.getRequestURI());
		request.getRequestDispatcher("/customerIndex.jsp").include(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------CustomersBottoms");
		doGet(request, response);
	}

}
