<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5 sidebarContainer">
	<h1>Edit Tag</h1>
	<hr>
	<form action="${context}/EmployeeTagsEdit" method="post" accept-charset="utf-8">
		<div class="mb-3">
		    <label for="inputId" class="form-label">Id</label>
		    <input type="text" value="${tag.getId() }" class="form-control" id="inputId" required disabled>
		    <input type="hidden" value="${tag.getId() }" name="id">
	  </div>
	  <div class="mb-3">
	    <label for="inputName" class="form-label">Tag Name</label>
	    <input type="text" value="${tag.getName() }" class="form-control" name="name" id="inputName" required>
	  </div>
	  <div class="row">
	  	<div class="col-md-1">
	  		<a href="${context}/EmployeeTags" class="btn btn-danger">Cancel</a>
	  	</div>
	  	<div class="col-md-2">
	  		<button type="submit" class="btn btn-primary">Submit</button>
	  	</div>
	  </div>
	 </form>
</div>