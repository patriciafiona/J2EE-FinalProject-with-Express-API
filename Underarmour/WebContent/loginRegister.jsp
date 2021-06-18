<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page trimDirectiveWhitespaces="true" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<%
	String uri = request.getRequestURI().toString();
	String currentPage = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
	pageContext.setAttribute("currentPage", currentPage);
	
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    
	    <title>Under Armour</title> 
	    
	    <link rel="icon" href='<c:url value="/assets/img/underarmour_logo_white.png"/>'>
		
		<!-- Icon Script -->
		<link rel="stylesheet" href='<c:url value="/assets/vendor/font-awesome-4.7.0/css/font-awesome.min.css"/>'>
		
		<!-- Bootstrap css -->
		<link href='<c:url value="/assets/vendor/bootstrap-5.0.0-beta2-dist/css/bootstrap.min.css"/>' rel="stylesheet" />
		
		<!--Custom CSS -->
		<link href='<c:url value="/assets/css/styles.css"/>' rel="stylesheet"/>
		<link href='<c:url value="/assets/css/loginStyle.css"/>' rel="stylesheet"/>
		
		<!-- JQuery Script -->
		<script src='<c:url value="/assets/vendor/jquery/jquery-3.6.0.min.js"/>' ></script>
	</head>
	<body>
		<jsp:include page="views/navbar/navbarLoginRegister.jsp" />  
		
		<div class="container-fluid">
		  <div class="row no-gutter">
		  	<c:choose>
			    <c:when test="${currentPage eq 'CustomerLogin' || currentPage eq 'CustomerRegister'}">
			        <div id="banner" class="d-none d-md-flex m-0 p-0">
			        	<video autoplay loop muted>
						  <source src='<c:url value="/assets/video/loginregistervideo.mkv"/>' type="video/mp4" />
						</video>
						<div class="darkTransparent"></div>
			        </div>
			        <div class="login py-5">
			        	<div class="customerLoginRegisterBg p-2">
			        		<c:choose>
							    <c:when test="${currentPage eq 'CustomerLogin'}">
							        <jsp:include page="views/pages/customerLogin.jsp" />  
							    </c:when>
							    <c:when test="${currentPage eq 'CustomerRegister'}">
							        <jsp:include page="views/pages/customerRegister.jsp" />  
							    </c:when>
							    <c:otherwise>
							        <p>Status ${currentPage} not found</p>
							    </c:otherwise>
							</c:choose>
			        	</div>
				      </div>
			    </c:when>
			    <c:when test="${currentPage eq 'EmployeeLogin' || currentPage eq 'EmployeeRegister'}">
			        <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image-02"></div>
			        <div class="col-md-8 col-lg-6">
				      <div class="login d-flex align-items-center py-5">
				      	<c:choose>
						    <c:when test="${currentPage eq 'EmployeeLogin'}">
						        <jsp:include page="views/pages/employeeLogin.jsp" />  
						    </c:when>
						    <c:when test="${currentPage eq 'EmployeeRegister'}">
						        <jsp:include page="views/pages/employeeRegister.jsp" />  
						    </c:when>
						    <c:otherwise>
						        <p>Status ${currentPage} not found</p>
						    </c:otherwise>
						</c:choose>
				      </div>
				    </div>
			    </c:when>
			    <c:otherwise>
			        <p>Status ${currentPage} not found</p>
			    </c:otherwise>
			</c:choose>
		  </div>
		</div>
		
		<!-- Option 1: Bootstrap Bundle with Popper -->
		<script src='<c:url value="/assets/vendor/bootstrap-5.0.0-beta2-dist/js/bootstrap.bundle.min.js"/>' ></script>
		
		<script type="text/javascript">
			$(document).ready(function(){				
				//Alert Close listener
				$(".close").click(function(){
				  $("#popupAlert").alert("close");
				});
			});
	   </script>
	</body>
</html>