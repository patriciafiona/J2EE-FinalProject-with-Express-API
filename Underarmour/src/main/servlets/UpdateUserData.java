package main.servlets;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import main.entities.User;
import main.service.Service;
import main.service.implementation.UserServiceImpl;

@WebServlet("/UpdateUserData")
public class UpdateUserData extends HttpServlet {
	private String page;
	private String user_id;
	private String name;
	private String email;
	private Date bod;
	private String phoneNumber;
	private String address;
	private String newFilename;
	
	private static final long serialVersionUID = 1L;
       
    public UpdateUserData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateUserData------------------------------------------------------------------------------doGet");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateUserData-------------------------------------------------------------------------------doPost");
		
		//Handle File Upload to our Folder
		   File file ;
		   int maxFileSize = 5000 * 1024;
		   int maxMemSize = 5000 * 1024;
		   String filePath = "C:/Users/USER/eclipse-workspace/J2EE-FinalProject-with-Express-API/Underarmour/WebContent/assets/img/user_profile/";
		 
		   String contentType = request.getContentType();
		   if ((contentType.indexOf("multipart/form-data") >= 0)) {
		 
		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      factory.setSizeThreshold(maxMemSize);
		      factory.setRepository(new File("c:\\temp"));
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      upload.setSizeMax( maxFileSize );
		      try{ 
		         List fileItems = upload.parseRequest(request);
		         Iterator i = fileItems.iterator();
		         while ( i.hasNext () ) 
		         {
		            FileItem fi = (FileItem)i.next();
		            if ( fi.isFormField () )  {
		            	String fieldName = fi.getFieldName();
		            	switch(fieldName) {
		            	case "page":
		            		page = fi.getString();
		            		break;
		            	case "user_id":
		            		user_id = fi.getString();
		            		break;
		            	case "name":
		            		name = ConvertResult(fi.getString());
		            		break;
		            	case "email":
		            		email = fi.getString();
		            		break;
		            	case "bod":
		            		bod = Date.valueOf(fi.getString() );
		            		break;
		            	case "address":
		            		address = fi.getString();
		            		break;
		            	case "phoneNumber":
		            		phoneNumber = fi.getString();
		            		break;
		            	}
		            }else {
		                String fieldName = fi.getFieldName();
		                
		                String fileName = fi.getName();
		                String fileExt = FilenameUtils.getExtension(fileName);
		                
		                if(!fileName.isEmpty() && fileName != null) {
		                	newFilename = "user_photo_" + user_id + "." + fileExt;
			                file = new File( filePath + newFilename) ;
			                fi.write( file ) ;
			                System.out.println("Uploaded Filename: " + filePath + newFilename);
		                }
		                
		            }
		         }
		      }catch(Exception ex) {
		         System.out.println(ex);
		      }
		   }else{
			  request.setAttribute("pagina", request.getRequestURI());
		      System.out.println("No file uploaded"); 
		      request.setAttribute("updateUserData", "failed");
		      request.setAttribute("updateUserDataDetail", "No file uploaded, please try again...");
		      response.sendRedirect("./EmployeeHome?status=updateDataFailed");  
		   }
		   
		// Sent new data to database
		if(!user_id.isEmpty() && !name.isEmpty() && !email.isEmpty() && bod != null && 
				!phoneNumber.isEmpty() && !address.isEmpty() ) {
			Service us = new UserServiceImpl();
			User userData = new User(Integer.valueOf(user_id), name, email, bod, address, phoneNumber, newFilename);
			try {
				if(us.update(userData) == 1) {
					System.out.println("Update Success"); 
					
					//delay to wait project refresh
				   try {
						TimeUnit.SECONDS.sleep(3);
						
						if(page!=null & page.equals("customerHome")) {
							response.sendRedirect("./CustomerProfile?status=updateDataSuccess");  
						}else {
							response.sendRedirect("./EmployeeHome?status=updateDataSuccess");  
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("Update Data to DB Failed"); 
					
					if(page!=null & page.equals("customerHome")) {
						response.sendRedirect("./CustomerProfile?status=updateDataFailed");  
					}else {
						response.sendRedirect("./EmployeeHome?status=updateDataFailed");  
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    
		}else {
			System.out.println("Update Failed - some data empty"); 
			if(page!=null & page.equals("customerHome")) {
				response.sendRedirect("./CustomerProfile?status=updateDataFailed");  
			}else {
				response.sendRedirect("./EmployeeHome?status=updateDataFailed");  
			}
		}
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
