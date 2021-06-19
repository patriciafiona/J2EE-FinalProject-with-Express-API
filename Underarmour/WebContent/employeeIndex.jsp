<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page trimDirectiveWhitespaces="true" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<%
	String uri = request.getRequestURI().toString();
	String currentPage = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
	pageContext.setAttribute("currentPage", currentPage);
	
	if(uri.indexOf("status/edit") > 0 ){
		pageContext.setAttribute("statusEditPage", "EmployeeStatusEdit");
	}
	
	if(uri.indexOf("category/edit") > 0 ){
		pageContext.setAttribute("categoryEditPage", "EmployeeCategoryEdit");
	}
	
	if(uri.indexOf("tag/edit") > 0 ){
		pageContext.setAttribute("tagsEditPage", "EmployeeTagsEdit");
	}
	
	if(uri.indexOf("user/edit") > 0 ){
		pageContext.setAttribute("usersEditPage", "EmployeeUsersEdit");
	}
	
	if(uri.indexOf("product/edit") > 0 ){
		pageContext.setAttribute("productsEditPage", "EmployeeProductsEdit");
	}
	
	if(uri.indexOf("upload_images") > 0 ){
		pageContext.setAttribute("photosEditPage", "EmployeeProductPhotosEdit");
	}
	
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    
	    <title>Under Armour | Admin Site</title> 
	    
	    <link rel="icon" href='<c:url value="/assets/img/underarmour_logo_white.png"/>'>
		
		<!-- Icon Script -->
		<link rel="stylesheet" href='<c:url value="/assets/vendor/font-awesome-4.7.0/css/font-awesome.min.css"/>'>
		
		<!-- Bootstrap css -->
		<link href='<c:url value="/assets/vendor/bootstrap-5.0.0-beta2-dist/css/bootstrap.min.css"/>' rel="stylesheet" />
		
		<!--Custom CSS -->
		<link href='<c:url value="/assets/css/styles.css"/>' rel="stylesheet"/>
		<link href='<c:url value="/assets/css/sidebarStyle.css"/>' rel="stylesheet"/>
		
		<!-- JQuery Script -->
		<script src='<c:url value="/assets/vendor/jquery/jquery-3.6.0.min.js"/>' ></script>
	</head>
	<body>
		<jsp:include page="views/sidebar/sidebar.jsp" />  
		
		<main>
			<!-- Call Body Content based on the Page -->
			<!-- Implement Single Main Page -->
			<c:choose>
			    <c:when test="${currentPage eq 'EmployeeHome'}">
			        <jsp:include page="views/pages/employeeHome.jsp" />  
			    </c:when>
			    
			    <c:when test="${currentPage eq 'EmployeeStatus'}">
			        <jsp:include page="views/pages/employeeStatus.jsp" />  
			    </c:when>
			    <c:when test="${currentPage eq 'EmployeeStatusAdd'}">
			        <jsp:include page="views/pages/employeeStatusAdd.jsp" />  
			    </c:when>
			    <c:when test="${statusEditPage eq 'EmployeeStatusEdit'}">
			        <jsp:include page="views/pages/employeeStatusEdit.jsp" />  
			    </c:when>
			    
			    <c:when test="${currentPage eq 'EmployeeCategories'}">
			        <jsp:include page="views/pages/employeeCategory.jsp" />  
			    </c:when>
			    <c:when test="${currentPage eq 'EmployeeCategoryAdd'}">
			        <jsp:include page="views/pages/employeeCategoryAdd.jsp" />  
			    </c:when>
			    <c:when test="${categoryEditPage eq 'EmployeeCategoryEdit'}">
			        <jsp:include page="views/pages/employeeCategoryEdit.jsp" />  
			    </c:when>
			    
			    <c:when test="${currentPage eq 'EmployeeTags'}">
			        <jsp:include page="views/pages/employeeTag.jsp" />  
			    </c:when>
			    <c:when test="${currentPage eq 'EmployeeTagsAdd'}">
			        <jsp:include page="views/pages/employeeTagAdd.jsp" />  
			    </c:when>
			    <c:when test="${tagsEditPage eq 'EmployeeTagsEdit'}">
			        <jsp:include page="views/pages/employeeTagEdit.jsp" />  
			    </c:when>
			    
			    <c:when test="${currentPage eq 'EmployeeUsers'}">
			        <jsp:include page="views/pages/employeeUsers.jsp" />  
			    </c:when>
			    <c:when test="${currentPage eq 'EmployeeUsersAdd'}">
			        <jsp:include page="views/pages/employeeUsersAdd.jsp" />  
			    </c:when>
			    <c:when test="${usersEditPage eq 'EmployeeUsersEdit'}">
			        <jsp:include page="views/pages/employeeUsersEdit.jsp" />  
			    </c:when>
			    
			    <c:when test="${currentPage eq 'EmployeeProducts'}">
			        <jsp:include page="views/pages/employeeProducts.jsp" />  
			    </c:when>
			    <c:when test="${currentPage eq 'EmployeeProductsAdd'}">
			        <jsp:include page="views/pages/employeeProductsAdd.jsp" />  
			    </c:when>
			    <c:when test="${productsEditPage eq 'EmployeeProductsEdit'}">
			        <jsp:include page="views/pages/employeeProductsEdit.jsp" />  
			    </c:when>
			    
			    <c:when test="${photosEditPage eq 'EmployeeProductPhotosEdit'}">
			        <jsp:include page="views/pages/employeePhotosEdit.jsp" />  
			    </c:when>
			    
			    <c:when test="${currentPage eq 'EmployeeOrders'}">
			        <jsp:include page="views/pages/employeeOrders.jsp" />  
			    </c:when>
			    <c:otherwise>
			        <p>Status ${currentPage} not found</p>
			    </c:otherwise>
			</c:choose> 
		</main>	
		
		<!-- Option 1: Bootstrap Bundle with Popper -->
		<script src='<c:url value="/assets/vendor/bootstrap-5.0.0-beta2-dist/js/bootstrap.bundle.min.js"/>' ></script>
		
		<script type="text/javascript">
			$('document').ready(function() {
			    $(".dropdown-toggle").click(function() {
			    	$(".dropdown-menu").toggle();
			    });
			});
			
			$(document).ready(function(){				
				//Alert Close listener
				$(".close").click(function(){
				  $("#popupAlert").alert("close");
				});
			});
			
			/* global bootstrap: false */
			(function () {
			  'use strict'
			  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
			  tooltipTriggerList.forEach(function (tooltipTriggerEl) {
			    new bootstrap.Tooltip(tooltipTriggerEl)
			  })
			})()
	   </script>
	</body>
</html>