package main.servlets.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CustomerHome")
public class CustomerHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CustomerHome() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------CustomerHome");
		
		request.setAttribute("pagina", request.getRequestURI());
		request.getRequestDispatcher("/customerIndex.jsp").include(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------CustomerHome");
		doGet(request, response);
	}

}
