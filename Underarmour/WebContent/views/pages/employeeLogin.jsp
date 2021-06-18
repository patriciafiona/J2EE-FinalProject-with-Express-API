<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container">
  <div class="row">
    <div class="col-md-9 col-lg-8 mx-auto">
    	<%
		    if (request.getParameter("status") != null) {
		        request.setAttribute("status", request.getParameter("status"));
		        if(request.getParameter("status_detail") != null){
		        	request.setAttribute("status_detail", request.getParameter("status_detail"));
		        }
		    }
		%>
		
		<c:choose>
			<c:when test="${status eq 'failed'}">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Attention!</strong> ${status_detail }
					<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
				    	<span aria-hidden="true">&times;</span>
				  	</button>
				</div>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
		
      <h3 class="login-heading mb-4">Login</h3>
      <form action="./EmployeeLogin" method="post" accept-charset="utf-8">
        <div class="form-label-group">
          <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus>
          <label for="inputEmail">Email address</label>
        </div>

        <div class="form-label-group">
          <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
          <label for="inputPassword">Password</label>
        </div>
        
        <div class="row">
        	<div class="col col-md-6">
        		<div class="form-group"> 
          	<label class="form-control-label text-muted">Captcha</label> 
          	<input type="password" id="inputCaptcha" name="custom_captcha_input" placeholder="Captcha" class="form-control" required> 
          </div>
        	</div>
        	<div class="col col-md-6">
        		<img class="img-fluid my-3" alt="Captcha" src="data:image/png;base64,${imageAsBase64}" />
        	</div>
        </div>
        
        <br>

        <div class="custom-control custom-checkbox mb-3">
          <input type="checkbox" name="rememberMe" class="custom-control-input" id="customCheck1">
          <label class="custom-control-label" for="customCheck1">Remember password</label>
        </div>
        <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">Sign in</button>
        <hr/>
        <div class="text-center">
          <span>Don't have account? <a class="small" href="./EmployeeRegister">Sign Up</a></span>
        </div>
      </form>
    </div>
  </div>
</div>