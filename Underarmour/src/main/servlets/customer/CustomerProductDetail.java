package main.servlets.customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.Cart;
import main.entities.Photo;
import main.entities.Product;
import main.entities.ProductCart;
import main.service.implementation.PhotosServiceImpl;
import main.service.implementation.ProductsServiceImpl;

@WebServlet("/CustomerProductDetail")
public class CustomerProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<Integer, ProductCart> listCart;
    
    public CustomerProductDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------CustomerProductDetail");
		ProductsServiceImpl ps = new ProductsServiceImpl();
		PhotosServiceImpl phs = new PhotosServiceImpl();
		
		HttpSession session = request.getSession();
		try {
			String URI = request.getRequestURI();
			int id = Integer.valueOf(URI.substring(URI.lastIndexOf('/') + 1) );
			
			//check if product already in cart or not
			//if already send current data in cart
			ArrayList<Cart> myCartSession = (ArrayList<Cart>) session.getAttribute("cart");
			
			
			//get data from database
			try {
				Product detail_product = ps.findById(id);
				
				if(myCartSession != null && myCartSession.size() > 0) {
					//merge using hashmap
					listCart = new HashMap<>();
					
					for (int i=0; i<myCartSession.size(); i++) {
						
						if(myCartSession.get(i).getProductId() == id) {
							ProductCart product = new ProductCart(
									detail_product, 
									myCartSession.get(i).getSize(), 
									myCartSession.get(i).getQuantity()
									);
							listCart.put(detail_product.getId(), product);
						}
					}
				}else {
					System.out.println("Cart is still empty");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(listCart != null && listCart.size() > 0) {
				int productInCart = 0;
				String size="";
				Iterator it = listCart.entrySet().iterator();
			    while (it.hasNext()) {
			    	Map.Entry pair = (Map.Entry)it.next();
			    	ProductCart p = (ProductCart) pair.getValue();
			    	
			    	if(p.getProduct().getId() == id ) {
			    		System.out.println("Found data for id: "+ p.getProduct().getId());
						productInCart = p.getQuantity();
						size = p.getSize();
						break;
					}
			    }
			    request.setAttribute("currentSizeInCart", size);
				request.setAttribute("currentQuantityInCart", productInCart);
				//end of checking session
			}
			
			Product product = ps.findById(id);
			request.setAttribute("product", product);
			
			Photo photo = phs.findById(product.getId());
			request.setAttribute("photo", photo);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    	
    	request.setAttribute("pagina", request.getRequestURI());
		request.getRequestDispatcher("/customerIndex.jsp").include(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------CustomerProductDetail");
		doGet(request, response);
	}

}
