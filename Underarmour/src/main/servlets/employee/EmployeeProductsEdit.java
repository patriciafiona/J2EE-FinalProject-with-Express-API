package main.servlets.employee;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.Category;
import main.entities.Product;
import main.entities.Tag;
import main.service.Service;
import main.service.implementation.CategoriesServiceImpl;
import main.service.implementation.ProductsServiceImpl;
import main.service.implementation.TagsServiceImpl;

@WebServlet("/EmployeeProductsEdit")
public class EmployeeProductsEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private int id;
	private String name;
	private int category;
	private int tag;
	private double rating;
	private double price;
	private int stock;
	private String color;
	private String description;
	
    public EmployeeProductsEdit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeProductsEdit");
		//check login status
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email") != null && session.getAttribute("user_status") != null) {
			String email = session.getAttribute("email").toString();
			String u_stat = session.getAttribute("user_status").toString();
			System.out.println("Login with email: "+ email);
			if(!email.isEmpty() && u_stat.equals("1")) {
				Service ps = new ProductsServiceImpl();
				Service cs = new CategoriesServiceImpl();
				Service ts = new TagsServiceImpl();
				
				try {
					String URI = request.getRequestURI();
					int id = Integer.valueOf(URI.substring(URI.lastIndexOf('/') + 1) );
					Product product = ps.findById(id);
					
					DecimalFormat decimalFormat = new DecimalFormat("#");
					request.setAttribute("product_price", decimalFormat.format(product.getPrice()));
					
					request.setAttribute("product", product);
					
					List<Category> listCategory = cs.findAll();
					request.setAttribute("listCategory", listCategory);
					
					List<Tag> listTag = ts.findAll();
					request.setAttribute("listTag", listTag);
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
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeProductsEdit");
		id = Integer.valueOf(request.getParameter("id"));
		name = request.getParameter("name");
		category = Integer.valueOf(request.getParameter("category"));
		tag = Integer.valueOf(request.getParameter("tag"));
		price = Double.valueOf(request.getParameter("price"));;
		stock = Integer.valueOf(request.getParameter("stock"));
		color = request.getParameter("color");
		description = request.getParameter("description");
		
		Service ps = new ProductsServiceImpl();
		
		if(id > 0 && !name.isEmpty() && category !=0 && tag != 0 && price != 0 && 
				!color.isEmpty() && !description.isEmpty() ) {
			try {
				Product product = new Product(id, name, category, tag, rating, price, stock, color, description);
				if(ps.update(product) == 1){
					System.out.println("Edit product Success");
					response.sendRedirect(request.getContextPath()+"/EmployeeProducts?status=editSuccess");  
				}else {
					System.out.println("Edit product Failed");
					response.sendRedirect(request.getContextPath()+"/EmployeeProducts?status=editFailed");  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
