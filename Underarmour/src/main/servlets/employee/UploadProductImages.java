package main.servlets.employee;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import main.entities.Photo;
import main.service.Service;
import main.service.implementation.PhotosServiceImpl;

@WebServlet("/UploadProductImages")
public class UploadProductImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int product_id;
	private String photo_01;
	private String photo_02;
	private String photo_03;
	private String photo_04;
	private String photo_05;
	
    public UploadProductImages() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeProductsAdd");
		//check login status
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email") != null) {
			String email = session.getAttribute("email").toString();
			System.out.println("Login with email: "+ email);
			if(!email.isEmpty()) {
				Service ps = new PhotosServiceImpl();
				try {
					String URI = request.getRequestURI();
					int id = Integer.valueOf(URI.substring(URI.lastIndexOf('/') + 1) );
					
					Photo photo = ps.findById(id);
					request.setAttribute("photo", photo);
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
	System.out.println("UpdateUserData-------------------------------------------------------------------------------doPost");
		
		//Handle File Upload to our Folder
		   int maxFileSize = 5000 * 1024;
		   int maxMemSize = 5000 * 1024;
		   String filePath = "C:/Users/USER/eclipse-workspace/J2EE-FinalProject-with-Express-API/Underarmour/WebContent/assets/img/products/";
		 
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
		            	case "product_id":
		            		product_id = Integer.valueOf(fi.getString() );
		            		break;
		            	}
		            }else {
		            	String fieldName = fi.getFieldName();
		            	switch(fieldName) {
		            	case "file_01":
		            		store_image(fi, 1, filePath);
		            		break;
		            	case "file_02":
		            		store_image(fi, 2, filePath);
		            		break;
		            	case "file_03":
		            		store_image(fi, 3, filePath);
		            		break;
		            	case "file_04":
		            		store_image(fi, 4, filePath);
		            		break;
		            	case "file_05":
		            		store_image(fi, 5, filePath);
		            		break;
		            	}
		            }
		         }
		      }catch(Exception ex) {
		         System.out.println(ex);
		      }
		   }else{
		      System.out.println("No file uploaded"); 
		      response.sendRedirect("./EmployeeProducts?status=updatePhotoFailed");  
		   }
		   
		// Sent new data to database
		System.out.println("Foto 01: "+ photo_01);
		if(product_id !=0 && !photo_01.isEmpty()) {
			Service ps = new PhotosServiceImpl();
			Photo photo = new Photo(product_id, photo_01, photo_02, photo_03, photo_04, photo_05);
			try {
				if(ps.update(photo) == 1) {
					System.out.println("Update Success"); 
				}else {
					System.out.println("Update Failed"); 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//delay to wait project refresh
		   try {
				TimeUnit.SECONDS.sleep(5);
				
				response.sendRedirect(request.getContextPath()+"/EmployeeProducts?status=updatePhotoSuccess");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    
		}else {
			System.out.println("Update Failed"); 
			response.sendRedirect(request.getContextPath()+"/EmployeeProducts?status=updatePhotoFailed");
		}
	}
	
	private void store_image(FileItem fi, int photo_position, String filePath) {
		File file = null ;
		String fileName = fi.getName();
        String fileExt = FilenameUtils.getExtension(fileName);
        
        if(!fileName.isEmpty() && fileName != null) {
        	switch(photo_position) {
        	case 1:
        		photo_01 = "product_photo_UA" + photo_position+ "_"+ product_id + "." + fileExt;
        		file = new File( filePath + photo_01) ;
        		break;
        	case 2:
        		photo_02 = "product_photo_UA" + photo_position+ "_"+ product_id + "." + fileExt;
        		file = new File( filePath + photo_02) ;
        		break;
        	case 3:
        		photo_03 = "product_photo_UA" + photo_position+ "_"+ product_id + "." + fileExt;
        		file = new File( filePath + photo_03) ;
        		break;
        	case 4:
        		photo_04 = "product_photo_UA" + photo_position+ "_"+ product_id + "." + fileExt;
        		file = new File( filePath + photo_04) ;
        		break;
        	case 5:
        		photo_05 = "product_photo_UA" + photo_position+ "_"+ product_id + "." + fileExt;
        		file = new File( filePath + photo_05) ;
        		break;
        	}
        	
            try {
				fi.write( file ) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
            System.out.println("Uploaded Filename Finished ");
        }
	}

}
