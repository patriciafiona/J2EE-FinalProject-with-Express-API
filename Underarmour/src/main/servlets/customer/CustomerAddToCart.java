package main.servlets.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.Cart;

@WebServlet("/CustomerAddToCart")
public class CustomerAddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CustomerAddToCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// For add product to cart, please login first
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeCategories");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeCategories");
		//check login status
		HttpSession session = request.getSession(true);
		int product_id = Integer.valueOf(request.getParameter("productId") );
		String size = request.getParameter("size");
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		
		if(product_id!=0 && !size.isEmpty() && quantity !=0) {
			Cart addCart = new Cart(product_id, size, quantity);
			List<Cart> list = (List<Cart>) session.getAttribute("cart");
			if(list==null){
				list = new ArrayList<Cart>();
				list.add(addCart);
			}else {
				//check if Cart already in session or not,
				//if already add to session, Update the session with new data
				int index = -1;
				for(int i=0; i<list.size(); i++) {
					if(list.get(i).getProductId() == addCart.getProductId() ) {
						System.out.println(">>> Found same product in cart. Update later...");
						index = i;
						break;
					}
				}
				
				if(index != -1) {
					//update session
					System.out.println("      Already in cart, update data");
					list.set(index, addCart);
				}else {
					System.out.println("      Not yet in cart, add data");
					list.add(addCart);
				}
			}
			
			System.out.print("Cart: "+ list.toString() +"\n");
			session.setAttribute("cart", list);
			
			response.sendRedirect("./CustomerShoppingCart");
		}else {
			//still empty
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
		}
		
	}

}
