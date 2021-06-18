package main.servlets.employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.Category;
import main.entities.Photo;
import main.entities.Product;
import main.entities.Tag;
import main.service.Service;
import main.service.implementation.CategoriesServiceImpl;
import main.service.implementation.PhotosServiceImpl;
import main.service.implementation.ProductsServiceImpl;
import main.service.implementation.TagsServiceImpl;

@WebServlet("/EmployeeProductsAdd")
public class EmployeeProductsAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String name;
	private int category;
	private int tag;
	private double rating;
	private double price;
	private int stock;
	private String color;
	private String description;
	
    public EmployeeProductsAdd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeProductsAdd");
		//check login status
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email") != null) {
			String email = session.getAttribute("email").toString();
			System.out.println("Login with email: "+ email);
			if(!email.isEmpty()) {
				Service cs = new CategoriesServiceImpl();
				Service ts = new TagsServiceImpl();
				try {
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
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeProductsAdd");
		name = request.getParameter("name");
		category = Integer.valueOf(request.getParameter("category"));
		tag = Integer.valueOf(request.getParameter("tag"));
		rating = 0.0;
		price = Double.valueOf(request.getParameter("price"));;
		stock = Integer.valueOf(request.getParameter("stock"));
		color = request.getParameter("color");
		description = request.getParameter("description");
		
		Service ps = new ProductsServiceImpl();
		Service photo_s = new PhotosServiceImpl();
		try {
			Product product = new Product(name, category, tag, rating, price, stock, color, description);
			if(ps.insert(product) == 1){
				System.out.println("Insert product Success");
			}else {
				System.out.println("Insert product Failed");
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.println(name);
			Product p = ps.findByName02(name);
			
			if(p!=null) {
				//get product id that already add before
				Photo photo = new Photo(p.getId(),"product_default.png");
				
				if(photo_s.insert(photo) ==1) {
					System.out.println("Insert default photo Success");
					response.sendRedirect(request.getContextPath()+"/EmployeeProducts?status=addSuccess");  
				}else {
					System.out.println("Insert default photo Failed");
					response.sendRedirect(request.getContextPath()+"/EmployeeProducts?status=addFailed");  
				}
			}else {
				System.out.println("Failed to get Product id");
			}
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
