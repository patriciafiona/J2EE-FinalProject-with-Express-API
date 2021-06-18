<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5 sidebarContainer ">
	<h1>Add New User</h1>
	<hr>
	<form action="./EmployeeUsersAdd" method="post" accept-charset="utf-8">
	  <div class="mb-3">
	    <label for="inputName" class="form-label">Name</label>
	    <input type="text" class="form-control" name="name" id="inputName" required>
	  </div>
	  <div class="mb-3">
	  	<label for="inputEmail" class="form-label">Email address</label>
        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus>
      </div>
      
      <div class="row">
	  	<div class="col-md-4">
	  		<div class="mb-3">
			    <label for="inputBOD" class="form-label">Birthday</label>
			    <input type="date" name="bod" class="form-control" id="inputBOD" required>
			  </div>
	  	</div>
	  	<div class="col-md-5">
	  		<div class="mb-3">
			    <label for="inputPhoneNumber" class="form-label">Phone Number</label>
			    <input type="text" name="phoneNumber" class="form-control" id="inputPhoneNumber" required>
			  </div>
	  	</div>
	  	<div class="col-md-3">
	  		<div class="mb-3">
			    <label for="inputStatus" class="form-label">Status</label>
		        <select id="inputStatus" name="user_status" class="form-select" aria-label="Default select example">
				  <option selected>Select Your Status</option>
				  <c:forEach items="${listStatus}" var="status" varStatus="loop">
				  	<option value="${status.getId() }">${status.getName() }</option>
				  </c:forEach>
				</select>
			  </div>
	  	</div>
	  </div>
	  
	  <div class="mb-3">
	    <label for="inputAddress" class="form-label">Address</label>
		<textarea name="address" id="inputAddres" class="form-control" required></textarea>
	  </div>

      <div class="mb-3">
      	<label for="inputPassword" class="form-label">Password</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
      </div>
      
	  <div class="row">
	  	<div class="col-md-1 mb-5">
	  		<a href="${context}/EmployeeUsers" class="btn btn-danger">Cancel</a>
	  	</div>
	  	<div class="col-md-2 mb-5">
	  		<button type="submit" class="btn btn-primary">Submit</button>
	  	</div>
	  </div>
	 </form>
</div>