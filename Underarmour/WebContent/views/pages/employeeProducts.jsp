<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5 sidebarContainer">
	<div class="row">
		<div class="col-md-10">
			<h1>Product</h1>
		</div>
		<div class="col-md-2">
			<a href="./EmployeeProductsAdd" class="btn btn-primary">Add New Product</a>
		</div>
	</div>
	<hr>
	
	<%
	    if (request.getParameter("status") != null) {
	        request.setAttribute("status", request.getParameter("status"));
	    }
	%>
	
	<c:choose>
		<c:when test="${status eq 'addSuccess'}">
			<div class="alert alert-success alert-dismissible fade show" role="alert">
				<strong>Success!</strong> Your data already saved in the database...
				<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
			    	<span aria-hidden="true">&times;</span>
			  	</button>
			</div>
		</c:when>
		<c:when test="${status eq 'addFailed'}">
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
				<strong>Attention!</strong> Your data isn't stored in the database!
				<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
			    	<span aria-hidden="true">&times;</span>
			  	</button>
			</div>
		</c:when>
		
		<c:when test="${status eq 'editSuccess'}">
			<div class="alert alert-success alert-dismissible fade show" role="alert">
				<strong>Success!</strong> Your data already updated to the database...
				<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
			    	<span aria-hidden="true">&times;</span>
			  	</button>
			</div>
		</c:when>
		<c:when test="${status eq 'editFailed'}">
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
				<strong>Attention!</strong> Your data isn't updated to the database!
				<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
			    	<span aria-hidden="true">&times;</span>
			  	</button>
			</div>
		</c:when>
		
		<c:when test="${status eq 'deleteSuccess'}">
			<div class="alert alert-success alert-dismissible fade show" role="alert">
				<strong>Success!</strong> Your data already deleted from the database...
				<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
			    	<span aria-hidden="true">&times;</span>
			  	</button>
			</div>
		</c:when>
		<c:when test="${status eq 'deleteFailed'}">
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
				<strong>Attention!</strong> Your data isn't deleted from the database!
				<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
			    	<span aria-hidden="true">&times;</span>
			  	</button>
			</div>
		</c:when>
		
		<c:when test="${status eq 'updatePhotoSuccess'}">
			<div class="alert alert-success alert-dismissible fade show" role="alert">
				<strong>Success!</strong> Your product photos updated successful...
				<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
			    	<span aria-hidden="true">&times;</span>
			  	</button>
			</div>
		</c:when>
		<c:when test="${status eq 'updatePhotoFailed'}">
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
				<strong>Attention!</strong>  Your product photos failed to update
				<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
			    	<span aria-hidden="true">&times;</span>
			  	</button>
			</div>
		</c:when>
		<c:otherwise></c:otherwise>
	</c:choose>
	
	<div class="row">
		<div class="col-md-9">
			<div class="card my-3 p-3">
				<h3>What is Product?</h3>
				<p>Product is a collection of data containing store goods that will be sold to consumers.</p>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card my-3 p-3">
				<h4>Information</h4>
				<p>Size of Data: ${listProduct.size()}</p>
			</div>
		</div>
	</div>
	
	<c:set var="totalCount" scope="session" value="${listProduct.size()}"/>
	<c:set var="perPage" scope="session"  value="10"/>
	<c:set var="pageStart" value="${param.start}"/>
	<fmt:formatNumber  var="totalPage" scope="session" value="${listProduct.size() / perPage + (listProduct.size() / perPage % 1 == 0 ? 0 : 0.5)}" 
    	type="number" pattern="#" />
	<c:if test="${empty pageStart or pageStart < 0}">
	       <c:set var="pageStart" value="0"/>
	</c:if>
	<c:if test="${totalCount < pageStart}">
	       <c:set var="pageStart" value="${pageStart - perPage}"/>
	</c:if>
	
	<div class="table-responsive">
		<c:choose>
			<c:when test="${listProduct.size() > 0}">
				<table class="table table-hover">
					<thead>
					    <tr>
					      <th scope="col">ID</th>
					      <th scope="col">Name</th>
					      <th scope="col">Category</th>
					      <th scope="col">Tag</th>
					      <th scope="col">Rating</th>
					      <th scope="col">Price</th>
					      <th scope="col">Stock</th>
					      <th scope="col">Color</th>
					      <th scope="col">Description</th>
					      <th scope="col">Photos*</th>
					      <th scope="col">Created At</th>
					      <th scope="col">Updated At</th>
					      <th scope="col">Actions</th>
					    </tr>
					  </thead>
					  <tbody>
						<c:forEach items="${listProduct}" var="product" begin="${(pageStart*perPage) > 0 ? (pageStart*perPage):0}" 
							end="${(pageStart*perPage) + perPage -1}">
							<tr>
							    <th scope="row">${product.getId()}</th>
							    <td>${product.getName()}</td>
							    <td>${product.getCategory_name()}</td>
							    <td>${product.getTag_name()}</td>
							    <td>${product.getRating()}</td>
							    <td>
							    	<fmt:formatNumber currencySymbol="Rp." value = "${product.getPrice()}" type = "currency"/>
							    </td>
							    <td>${product.getStock()}</td>
							    <td>
							    	<div class="rounded-circle" style="background-color: ${product.getColor()}; width: 40px; height: 40px;"></div>
							    </td>
							    <td>
							    	<c:choose>
										<c:when test="${fn:length(product.getDescription()) > 100}">
							    			${fn:substring(product.getDescription(), 0, 100)}...
							    		</c:when>
							    		<c:otherwise>
							    			${product.getDescription()}
							    		</c:otherwise>
							    	</c:choose>
							    </td>
							    <td>
							    	<a href="./upload_images/${product.getId()}" >
							    		<img src='<c:url value="/assets/img/products/${fn:replace(product.getPhoto_01(),' ', '')}"/>'
					  					class="productImgSm" alt="..." />
					  				</a>
							    </td>
							    <td>${product.getCreated_at()}</td>
							    <td>${product.getUpdated_at()}</td>
							    <td>
							    	<a href="./product/edit/${product.getId()}" class="btn btn-warning">Edit</a>
					      			<a href="#" data-id="${product.getId()}" class="deleteDialog btn btn-danger" 
					      			data-bs-toggle="modal" 
					      			data-bs-target="#ConfirmDeleteModal">Delete</a>
							    </td>
							</tr>
						</c:forEach>
					  </tbody>
				</table>
			</c:when>
			<c:otherwise>
		        <img class="noProductImg" src='<c:url value="/assets/img/search_not_found.png"/>' alt="no result"/>
		        <h3 class="text-center">No Products Found</h3>
		    </c:otherwise>
		</c:choose>
	</div>
	<c:if test="${listProduct.size() > 0}">
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		  	<c:choose>
				<c:when test="${empty pageStart or pageStart+1 > 1}">
		       		<li class="page-item"><a class="page-link" href="./EmployeeProducts?tag=men&start=${pageStart - 1}">Previous</a></li>
		       	</c:when>
		       	<c:otherwise>
		       		<li class="page-item disabled"><a class="page-link" href="./EmployeeProducts?tag=men&start=${pageStart - 1}">Previous</a></li>
		       	</c:otherwise>
			</c:choose>
		  	<c:forEach begin="1" end="${totalPage}" varStatus="loop">
			    <li class="page-item ${loop.index - 1 == pageStart ? 'active': ''}"><a class="page-link" href="./EmployeeProducts?tag=men&start=${loop.index - 1}">${loop.index}</a></li>
			</c:forEach>
			<c:choose>
		       	<c:when test="${empty pageStart or pageStart+1 < totalPage}">
		       		<li class="page-item"><a class="page-link" href="./EmployeeProducts?tag=men&start=${pageStart + 1}">Next</a></li>
		       	</c:when>
		       	<c:when test="${empty pageStart or totalPage == 0}">
		       		<li class="page-item disabled"><a class="page-link" href="./EmployeeProducts?tag=men&start=${pageStart + 1}">Next</a></li>
		       	</c:when>
		       	<c:otherwise>
		       		<li class="page-item disabled"><a class="page-link" href="./EmployeeProducts?tag=men&start=${pageStart + 1}">Next</a></li>
		       	</c:otherwise>
			</c:choose>
			
		  </ul>
		</nav>
	</c:if>
</div>

<!-- Modal -->
<div class="modal fade" id="ConfirmDeleteModal" tabindex="-1" aria-labelledby="ConfirmDeleteModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Delete Products Confirmation</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Are you sure you want to delete this data?
        <div class="IdContainer mb-3">
		    <label for="product_id" class="form-label"><b>Product ID</b></label>
		    <input type="text" name="product_id" class="form-control" id="product_id" disabled/>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <a href="#" class="btn btn-danger deleteData">Delete</a>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
//Delete Modal
$(document).on("click", ".deleteDialog", function () {
    var Id = $(this).data('id');
    $(".modal-body .IdContainer #product_id").val( Id );
    $(".modal-footer .deleteData").attr("href", "./product/delete/"+ Id)
});
 </script>