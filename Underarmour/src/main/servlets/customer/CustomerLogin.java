package main.servlets.customer;

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
import main.service.implementation.UserServiceImpl;

@WebServlet("/CustomerLogin")
public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CustomerLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------CustomerLogin");
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email") != null) {
			String email = session.getAttribute("email").toString();
			request.setAttribute("userEmail", email);
			
			request.setAttribute("pagina", request.getRequestURI());
			response.sendRedirect(request.getContextPath()+"/");  
		}else{
			request.setAttribute("pagina", request.getRequestURI());
			request.getRequestDispatcher("/loginRegister.jsp").include(request, response); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------CustomerLogin");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(!email.isEmpty() && !password.isEmpty() ) {
			UserServiceImpl us = new UserServiceImpl();
			try {
				User userData = us.findUserByEmail(email);
				
				if(userData.getIsLogin() == 1) {
					// can't login					
					response.sendRedirect(request.getContextPath()+"/CustomerLogin?status=failed"
							+ "&"
							+ "status_detail=You're account are still use in other device. Please logout first...");  
				}else {
					if(userData.getEmail().equals(email) && givenPassword_whenHashing_thenVerifying(userData.getPassword(), password) ) {
						//Login Success go to home page
						us.update(userData.getId(), 1);
						
						//save user data into session
						HttpSession session = request.getSession(true);
						session.setAttribute("username", userData.getName());
						session.setAttribute("email", email);
						
						if(request.getParameter("rememberMe") != null) {
							session.setMaxInactiveInterval(604800);    // session timeout in seconds (for 1 week)
						}else {
							session.setMaxInactiveInterval(86400);    // session timeout in seconds (for 1 day)
						}
				        
						//go to homepage
				        response.sendRedirect("./CustomerHome");  
					}else {
						//Login failed
						response.sendRedirect(request.getContextPath()+"/CustomerLogin?status=failed"
								+ "&"
								+ "status_detail=Email and/or Password isn't correct, please try again...");  
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			//Login failed			
			response.sendRedirect(request.getContextPath()+"/CustomerLogin?status=failed"
					+ "&"
					+ "status_detail=Failed to Login because some value still empty, please try again...");
		}
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
