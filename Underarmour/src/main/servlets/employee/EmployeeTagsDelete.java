package main.servlets.employee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.service.Service;
import main.service.implementation.TagsServiceImpl;

@WebServlet("/EmployeeTagsDelete")
public class EmployeeTagsDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeTagsDelete() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeTagsDelete");
		System.out.println("ID: "+ request.getParameter("tagId"));
		String URI = request.getRequestURI();
		int id = Integer.valueOf(URI.substring(URI.lastIndexOf('/') + 1) );
		
		Service ts = new TagsServiceImpl();
		try {
			if(ts.delete(id) == 1) {
				response.sendRedirect(request.getContextPath()+"/EmployeeTags?status=deleteSuccess");
			}else {
				response.sendRedirect(request.getContextPath()+"/EmployeeTags?status=deleteFailed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeTagsDelete");
		doGet(request, response);
	}

}
