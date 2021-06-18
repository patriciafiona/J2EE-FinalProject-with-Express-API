<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<div class="bg-light-grey">
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item">
	    	<a href="${context}/" class="grey nounderline">Home</a>
	    </li>
	    <li class="breadcrumb-item">
	    	<a href="${context}/CustomerTops?tag=${product.getTag()}" class="grey nounderline">
	    		${product.getCategory_name()}
	    	</a>
	    </li>
	    <li class="breadcrumb-item grey" aria-current="page">
	    	<a href="${context}/Customer${product.getTag_name()}?tag=${product.getTag_name()}&category=${product.getCategory_name()}s" class="grey nounderline">
	    		${product.getTag_name()}
	    	</a>
	    </li>
	    <li class="breadcrumb-item active">
	    	<a href="${context}/product/details/${product.getId() }" class="grey nounderline">
	    		<b>${product.getName()}</b>
	    	</a>
	    </li>
	  </ol>
	</nav>
	
	<div class="row">
		<div class="col-md-2">
			<a data-target="#carouselProductImage" data-slide-to="0" href="#">
				<div class="small_detail_photo">
					<img class="product-img-sm" src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_01(),' ', '')}"/>' alt="Product Image">
				</div>
			</a>
			<c:if test="${!empty photo.getPhoto_02()}">
				<a data-target="#carouselProductImage" data-slide-to="1" href="#">
					<div class="small_detail_photo">
						<img class="product-img-sm" src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_02(),' ', '')}"/>' alt="Product Image">
					</div>
				</a>
			</c:if>
			<c:if test="${!empty photo.getPhoto_03()}">
				<a data-target="#carouselProductImage" data-slide-to="2" href="#">
					<div class="small_detail_photo">
						<img class="product-img-sm" src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_03(),' ', '')}"/>' alt="Product Image">
					</div>
				</a>
			</c:if>
			<c:if test="${!empty photo.getPhoto_04()}">
				<a data-target="#carouselProductImage" data-slide-to="3" href="#">
					<div class="small_detail_photo">
						<img class="product-img-sm" src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_04(),' ', '')}"/>' alt="Product Image">
					</div>
				</a>
			</c:if>
			<c:if test="${!empty photo.getPhoto_05()}">
				<a data-target="#carouselProductImage" data-slide-to="4" href="#">
					<div class="small_detail_photo">
						<img class="product-img-sm" src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_05(),' ', '')}"/>' alt="Product Image">
					</div>
				</a>
			</c:if>
		</div>
		
		<div class="col-md-6">
			<div id="carouselProductImage" class="carousel slide" data-ride="carousel">
			  <ol class="carousel-indicators">
			    <li data-target="#carouselProductImage" data-slide-to="0" class="active"></li>
			    <c:if test="${!empty photo.getPhoto_02()}">
			    	<li data-target="#carouselProductImage" data-slide-to="1"></li>
			    </c:if>
			    <c:if test="${!empty photo.getPhoto_03()}">
			    	<li data-target="#carouselProductImage" data-slide-to="2"></li>
			    </c:if>
			    <c:if test="${!empty photo.getPhoto_04()}">
			    	<li data-target="#carouselProductImage" data-slide-to="3"></li>
			    </c:if>
			    <c:if test="${!empty photo.getPhoto_05()}">
			    	<li data-target="#carouselProductImage" data-slide-to="4"></li>
			    </c:if>
			  </ol>
			  <div class="carousel-inner">
			    <div class="carousel-item active">
			      <img class="d-block w-100" src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_01(),' ', '')}"/>' alt="First slide">
			    </div>
			    <c:if test="${!empty photo.getPhoto_02()}">
			    	<div class="carousel-item">
				      <img class="d-block w-100" src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_02(),' ', '')}"/>' alt="Second slide">
				    </div>
			    </c:if>
			    <c:if test="${!empty photo.getPhoto_03()}">
			    	<div class="carousel-item">
				      <img class="d-block w-100" src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_03(),' ', '')}"/>' alt="Third slide">
				    </div>
			    </c:if>
			    <c:if test="${!empty photo.getPhoto_04()}">
			    	<div class="carousel-item">
				      <img class="d-block w-100" src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_04(),' ', '')}"/>' alt="Fourth slide">
				    </div>
			    </c:if>
			    <c:if test="${!empty photo.getPhoto_05()}">
			    	<div class="carousel-item">
				      <img class="d-block w-100" src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_05(),' ', '')}"/>' alt="Fifth slide">
				    </div>
			    </c:if>
			  </div>
			  <a class="carousel-control-prev" href="#carouselProductImage" role="button" data-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="sr-only">Previous</span>
			  </a>
			  <a class="carousel-control-next" href="#carouselProductImage" role="button" data-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="sr-only">Next</span>
			  </a>
			</div>
		</div>
		
		<div class="col-md-4">
			<div class="detailTitle">
				<p class="m-0 p-0 text-center">Details</p>
			</div>
			<div class="detailContainer">
				<div class="row">
					<div class="col-md-10">
						<h5>${product.getName() }</h5>
					</div>
					<div class="col-md-2">
						<form action="???" method="GET">
				  			<input type="hidden" name="productId" value="${product.getId()}"/>
				  			<button type="submit" class="btn btn-outline-danger rounded-circle">
				  				<i class="fa fa-heart-o" aria-hidden="true"></i>
				  			</button>
				  		</form>
					</div>
				</div>
				<div>
					<span class="fa fa-star checked"></span>
					<span class="fa fa-star checked"></span>
					<span class="fa fa-star checked"></span>
					<span class="fa fa-star"></span>
					<span class="fa fa-star"></span>
				</div>
				<p class="text-muted">
					<fmt:formatNumber currencySymbol="Rp." value = "${product.getPrice()}" type = "currency"/>
				</p>
				<br>
				<p class="text-10 mb-1 p-0"><b>Color:</b> ${product.getColor() }</p>
				<div style="width:50px; height:30px; background-color:${product.getColor()}"></div>
				<br>
				
				<form method="POST" action="${context}/CustomerAddToCart">
					<p class="mb-1"><b>Please Select A Size</b></p>
					<div class="input-group mb-3">
					  <div class="input-group-prepend">
					    <label class="input-group-text" for="inputGroupSelect01">Size</label>
					  </div>
					  <c:choose>
		   				 <c:when test="${product.getTag_name() eq 'Shoes' && product.getCategory_name() eq 'Kids'}">
							  <select name="size" class="custom-select" id="inputGroupSelect01">
							    <option selected>Choose...</option>
							    <c:forEach begin="30" end="38" varStatus="loop">
							    	<option value="${loop.index} ${loop.index == currentSizeInCart ? 'selected':'' } ">${loop.index}</option>
							    </c:forEach>
							  </select>
					  	</c:when>
					  	<c:when test="${product.getTag_name() eq 'Shoes' && product.getCategory_name() ne 'Kids'}">
							  <select name="size" class="custom-select" id="inputGroupSelect01">
							    <option selected>Choose...</option>
							    <c:forEach begin="36" end="45" varStatus="loop">
							    	<option value="${loop.index} ${loop.index == currentSizeInCart ? 'selected':'' }">${loop.index}</option>
							    </c:forEach>
							  </select>
					  	</c:when>
					  	<c:otherwise>
					  		<select name="size" class="custom-select" id="inputGroupSelect01">
							    <option selected>Choose...</option>
							    <option value="XS" ${currentSizeInCart eq 'XS' ? 'selected':'' }>XS</option>
							    <option value="S" ${currentSizeInCart eq 'S' ? 'selected':'' }>S</option>
							    <option value="M" ${currentSizeInCart eq 'M' ? 'selected':'' }>M</option>
							    <option value="L" ${currentSizeInCart eq 'L' ? 'selected':'' }>L</option>
							    <option value="XL" ${currentSizeInCart eq 'XL' ? 'selected':'' }>XL</option>
							    <option value="XXL" ${currentSizeInCart eq 'XXL' ? 'selected':'' }>XXL</option>
							  </select>
					  	</c:otherwise>
					  </c:choose>
					</div>
					Current size${currentSizeInCart }
					<p class="mb-1"><b>Quantity</b></p>
					<div class="input-group mb-3">
					  <div class="input-group-prepend">
					    <span class="input-group-text" id="inputGroup-sizing-default">Quantity</span>
					  </div>
					  <input type="hidden" name="productId" value="${product.getId()}" />
					  <input name="quantity" type="number" value="${currentQuantityInCart > 0 ? currentQuantityInCart:0 }" min="0" max="1000" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
					</div>
					<div class="row">
					  	<div class="col-md-3">
					  		<a href="${context}/CustomerTops?tag=${product.getCategory_name()}" class="btn btn-danger">Cancel</a>
					  	</div>
					  	<div class="col-md-4">
					  		<button type="submit" class="btn btn-primary">
					  			${!empty currentQuantityInCart and !empty currentSizeInCart ? 'Update':'Add' } To Bag <i class="fa fa-shopping-bag" aria-hidden="true"></i>
					  		</button>
					  	</div>
					  </div>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="container mb-5 p-3 background-white">
	<h3>Product DNA</h3>
	<pre class="detail_txt">${product.getDescription()}</pre>
</div>