<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5 sidebarContainer">
	<h1>Edit Status</h1>
	<hr>
	<form action="${context}/EmployeeStatusEdit" method="post" accept-charset="utf-8">
		<div class="mb-3">
		    <label for="inputId" class="form-label">Id</label>
		    <input type="text" value="${status.getId() }" class="form-control" id="inputId" required disabled>
		    <input type="hidden" value="${status.getId() }" name="id">
	  </div>
	  <div class="mb-3">
	    <label for="inputName" class="form-label">Status Name</label>
	    <input type="text" value="${status.getName() }" class="form-control" name="name" id="inputName" required>
	  </div>
	  <div class="row">
	  	<div class="col-md-1">
	  		<a href="${context}/EmployeeStatus" class="btn btn-danger">Cancel</a>
	  	</div>
	  	<div class="col-md-2">
	  		<button type="submit" class="btn btn-primary">Submit</button>
	  	</div>
	  </div>
	 </form>
</div>