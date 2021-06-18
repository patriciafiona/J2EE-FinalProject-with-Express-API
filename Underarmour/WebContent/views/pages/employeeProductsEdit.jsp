<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5 sidebarContainer">
	<h1>Edit Product</h1>
	<hr>
	<form action="${context}/EmployeeProductsEdit" method="post" accept-charset="utf-8">
		<div class="mb-3">
		    <label for="inputId" class="form-label">Id</label>
		    <input type="text" value="${product.getId() }" class="form-control" id="inputId" required disabled>
		    <input type="hidden" value="${product.getId() }" name="id">
	  </div>
	  <div class="mb-3">
	    <label for="inputName" class="form-label">Name</label>
	    <input type="text" class="form-control" name="name" value="${product.getName() }" id="inputName" required>
	  </div>
	  <div class="row">
		  	<div class="col-md-6">
		  		<div class="mb-3">
			  		<label for="inputCategory" class="form-label">Category</label>
			    	<select id="inputCategory" name="category" class="form-select" aria-label="Default select">
					  <option selected>Select Category</option>
					  <c:forEach items="${listCategory}" var="category" varStatus="loop">
					  	<option value="${category.getId() }" ${product.getCategory() eq category.getId() ? 'selected': '' } >${category.getName() }</option>
					  </c:forEach>
					</select>
				</div>
		  	</div>
		  	<div class="col-md-6">
		  		<div class="mb-3">
				  	<label for="inputTag" class="form-label">Tag</label>
				    <select id="inputTag" name="tag" class="form-select" aria-label="Default select">
					  <option selected>Select Tag</option>
					  <c:forEach items="${listTag}" var="tag" varStatus="loop">
					  	<option value="${tag.getId() }" ${product.getTag() eq tag.getId() ? 'selected': '' } >${tag.getName() }</option>
					  </c:forEach>
					</select>
				</div>
		  </div>
	  </div>
	  <div class="row">
		  <div class="col-md-6">
		  		<div class="mb-4">
			  		<label for="inputPrice" class="form-label">Price</label>
			  		<div class="input-group flex-nowrap">
					  <span class="input-group-text" id="addon-wrapping">Rp.</span>
					  <input type="number" value="${product_price}" class="form-control" step="0.1" 
					  name="price" min="0" id="inputPrice" required/>
					</div>
				</div>
		  	</div>
		  	<div class="col-md-6">
		  		<div class="mb-3">
			  		<label for="inputStock" class="form-label">Stocks</label>
			  		<input type="number" value="${product.getStock() }" class="form-control" name="stock" min="0" id="inputStock" required/>
				</div>
		  	</div>
	  </div>
	  <div class="mb-3">
	    <label for="inputDesc" class="form-label">Description</label>
		<textarea name="description" id="inputDesc" class="form-control" required>${product.getDescription() }</textarea>
	  </div>
	  <div class="mb-3">
  		<label for="inputColor" class="form-label">Color</label>
  		<input type="color" value="${product.getColor() }" class="form-control form-control-color" name="color" id="inputColor" required/>
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
</div>