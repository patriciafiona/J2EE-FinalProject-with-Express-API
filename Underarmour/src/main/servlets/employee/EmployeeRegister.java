package main.servlets.employee;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.entities.UserStatus;
import main.entities.User;
import main.service.Service;
import main.service.implementation.StatusServiceImpl;
import main.service.implementation.UserServiceImpl;

@WebServlet("/EmployeeRegister")
public class EmployeeRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeRegister() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeRegister");
		Service us = new StatusServiceImpl();
		try {
			List<UserStatus> listStatus = us.findAll();
			request.setAttribute("listStatus", listStatus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("pagina", request.getRequestURI());
		request.getRequestDispatcher("/loginRegister.jsp").include(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeRegister");
		String name = ConvertResult(request.getParameter("name") );
		int status = Integer.valueOf(request.getParameter("user_status"));
		Date bod = Date.valueOf(request.getParameter("bod"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String recheckPassword = request.getParameter("recheck_password");
		
		if(bod != null && status != 0 && !name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !recheckPassword.isEmpty() ) {
			if(password.equals(recheckPassword)) {
				UserServiceImpl us = new UserServiceImpl();
				
				User user = new User(name, email, encryptPassword(password), 1, bod, "user_default.png", 0);
				try {
					
					//try to check to database email already registered or not
					if(us.findUserByEmail(email) != null) {
						response.sendRedirect(request.getContextPath()+"/EmployeeRegister?status=alreadyRegistered");  
					}else {
						if(us.insert(user) == 1) {
							response.sendRedirect(request.getContextPath()+"/EmployeeRegister?status=success");  
						}else {
							response.sendRedirect(request.getContextPath()+"/EmployeeRegister?status=failed");  
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}else {
				response.sendRedirect(request.getContextPath()+"/EmployeeRegister?status=passwordNotSame"); 
			}
		}else {
			//show alert
			request.setAttribute("registerStatus", "emptyField");
	        request.getRequestDispatcher("/loginRegister.jsp").include(request, response); 
		}
		
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
	
	private String ConvertResult(String val) {
		String temp = "";
		try {
			temp = new String(val.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

}
