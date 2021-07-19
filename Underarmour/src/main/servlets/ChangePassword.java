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

import main.entities.User;
import main.service.implementation.UserServiceImpl;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User userData;
       
    public ChangePassword() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ChangePassword------------------------------------------------------------------------------doGet");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ChangePassword------------------------------------------------------------------------------doPost");
		
		String page = request.getParameter("page");
		String email = request.getParameter("email");
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String reinputNewPassword = request.getParameter("reinput_newPassword");
		
		if(!email.isEmpty() && !currentPassword.isEmpty() && !newPassword.isEmpty() && !reinputNewPassword.isEmpty() ) {
			UserServiceImpl us = new UserServiceImpl();
			try {
				userData = us.findUserByEmail(email);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			if(givenPassword_whenHashing_thenVerifying(userData.getPassword(), currentPassword) ) {
				if(newPassword.equals(reinputNewPassword)) {
					//update password by user id
					try {
						us.updatePassword(userData.getId(), encryptPassword(reinputNewPassword) );
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					System.out.println("Update Success"); 
					if(page!=null && page.equals("customerHome")) {
						response.sendRedirect("./CustomerProfile?status=changePasswordSuccess");  
					}else {
						response.sendRedirect("./EmployeeHome?status=changePasswordSuccess");  
					}
				}else {
					System.out.println("Update Failed"); 
					if(page!=null && page.equals("customerHome")) {
						response.sendRedirect("./CustomerProfile?status=changePasswordFailed");  
					}else {
						response.sendRedirect("./EmployeeHome?status=changePasswordFailed");  
					}
				}
			}else {
				System.out.println("Update Failed"); 
				if(page!=null && page.equals("customerHome")) {
					response.sendRedirect("./CustomerProfile?status=changePasswordFailed");    
				}else {
					response.sendRedirect("./EmployeeHome?status=changePasswordFailed");    
				}
			}
		}else {
			System.out.println("Update Failed"); 
			if(page!=null && page.equals("customerHome")) {
				response.sendRedirect("./CustomerProfile?status=changePasswordFailed");
			}else {
				response.sendRedirect("./EmployeeHome?status=changePasswordFailed");    
			}
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
	
	private String encryptPassword(String passwordToHash) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            return sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        
        return "";
	}

}
