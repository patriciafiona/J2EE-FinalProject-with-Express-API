<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5 sidebarContainer">
	<h1>Add New Category</h1>
	<hr>
	<form action="./EmployeeCategoryAdd" method="post" accept-charset="utf-8">
	  <div class="mb-3">
	    <label for="inputName" class="form-label">Category Name</label>
	    <input type="text" class="form-control" name="name" id="inputName" required>
	  </div>
	  <div class="row">
	  	<div class="col-md-1">
	  		<a href="${context}/EmployeeCategories" class="btn btn-danger">Cancel</a>
	  	</div>
	  	<div class="col-md-2">
	  		<button type="submit" class="btn btn-primary">Submit</button>
	  	</div>
	  </div>
	 </form>
</div>