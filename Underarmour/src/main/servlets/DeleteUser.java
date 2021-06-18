package main.servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.User;
import main.service.Service;
import main.service.implementation.UserServiceImpl;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DeleteUser() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteUser------------------------------------------------------------------------------doGet");
		int id = Integer.valueOf(request.getParameter("id"));
		String page = request.getParameter("page");
		String password = request.getParameter("password");
		String reinput_password = request.getParameter("reinput_Password");
		
		Service us = new UserServiceImpl();
		try {
			User userData = us.findById(id);
			if(password.equals(reinput_password)) {
				if(givenPassword_whenHashing_thenVerifying(userData.getPassword(), password) ) {
					//Delete Account
					if(us.delete(id) == 1) {
						System.out.println("Delete Account Success"); 
						request.setAttribute("pagina", request.getRequestURI());
						
						if(page!=null && page.equals("customerHome")) {
							response.sendRedirect("./CustomerLogin");  
						}else {
							response.sendRedirect("./EmployeeLogin");  
						}
					}else {
						System.out.println("Delete Account Failed"); 
						request.setAttribute("pagina", request.getRequestURI());
						if(page!=null && page.equals("customerHome")) {
							response.sendRedirect("./CustomerProfile");  
						}else {
							response.sendRedirect("./EmployeeHome");  
						}
					}
					
				}
			}
			
			HttpSession session = request.getSession(true);
			session.removeAttribute("email");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteUser------------------------------------------------------------------------------doPost");
		doGet(request, response);
	}
	
	private boolean givenPassword_whenHashing_thenVerifying(String hashPwd, String inputPwd){
	    // Create MessageDigest instance for MD5
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			//Add password bytes to digest
	        md.update(inputPwd.getBytes());
	        //Get the hash's bytes 
	        byte[] bytes = md.digest();
	        //This bytes[] has bytes in decimal format;
	        //Convert it to hexadecimal format
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++)
	        {
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
		        
	        return (sb.toString().equals(hashPwd) ) ;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return false;
	}

}
