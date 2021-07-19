package main.servlets.customer;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.Cart;
import main.entities.Product;
import main.entities.ProductCart;
import main.service.implementation.ProductsServiceImpl;

@WebServlet("/CustomerShoppingCart")
public class CustomerShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DecimalFormat doubleFormat = new DecimalFormat("#.##");
       
    public CustomerShoppingCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------CustomerShoppingCart");
		HttpSession session = request.getSession();
		ArrayList<Cart> myCartSession = (ArrayList<Cart>) session.getAttribute("cart");
		ArrayList<Integer> ids = new ArrayList();
		
		if(myCartSession == null) {
			myCartSession = new ArrayList<>();
		}else {
			for(int i=0; i<myCartSession.size(); i++) {
				ids.add(myCartSession.get(i).getProductId());
			}
		}
		
		//get data from database
		ProductsServiceImpl ps = new ProductsServiceImpl();
		List<Product> listProductCart;
		try {
			listProductCart = ps.findByManyId(ids);
			
			//merge using hashmap
			Map<Integer, ProductCart> listCart = new HashMap<>();
			
			for (int i=0; i<listProductCart.size(); i++) {
				ProductCart product = new ProductCart(
						listProductCart.get(i), 
						myCartSession.get(i).getSize(), 
						myCartSession.get(i).getQuantity()
						);
				listCart.put(listProductCart.get(i).getId(), product);
			}
			
			//count total payment
			countTotalPayment(request, listCart);
			request.setAttribute("listCart", listCart);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("pagina", request.getRequestURI());
		request.getRequestDispatcher("/customerIndex.jsp").include(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------CustomerShoppingCart");
		doGet(request, response);
	}
	
	private void countTotalPayment(HttpServletRequest request, Map<Integer, ProductCart> listCart) {
		double totalPayment = 0.0;
		Iterator it = listCart.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry pair = (Map.Entry)it.next();
	    	ProductCart p = (ProductCart) pair.getValue();
	    	
			int totalProduct = p.getQuantity();
			totalPayment += Double.valueOf(p.getProduct().getPrice() ) * totalProduct;
		}
		request.setAttribute("totalPayment", doubleFormat.format(totalPayment) );		
	}

}
