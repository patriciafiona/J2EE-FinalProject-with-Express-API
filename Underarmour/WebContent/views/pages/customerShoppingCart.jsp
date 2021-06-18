<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5">
	<h1>MY BAG</h1>
	<hr>
	<div class="row">
		<div class="col-md-8">
			<c:choose>
			    <c:when test="${listCart.size() > 0}">
			        <c:forEach items="${listCart}" var="product">
						<div class="card m-3 p-3">
							<div class="row">
								<div class="col col-md-2">
									<img class="img-fluid" src='<c:url value="/assets/img/products/${product.value.getProduct().getPhoto_01()}"/>' alt="book cover"/>
								</div>
								<div class="col col-md-7">
									<h5>${product.value.getProduct().getName()}</h5>
									<p>
										<fmt:formatNumber currencySymbol="Rp." value = "${product.value.getProduct().getPrice()}" type = "currency"/>
									</p>
								</div>
								<div class="col col-md-1">
									<p class="secondary">x${product.value.getQuantity()}</p>
								</div>
								<div class="col col-md-1">
									<a href="./product/details/${product.value.getProduct().getId()}">
										<i class="fa fa-pencil" aria-hidden="true"></i>
									</a>
								</div>
								<div class="col col-md-1">
									<a href="./cart/remove/${product.value.getProduct().getId()}">
										<i class="fa fa-trash" aria-hidden="true"></i>
									</a>
								</div>
							</div>
						</div>
					</c:forEach>
			    </c:when>    
			    <c:otherwise>
			        <img class="noProductImg" src='<c:url value="/assets/img/search_not_found.png"/>' alt="no result"/>
		        	<h3 class="text-center">No Product Found</h3>
			    </c:otherwise>
			</c:choose>
		</div>
		<div class="col-md-4">
			<div class="card my-3 p-3">
				<h3>Totals</h3>
				<c:choose>
			    	<c:when test="${totalPayment > 0.0}">
						<p><fmt:formatNumber currencySymbol="Rp." value = "${totalPayment}" type = "currency"/></p>
					</c:when>    
				    <c:otherwise>
				        <p>Rp.0,0</p>
				    </c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>