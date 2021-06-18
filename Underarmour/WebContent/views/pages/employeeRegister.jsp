<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container">
  <div class="row">
    <div class="col-md-9 col-lg-8 mx-auto">
    	
    	<%
		    if (request.getParameter("status") != null) {
		        request.setAttribute("status", request.getParameter("status"));
		    }
		%>
		
		<c:choose>
			<c:when test="${status eq 'passwordNotSame'}">
				<div class="alert alert-danger alert-dismissible show ${registerStatus == 'passwordNotSame' ? 'show' : ''}" role="alert">
					<strong>Attention!</strong> Your Password is not same. Please try again...
					<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
				    	<span aria-hidden="true">&times;</span>
				  	</button>
				</div>
			</c:when>
			<c:when test="${status eq 'failed'}">
				<div class="alert alert-danger alert-dismissible show ${registerStatus == 'passwordNotSame' ? 'show' : ''}" role="alert">
					<strong>Attention!</strong> Register failed. Please try again...
					<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
				    	<span aria-hidden="true">&times;</span>
				  	</button>
				</div>
			</c:when>
			<c:when test="${status eq 'success'}">
				<div class="alert alert-success alert-dismissible show ${registerStatus == 'success' ? 'show' : ''}" role="alert">
					<strong>Success!</strong> Congratulation, your data successful stored in Database
					<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
				    	<span aria-hidden="true">&times;</span>
				  	</button>
				</div>
			</c:when>
			<c:when test="${status eq 'alreadyRegistered'}">
				<div class="alert alert-warning alert-dismissible show ${registerStatus == 'success' ? 'show' : ''}" role="alert">
					<strong>Attention!</strong> Your account already registered. Please use another data to register
					<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
				    	<span aria-hidden="true">&times;</span>
				  	</button>
				</div>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
    	
      <h3 class="login-heading mb-4">Register</h3>
      <form action="./EmployeeRegister" method="post" accept-charset="utf-8">
      	<div class="form-label-group">
          <input type="text" id="inputName" name="name" class="form-control" placeholder="Your name" required autofocus>
          <label for="inputName">Name</label>
        </div>
        
        <div class="form-label-group">
          <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus>
          <label for="inputEmail">Email address</label>
        </div>
        
        <div class="form-label-group">
          <input type="date" id="inputBod" name="bod" class="form-control" required autofocus>
          <label for="inputBod">Birthday</label>
        </div>
        
        <div class="form-label-group">
          <select name="user_status" class="form-select" aria-label="Default select example">
			  <option selected>Select Your Status</option>
			  <c:forEach items="${listStatus}" var="status" varStatus="loop">
			  	<c:choose>
					<c:when test="${status.getName() ne 'Customer'}">
				  		<option value="${status.getId() }">${status.getName() }</option>
				  	</c:when>
				  	<c:otherwise></c:otherwise>
				  </c:choose>
			  </c:forEach>
			</select>
        </div>

        <div class="form-label-group">
          <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
          <label for="inputPassword">Password</label>
        </div>
        
        <div class="form-label-group">
          <input type="password" id="reinputPassword" name="recheck_password" class="form-control" placeholder="Password" required>
          <label for="reinputPassword">Re-input Password</label>
        </div>
        
        <input type="hidden" name="status" value="1">
        
        <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">Sign Up</button>
        <hr/>
        <div class="text-center">
          <span>Already have account? <a class="small" href="./EmployeeLogin">Sign In</a></span>
        </div>
      </form>
    </div>
  </div>
</div>