<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5 sidebarContainer">
	<div class="row">
		<div class="col-md-10">
			<h1>Users</h1>
		</div>
		<div class="col-md-2">
			<a href="./EmployeeUsersAdd" class="btn btn-primary">Add New Users</a>
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
				<h3>What is User?</h3>
				<p>The user is the party who uses this application.</p>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card my-3 p-3">
				<h4>Information</h4>
				<p>Size of Data: ${listUser.size()}</p>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${listUser.size() > 0}">
			<table class="table table-hover">
				<thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">Name</th>
				      <th scope="col">Email</th>
				      <th scope="col">Status</th>
				      <th scope="col">Birthday</th>
				      <th scope="col">Address</th>
				      <th scope="col">Phone Number</th>
				      <th scope="col">Photo</th>
				      <th scope="col">Created At</th>
				      <th scope="col">Updated At</th>
				      <th scope="col">Actions</th>
				    </tr>
				  </thead>
				  <tbody>
					<c:forEach items="${listUser}" var="user">
						<tr>
						    <th scope="row">${user.getId()}</th>
						    <td>${user.getName()}</td>
						    <td>${user.getEmail()}</td>
						    <td>${user.getStatus_name()}</td>
						    <td>${user.getBod()}</td>
						    <td>${user.getAddress()}</td>
						    <td>${user.getPhone_number()}</td>
						    <td>
						    	<img src='<c:url value="/assets/img/user_profile/${fn:replace(user.getPhoto(),' ', '')}"/>'
					  				class="userProfileSm" alt="..." />
					  		</td>
						    <td>${user.getCreated_at()}</td>
						    <td>${user.getUpdated_at()}</td>
						    <td>
						    	<a href="./user/edit/${user.getId()}" class="btn btn-warning">Edit</a>
				      			<a href="#" data-id="${user.getId()}" class="deleteDialog btn btn-danger" 
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
	        <h3 class="text-center">No User Found</h3>
	    </c:otherwise>
	</c:choose>
</div>

<!-- Modal -->
<div class="modal fade" id="ConfirmDeleteModal" tabindex="-1" aria-labelledby="ConfirmDeleteModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Delete User Confirmation</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Are you sure you want to delete this data?
        <div class="IdContainer mb-3">
		    <label for="userId" class="form-label"><b>User ID</b></label>
		    <input type="text" name="userId" class="form-control" id="userId" disabled/>
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
    $(".modal-body .IdContainer #userId").val( Id );
    $(".modal-footer .deleteData").attr("href", "./user/delete/"+ Id)
});
 </script>