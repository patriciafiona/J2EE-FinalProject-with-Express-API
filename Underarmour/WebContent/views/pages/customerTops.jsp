<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5">
	<h1>${fn:toUpperCase(fn:substring(category, 0, 1))}${fn:toLowerCase(fn:substring(category, 1,fn:length(category)))} Tops</h1>
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item">
	    	<a href="${context}/" class="grey nounderline">Home</a>
	    </li>
	    <li class="breadcrumb-item active">
	    	<a href="${context}/CustomerProductByCategory?category=${fn:toUpperCase(fn:substring(category, 0, 1))}${fn:toLowerCase(fn:substring(category, 1,fn:length(category)))}" class="grey nounderline">
	    		${fn:toUpperCase(fn:substring(category, 0, 1))}${fn:toLowerCase(fn:substring(category, 1,fn:length(category)))}
	    	</a>
	    </li>
	    <li class="breadcrumb-item grey" aria-current="page">
	    	<a href="${context}/CustomerTops?category=${category}&tag=${tag}" class="grey nounderline">
	    		<b>Tops</b>
	    	</a>
	    </li>
	  </ol>
	</nav>
	<hr>
	
	<c:set var="totalCount" scope="session" value="${listProduct.size()}"/>
	<c:set var="perPage" scope="session"  value="30"/>
	<c:set var="pageStart" value="${param.start}"/>
	<fmt:formatNumber  var="totalPage" scope="session" value="${listProduct.size() / perPage + (listProduct.size() / perPage % 1 == 0 ? 0 : 0.5)}" 
    	type="number" pattern="#" />
	<c:if test="${empty pageStart or pageStart < 0}">
	       <c:set var="pageStart" value="0"/>
	</c:if>
	<c:if test="${totalCount < pageStart}">
	       <c:set var="pageStart" value="${pageStart - perPage}"/>
	</c:if>
	
	<div class="row d-flex justify-content-center">
		<c:choose>
			<c:when test="${listProduct.size() > 0}">
				<c:forEach items="${listProduct}" var="product" begin="${pageStart}" end="${pageStart + perPage - 1}">
					<div class="card listProducts my-3 mx-2 p-0" style="width: 18rem;">
					  <div class="product-img-container">
					  	<img class="product-img" src="assets/img/products/${product.getPhoto_01()}" alt="Product Image">
					  </div>
					  <div class="card-body">
					    <p class="book-title"><strong>${product.getName()}</strong></p>
					    <p class="card-text text-mute">
					    	<fmt:formatNumber currencySymbol="Rp." value = "${product.getPrice()}" type = "currency"/>
					    </p>
					  </div>
					  <div class="card-footer">
					  	<div class="row">
					  		<div class="col-md-9">
						  		<a href="./product/details/${product.getId()}" class="btn btn-primary">See details</a>
						  	</div>
						  	<div class="col-md-3">
						  		<form action="???" method="GET">
						  			<input type="hidden" name="productId" value="${product.getId()}"/>
						  			<button type="submit" class="btn btn-outline-danger rounded-circle">
						  				<i class="fa fa-heart-o" aria-hidden="true"></i>
						  			</button>
						  		</form>
						  	</div>
					  	</div>
					  </div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
		        <img class="noProductImgXl" src='<c:url value="/assets/img/search_not_found.png"/>' alt="no result"/>
		        <h3 class="text-center">No Product Found</h3>
		    </c:otherwise>
		</c:choose>
	</div>
	<c:if test="${listProduct.size() > 0}">
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		  	<c:choose>
				<c:when test="${empty pageStart or pageStart+1 > 1}">
		       		<li class="page-item"><a class="page-link" href="./CustomerTops?tag=men&start=${pageStart - 1}">Previous</a></li>
		       	</c:when>
		       	<c:otherwise>
		       		<li class="page-item disabled"><a class="page-link" href="./CustomerTops?tag=men&start=${pageStart - 1}">Previous</a></li>
		       	</c:otherwise>
			</c:choose>
		  	<c:forEach begin="1" end="${totalPage}" varStatus="loop">
			    <li class="page-item ${loop.index - 1 == pageStart ? 'active': ''}"><a class="page-link" href="./CustomerTops?tag=men&start=${loop.index - 1}">${loop.index}</a></li>
			</c:forEach>
			<c:choose>
		       	<c:when test="${empty pageStart or pageStart+1 < totalPage}">
		       		<li class="page-item"><a class="page-link" href="./CustomerTops?tag=men&start=${pageStart + 1}">Next</a></li>
		       	</c:when>
		       	<c:when test="${empty pageStart or totalPage == 0}">
		       		<li class="page-item disabled"><a class="page-link" href="./CustomerTops?tag=men&start=${pageStart + 1}">Next</a></li>
		       	</c:when>
		       	<c:otherwise>
		       		<li class="page-item disabled"><a class="page-link" href="./CustomerTops?tag=men&start=${pageStart + 1}">Next</a></li>
		       	</c:otherwise>
			</c:choose>
			
		  </ul>
		</nav>
	</c:if>
</div>