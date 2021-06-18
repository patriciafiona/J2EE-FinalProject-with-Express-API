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
import javax.servlet.http.HttpSession;

import main.entities.User;
import main.entities.UserStatus;
import main.service.Service;
import main.service.implementation.StatusServiceImpl;
import main.service.implementation.UserServiceImpl;

@WebServlet("/EmployeeUsersEdit")
public class EmployeeUsersEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String password;
	private String email;
	private Date bod;
	private String phoneNumber;
	private String address;
	private int status;
       
    public EmployeeUsersEdit() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeUsersEdit");
		//check login status
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email") != null) {
			String email = session.getAttribute("email").toString();
			System.out.println("Login with email: "+ email);
			if(!email.isEmpty()) {
				System.out.println("ID: "+ request.getParameter("statusId"));
				String URI = request.getRequestURI();
				int id = Integer.valueOf(URI.substring(URI.lastIndexOf('/') + 1) );
				
				Service us = new UserServiceImpl();
				Service ss = new StatusServiceImpl();
				try {
					User user = us.findById(id);
					request.setAttribute("user", user);
					
					List<UserStatus> listStatus = ss.findAll();
					request.setAttribute("listStatus", listStatus);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
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
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeUsersEdit");
		id = Integer.valueOf(request.getParameter("id")) ;
		name = ConvertResult(request.getParameter("name"));
		email = request.getParameter("email");
		password = encryptPassword(request.getParameter("password"));
		bod = Date.valueOf(request.getParameter("bod"));
		phoneNumber = request.getParameter("phoneNumber");
		address = request.getParameter("address");
		status = Integer.valueOf(request.getParameter("user_status"));
		
		Service us = new UserServiceImpl();
		try {
			User user = null;
			if(password != null && !password.equals("")) {
				user = new User(id, name, email, password, status, bod, phoneNumber, address, 0);
				if(us.update(user) == 1){
					System.out.println("Update User Success");
					response.sendRedirect(request.getContextPath()+"/EmployeeUsers?status=editSuccess"); 
				}else {
					System.out.println("Update User Failed");
					response.sendRedirect(request.getContextPath()+"/EmployeeUsers?status=editFailed"); 
				}
			}else {
				user = new User(id, name, email, status, bod, phoneNumber, address, 0);
				if(us.update(user) == 1){
					System.out.println("Update User Success");
					response.sendRedirect(request.getContextPath()+"/EmployeeUsers?status=editSuccess"); 
				}else {
					System.out.println("Update User Failed");
					response.sendRedirect(request.getContextPath()+"/EmployeeUsers?status=editFailed"); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
