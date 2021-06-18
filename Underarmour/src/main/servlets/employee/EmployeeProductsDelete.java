package main.servlets.employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.service.Service;
import main.service.implementation.PhotosServiceImpl;
import main.service.implementation.ProductsServiceImpl;

@WebServlet("/EmployeeProductsDelete")
public class EmployeeProductsDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmployeeProductsDelete() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeProductsDelete");
		System.out.println("ID: "+ request.getParameter("tagId"));
		String URI = request.getRequestURI();
		int id = Integer.valueOf(URI.substring(URI.lastIndexOf('/') + 1) );
		
		Service ps = new ProductsServiceImpl();
		Service photo_s = new PhotosServiceImpl();
		try {
			if(photo_s.delete(id) == 1 ) {
				TimeUnit.SECONDS.sleep(1);
				if(ps.delete(id) == 1) {
					response.sendRedirect(request.getContextPath()+"/EmployeeProducts?status=deleteSuccess");  
				}else {
					response.sendRedirect(request.getContextPath()+"/EmployeeProducts?status=deleteFailed");  
				}
			}else {
				response.sendRedirect(request.getContextPath()+"/EmployeeProducts?status=deleteFailed");  
			}
			
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeProductsDelete");
		doGet(request, response);
	}

}
