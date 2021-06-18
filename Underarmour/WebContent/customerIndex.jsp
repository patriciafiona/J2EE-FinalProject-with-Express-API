<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page trimDirectiveWhitespaces="true" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<%
	String uri = request.getRequestURI().toString();
	String currentPage = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
	pageContext.setAttribute("currentPage", currentPage);
	
	if(uri.indexOf("product/details") > 0 ){
		pageContext.setAttribute("productDetail", "CustomerProductDetail");
	}
	
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
		
		<!-- JQuery Script -->
		<script src='<c:url value="/assets/vendor/jquery/jquery-3.6.0.min.js"/>' ></script>
	</head>
	<body>
		<jsp:include page="views/navbar/navbar.jsp" />  
		
		<!-- Call Body Content based on the Page -->
		<!-- Implement Single Main Page -->
		<c:choose>
		    <c:when test="${currentPage eq 'CustomerHome' || currentPage eq ''}">
		        <jsp:include page="views/pages/customerHome.jsp" />  
		    </c:when>
		    <c:when test="${currentPage eq 'CustomerTops'}">
		        <jsp:include page="views/pages/customerTops.jsp" />  
		    </c:when>
		    <c:when test="${currentPage eq 'CustomerBottoms'}">
		        <jsp:include page="views/pages/customerBottoms.jsp" />  
		    </c:when>
		    <c:when test="${currentPage eq 'CustomerShoes'}">
		        <jsp:include page="views/pages/customerShoes.jsp" />  
		    </c:when>
		    <c:when test="${productDetail eq 'CustomerProductDetail'}">
		        <jsp:include page="views/pages/customerProductDetail.jsp" />  
		    </c:when>
		    <c:when test="${currentPage eq 'CustomerShoppingCart'}">
		        <jsp:include page="views/pages/customerShoppingCart.jsp" />  
		    </c:when>
		    <c:when test="${currentPage eq 'CustomerProductByCategory'}">
		        <jsp:include page="views/pages/customerProductByCategory.jsp" />  
		    </c:when>
		    <c:when test="${currentPage eq 'CustomerProfile'}">
		        <jsp:include page="views/pages/customerProfile.jsp" />  
		    </c:when>
		    <c:otherwise>
		        <p>Status ${currentPage} not found</p>
		    </c:otherwise>
		</c:choose>
		
		<jsp:include page="views/footer/footer.jsp" />  
		
		<!-- Option 1: Bootstrap Bundle with Popper -->
		<script src='<c:url value="/assets/vendor/bootstrap-5.0.0-beta2-dist/js/bootstrap.bundle.min.js"/>' ></script>
		
		<script type="text/javascript">
			$('document').ready(function() {
			    $("#dropdown-toggle-men").click(function() {
			    	$("#dropdown-menu-men").toggle();
			    });
			    $("#dropdown-toggle-women").click(function() {
			    	$("#dropdown-menu-women").toggle();
			    });
			    $("#dropdown-toggle-kids").click(function() {
			    	$("#dropdown-menu-kids").toggle();
			    });
			});
			
			$(document).ready(function(){				
				//Alert Close listener
				$(".close").click(function(){
				  $("#popupAlert").alert("close");
				});
			});
	   </script>
	</body>
</html>