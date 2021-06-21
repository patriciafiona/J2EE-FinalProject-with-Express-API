package main.servlets.employee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.Category;
import main.entities.Tag;
import main.entities.UserStatus;
import main.service.Service;
import main.service.implementation.CategoriesServiceImpl;
import main.service.implementation.StatusServiceImpl;
import main.service.implementation.TagsServiceImpl;

@WebServlet("/EmployeeTagsAdd")
public class EmployeeTagsAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeTagsAdd");
		//check login status
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email") != null && session.getAttribute("user_status") != null) {
			String email = session.getAttribute("email").toString();
			String u_stat = session.getAttribute("user_status").toString();
			System.out.println("Login with email: "+ email);
			if(!email.isEmpty() && u_stat.equals("1")) {
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
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeTagsAdd");
		String name = request.getParameter("name");
		Service ts = new TagsServiceImpl();
		if(!name.isEmpty()) {
			try {
				if(ts.insert(new Tag(name)) == 1) {
					System.out.println("Add Tag Success");
				}else {
					System.out.println("Add Tag Failed");
				}
				response.sendRedirect(request.getContextPath()+"/EmployeeTags?status=addSuccess");  
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/EmployeeTags?status=addFailed");  
		}
		
	}

}
