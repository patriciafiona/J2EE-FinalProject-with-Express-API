<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5 sidebarContainer ">
	<h1>Edit Photos</h1>
	<hr>
	<form action="./UploadProductImages" method="post" accept-charset="utf-8" enctype="multipart/form-data">
		<div class="row">
			<div class="col-md-12">
				<div class="card p-3 my-3">
					<label for="inputProductId" class="form-label">Product ID</label>
			    	<input type="text" class="form-control" id="inputProductId" value="${photo.getProduct_id() }" disabled>
					<input type="hidden" name="product_id" value="${photo.getProduct_id() }" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="card p-3 my-3">
					<h5>Photo 01</h5>
					<p class="red text-10">*You can empty this form if you don't want to change default photo or add another photo</p>
					<input name="file_01" type="file" class="form-control">
		            <hr>
		            <div class="row">
		            	<div class="col-md-6">
		            		<p>Current Image: </p>
		            	</div>
		            	<div class="col-md-6">
		            		<img src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_01(),' ', '')}"/>'
						  		class="productImgSm" alt="..." />
		            	</div>
		            </div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card p-3 my-3">
					<h5>Photo 02</h5>
					<p class="red text-10">*You can empty this form if you don't want to add another photo</p>
					<input name="file_02" type="file" class="form-control">
		            <hr>
		            <div class="row">
		            	<div class="col-md-6">
		            		<p>Current Image: </p>
		            	</div>
		            	<div class="col-md-6">
				            <c:choose>
								<c:when test="${!empty photo.getPhoto_02()}">
				            		<img src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_02(),' ', '')}"/>'
								  		class="productImgSm" alt="..." />
								</c:when>
								<c:otherwise>
							        <p><b>Still Empty</b></p>
							    </c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card p-3 my-3">
					<h5>Photo 03</h5>
					<p class="red text-10">*You can empty this form if you dont want to add another photo</p>
					<input name="file_03" type="file" class="form-control">
		            <hr>
		            <div class="row">
		            	<div class="col-md-6">
		            		<p>Current Image: </p>
		            	</div>
		            	<div class="col-md-6">
				            <c:choose>
								<c:when test="${!empty photo.getPhoto_03()}">
				            		<img src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_03(),' ', '')}"/>'
								  		class="productImgSm" alt="..." />
								</c:when>
								<c:otherwise>
							        <p><b>Still Empty</b></p>
							    </c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="card p-3 my-3">
					<h5>Photo 04</h5>
					<p class="red text-10">*You can empty this form if you don't want to add another photo</p>
					<input name="file_04" type="file" class="form-control">
		            <hr>
		            <div class="row">
		            	<div class="col-md-6">
		            		<p>Current Image: </p>
		            	</div>
		            	<div class="col-md-6">
				            <c:choose>
								<c:when test="${!empty photo.getPhoto_04()}">
				            		<img src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_04(),' ', '')}"/>'
								  		class="productImgSm" alt="..." />
								</c:when>
								<c:otherwise>
							        <p><b>Still Empty</b></p>
							    </c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card p-3 my-3">
					<h5>Photo 05</h5>
					<p class="red text-10">*You can empty this form if you don't want to add another photo</p>
					<input name="file_05" type="file" class="form-control">
		            <hr>
		            <div class="row">
		            	<div class="col-md-6">
		            		<p>Current Image: </p>
		            	</div>
		            	<div class="col-md-6">
				            <c:choose>
								<c:when test="${!empty photo.getPhoto_05()}">
				            		<img src='<c:url value="/assets/img/products/${fn:replace(photo.getPhoto_05(),' ', '')}"/>'
								  		class="productImgSm" alt="..." />
								</c:when>
								<c:otherwise>
							        <p><b>Still Empty</b></p>
							    </c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>
		<div class="row">
		  	<div class="col-md-1">
		  		<a href="${context}/EmployeeProducts" class="btn btn-danger">Cancel</a>
		  	</div>
		  	<div class="col-md-2">
		  		<button type="submit" class="btn btn-primary">Submit</button>
		  	</div>
		</div>
	</form>
	<br>
	<br>
</div>