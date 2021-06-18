package main.servlets.employee;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.entities.User;
import main.service.Service;
import main.service.implementation.UserServiceImpl;

@WebServlet("/EmployeeLogin")
public class EmployeeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String captchaString = "";
       
    public EmployeeLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet---------------------------------------------------------------------------------------EmployeeLogin");
		setCustomCaptcha(request, response);
		request.setAttribute("pagina", request.getRequestURI());
		request.getRequestDispatcher("/loginRegister.jsp").include(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost---------------------------------------------------------------------------------------EmployeeLogin");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String custom_captcha_user_answer = request.getParameter("custom_captcha_input");
		
		if(!custom_captcha_user_answer.isEmpty() && !email.isEmpty() && !password.isEmpty() ) {
			if(custom_captcha_user_answer.equals(captchaString)) {
				UserServiceImpl us = new UserServiceImpl();
				try {
					User userData = us.findUserByEmail(email);
					
					if(userData.getIsLogin() == 1) {
						// can't login
						//generate new custom captcha
						setCustomCaptcha(request, response);
						
						response.sendRedirect(request.getContextPath()+"/EmployeeLogin?status=failed"
								+ "&"
								+ "status_detail=You're account are still use in other device. Please logout first...");  
					}else {
						if(userData.getStatus() == 1 && userData.getEmail().equals(email) && givenPassword_whenHashing_thenVerifying(userData.getPassword(), password) ) {
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
					        response.sendRedirect("./EmployeeHome");  
						}else {
							//Login failed
							//generate new custom captcha
							setCustomCaptcha(request, response);
							
							response.sendRedirect(request.getContextPath()+"/EmployeeLogin?status=failed"
									+ "&"
									+ "status_detail=Email and/or Password isn't correct, please try again...");  
						}
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else {
				// Wrong captcha
				//generate new custom captcha
				setCustomCaptcha(request, response);
				
				response.sendRedirect(request.getContextPath()+"/EmployeeLogin?status=failed"
						+ "&"
						+ "status_detail=Your input Captha is wrong, please try again...");  
			}
		}else {
			//Login failed
			//generate new custom captcha
			setCustomCaptcha(request, response);
			
			response.sendRedirect(request.getContextPath()+"/EmployeeLogin?status=failed"
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
	
	private void setCustomCaptcha(HttpServletRequest request, HttpServletResponse response) {
		//create image captcha
		BufferedImage ima = getCaptchaImage();
		 
		 try {
			 ByteArrayOutputStream output = new ByteArrayOutputStream();
			 ImageIO.write(ima, "png", output);
			 String imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
			 output.flush();
			 output.close();
			 
			 //send to jsp
			 String captchaStr = getCaptchaString();
			 
			 HttpSession session = request.getSession();
			 session.setAttribute("customCaptchaAnswer", captchaStr);
			 request.setAttribute("imageAsBase64", imageAsBase64);
		 }catch(IOException e) {
			 System.out.println("Exception occured :" + e.getMessage());
		 }
	}
	
	// Function to generate random captcha image and returns the BufferedImage
    public BufferedImage getCaptchaImage() {
        try {
            Color backgroundColor = Color.white;
            Color borderColor = Color.black;
            Color textColor = Color.black;
            Color circleColor = new Color(190, 160, 150);
            
            Font textFont = new Font("Verdana", Font.BOLD, 20);
            
            int charsToPrint = 6;
            int width = 160;
            int height = 50;
            int circlesToDraw = 25;
            float horizMargin = 10.0f;
            double rotationRange = 0.7; 
            
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
            g.setColor(backgroundColor);
            g.fillRect(0, 0, width, height);

            // lets make some noisey circles
            g.setColor(circleColor);
            for (int i = 0; i < circlesToDraw; i++) {
                int L = (int) (Math.random() * height / 2.0);
                int X = (int) (Math.random() * width - L);
                int Y = (int) (Math.random() * height - L);
                g.draw3DRect(X, Y, L * 2, L * 2, true);
            }
            g.setColor(textColor);
            g.setFont(textFont);
            
            FontMetrics fontMetrics = g.getFontMetrics();
            int maxAdvance = fontMetrics.getMaxAdvance();
            int fontHeight = fontMetrics.getHeight();

            String elegibleChars = "ABCDEFGHJKLMNPQRSTUVWXYabcdefghjkmnpqrstuvwxy23456789";
            char[] chars = elegibleChars.toCharArray();
            float spaceForLetters = -horizMargin * 2 + width;
            float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);
            StringBuffer finalString = new StringBuffer();
            for (int i = 0; i < charsToPrint; i++) {
                double randomValue = Math.random();
                int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
                char characterToShow = chars[randomIndex];
                finalString.append(characterToShow);

                // this is a separate canvas used for the character so that
                // we can rotate it independently
                int charWidth = fontMetrics.charWidth(characterToShow);
                int charDim = Math.max(maxAdvance, fontHeight);
                int halfCharDim = (int) (charDim / 2);
                BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
                Graphics2D charGraphics = charImage.createGraphics();
                charGraphics.translate(halfCharDim, halfCharDim);
                double angle = (Math.random() - 0.5) * rotationRange;
                charGraphics.transform(AffineTransform.getRotateInstance(angle));
                charGraphics.translate(-halfCharDim, -halfCharDim);
                charGraphics.setColor(textColor);
                charGraphics.setFont(textFont);
                int charX = (int) (0.5 * charDim - 0.5 * charWidth);
                charGraphics.drawString("" + characterToShow, charX, (int) ((charDim - fontMetrics.getAscent()) / 2 + fontMetrics.getAscent()));
                float x = horizMargin + spacePerChar * (i) - charDim / 2.0f;
                int y = (int) ((height - charDim) / 2);
                g.drawImage(charImage, (int) x, y, charDim, charDim, null, null);
                charGraphics.dispose();
            }
            g.setColor(borderColor);
            g.drawRect(0, 0, width - 1, height - 1);
            g.dispose();
            captchaString = finalString.toString();
            System.out.println("Custom captcha: "+ captchaString);
            return bufferedImage;
        } catch (Exception ioe) {
            throw new RuntimeException("Unable to build image", ioe);
        }
    }

    // Function to return the Captcha string
    public String getCaptchaString() {
        return captchaString;
    }

}
