package main.servlets.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.Cart;

@WebServlet("/CustomerDeleteFromCart")
public class CustomerDeleteFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CustomerDeleteFromCart() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CustomerDeleteFromCart------------------------------------------------------------------------------doGet");
		String URI = request.getRequestURI();
		int id = Integer.valueOf(URI.substring(URI.lastIndexOf('/') + 1) );
		
		//get the index and remove from list in session cart
		HttpSession session = request.getSession();
		List<Cart> list = (List<Cart>) session.getAttribute("cart");
		if(list!=null && id != 0){
			//check if Cart already in session or not,
			//if already add to session, Update the session with new data
			int index = -1;
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).getProductId() == id ) {
					System.out.println("Found id: "+ list.get(i).getProductId());
					index = i;
					break;
				}
			}
			
			if(index != -1) {
				//update session
				System.out.println(">>> Remove Product From the Cart...");
				list.remove(index);
			}
		}
		
		//update cart
		session.setAttribute("cart", list);
		
		response.sendRedirect(request.getContextPath()+"/CustomerShoppingCart");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CustomerDeleteFromCart------------------------------------------------------------------------------doPost");
		doGet(request, response);
	}

}
