<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5 sidebarContainer">
	<div class="row">
		<div class="col-md-10">
			<h1>Category</h1>
		</div>
		<div class="col-md-2">
			<a href="./EmployeeCategoryAdd" class="btn btn-primary">Add New Category</a>
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
		<c:otherwise></c:otherwise>
	</c:choose>
	
	<div class="row">
		<div class="col-md-9">
			<div class="card my-3 p-3">
				<h3>What is Category?</h3>
				<p>Category is data to show a description of the category of 
				goods that will be displayed to the user. Examples of categories are 'Men', 'Women', and 'Kids'.</p>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card my-3 p-3">
				<h4>Information</h4>
				<p>Size of Data: ${listCategory.size()}</p>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${listCategory.size() > 0}">
			<table class="table table-hover">
				<thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">Name</th>
				      <th scope="col">Actions</th>
				    </tr>
				  </thead>
				  <tbody>
					<c:forEach items="${listCategory}" var="category">
						<tr>
						    <th scope="row">${category.getId()}</th>
						    <td>${category.getName()}</td>
						    <td>
						    	<a href="./category/edit/${category.getId()}" class="btn btn-warning">Edit</a>
				      			<a href="#" data-id="${category.getId()}" class="deleteCategoryDialog btn btn-danger" 
				      			data-bs-toggle="modal" 
				      			data-bs-target="#ConfirmDeleteModal">Delete</a>
						    </td>
						</tr>
					</c:forEach>
				  </tbody>
			</table>
		</c:when>
		<c:otherwise>
	        <img class="noProductImgXl" src='<c:url value="/assets/img/search_not_found.png"/>' alt="no result"/>
	        <h3>No Categories Found</h3>
	    </c:otherwise>
	</c:choose>
</div>

<!-- Modal -->
<div class="modal fade" id="ConfirmDeleteModal" tabindex="-1" aria-labelledby="ConfirmDeleteModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Delete Category Confirmation</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Are you sure you want to delete this data?
        <div class="IdContainer mb-3">
		    <label for="categoryId" class="form-label"><b>Category ID</b></label>
		    <input type="text" name="categoryId" class="form-control" id="categoryId" disabled/>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <a href="#" class="btn btn-danger deleteCategoryData">Delete</a>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
//Delete Modal
$(document).on("click", ".deleteCategoryDialog", function () {
    var Id = $(this).data('id');
    $(".modal-body .IdContainer #categoryId").val( Id );
    $(".modal-footer .deleteCategoryData").attr("href", "./category/delete/"+ Id)
});
 </script>