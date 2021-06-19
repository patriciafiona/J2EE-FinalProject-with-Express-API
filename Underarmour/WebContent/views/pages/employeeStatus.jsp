<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5 sidebarContainer">
	<div class="row">
		<div class="col-md-10">
			<h1>Status</h1>
		</div>
		<div class="col-md-2">
			<a href="./EmployeeStatusAdd" class="btn btn-primary">Add New Status</a>
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
				<h3>What is Status?</h3>
				<p>Status is data to show a description of user data stored in the database.</p>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card my-3 p-3">
				<h4>Information</h4>
				<p>Size of Data: ${listStatus.size()}</p>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${listStatus.size() > 0}">
			<table class="table table-hover">
				<thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">Name</th>
				      <th scope="col">Actions</th>
				    </tr>
				  </thead>
				  <tbody>
					<c:forEach items="${listStatus}" var="status">
						<tr>
						    <th scope="row">${status.getId()}</th>
						    <td>${status.getName()}</td>
						    <td>
						    	<a href="./status/edit/${status.getId()}" class="btn btn-warning">Edit</a>
				      			<a href="#" data-id="${status.getId()}" class="deleteStatusDialog btn btn-danger" 
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
	        <h3 class="text-center">No Status Found</h3>
	    </c:otherwise>
	</c:choose>
</div>

<!-- Modal -->
<div class="modal fade" id="ConfirmDeleteModal" tabindex="-1" aria-labelledby="ConfirmDeleteModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Delete Status Confirmation</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Are you sure you want to delete this data?
        <div class="IdContainer mb-3">
		    <label for="bookId" class="form-label"><b>Status ID</b></label>
		    <input type="text" name="statusId" class="form-control" id="statusId" disabled/>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <a href="#" class="btn btn-danger deleteBookData">Delete</a>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
//Delete Modal
$(document).on("click", ".deleteStatusDialog", function () {
    var Id = $(this).data('id');
    $(".modal-body .IdContainer #statusId").val( Id );
    $(".modal-footer .deleteBookData").attr("href", "./status/delete/"+ Id)
});
 </script>