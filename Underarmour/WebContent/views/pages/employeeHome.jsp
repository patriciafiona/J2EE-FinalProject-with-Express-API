<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="container my-5 sidebarContainer">
	<div class="row">
		<h1>Dashboard</h1>
		<hr>
		
		<%
		    if (request.getParameter("status") != null) {
		        request.setAttribute("status", request.getParameter("status"));
		    }
		%>
		
		<c:choose>
			<c:when test="${status eq 'updateDataSuccess'}">
				<div class="alert alert-success alert-dismissible fade show" role="alert">
					<strong>Success!</strong> Your data already updated to the database...
					<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
				    	<span aria-hidden="true">&times;</span>
				  	</button>
				</div>
			</c:when>
			<c:when test="${status eq 'updateDataFailed'}">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Attention!</strong> Your data isn't stored in the database!
					<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
				    	<span aria-hidden="true">&times;</span>
				  	</button>
				</div>
			</c:when>
			
			<c:when test="${status eq 'changePasswordSuccess'}">
				<div class="alert alert-success alert-dismissible fade show" role="alert">
					<strong>Success!</strong> Your password already updated to the database...
					<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
				    	<span aria-hidden="true">&times;</span>
				  	</button>
				</div>
			</c:when>
			<c:when test="${status eq 'changePasswordFailed'}">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Attention!</strong> Your password isn't updated to the database!
					<button id="popupAlert" type="button" class="close btn_alertDialog" data-dismiss="alert" aria-label="Close">
				    	<span aria-hidden="true">&times;</span>
				  	</button>
				</div>
			</c:when>

			<c:otherwise></c:otherwise>
		</c:choose>
		
		<div class="col-md-8">
			<div class="card my-3 p-3">
				<h3>Your Data</h3>
				<hr>
				<form action="./UpdateUserData" method="post" enctype="multipart/form-data" accept-charset="utf-8">
					<div class="text-center">
					  <img src='<c:url value="/assets/img/user_profile/${fn:replace(userData.getPhoto(),' ', '')}"/>'
					  class="rounded-circle userProfile" alt="..." />
					</div>
				  <div class="mb-3">
				    <label for="inputName" class="form-label">Name</label>
				    <input type="hidden" name="user_id" value="${userData.getId()}" />
				    <input type="hidden" name="page" value="home"/>
				    <input type="text" class="form-control" name="name" value="${userData.getName()}" id="inputName" required>
				  </div>
				  <div class="mb-3">
				    <label for="inputEmail" class="form-label">Email</label>
				    <input type="email" class="form-control" name="email" value="${userData.getEmail()}" id="inputEmail" required>
				  </div>
				  <div class="row">
				  	<div class="col-md-4">
				  		<div class="mb-3">
						    <label for="inputBOD" class="form-label">Birthday</label>
						    <input type="date" name="bod" class="form-control" value="${userData.getBod()}" id="inputBOD" required>
						  </div>
				  	</div>
				  	<div class="col-md-6">
				  		<div class="mb-3">
						    <label for="inputPhoneNumber" class="form-label">Phone Number</label>
						    <input type="text" name="phoneNumber" class="form-control" value="${userData.getPhone_number()}" id="inputPhoneNumber" required>
						  </div>
				  	</div>
				  	<div class="col-md-2">
				  		<div class="mb-3">
						    <label for="inputStatus" class="form-label">Status</label>
						    <input type="text" class="form-control" value="${userData.getStatus()}" id="inputStatus" disabled>
						    <input type="hidden" name="status" value="${userData.getStatus()}" required>
						  </div>
				  	</div>
				  </div>
				  <div class="mb-3">
				    <label for="inputAddress" class="form-label">Address</label>
				    <c:choose>
					    <c:when test="${userData.getAddress() eq '' || userData.getAddress() eq null}">
					        <textarea name="address" id="inputAddres" class="form-control" required></textarea>
					    </c:when>
					    <c:otherwise>
					        <textarea name="address" id="inputAddres" class="form-control" required><c:out value="${userData.getAddress()}" /></textarea>
					    </c:otherwise>
					</c:choose>
				  </div>
				  <div class="mb-3">
					  	<div class="input-group mb-3 px-2 py-2 rounded-pill bg-white shadow-sm">
			                <input id="upload" name="file" type="file" onchange="readURL(this);" class="form-control border-0">
			                <label id="upload-label" for="upload" class="font-weight-light text-muted">Choose file</label>
			                <div class="input-group-append">
			                    <label for="upload" class="btn btn-light m-0 rounded-pill px-4"> <i class="fa fa-cloud-upload mr-2 text-muted"></i><small class="text-uppercase font-weight-bold text-muted">Choose file</small></label>
			                </div>
			            </div>
		          </div>
		          <div class="mb-3">
		          	<p class="red"><i>No need to fill in if you don't want to change the value - The image uploaded will be rendered inside the box below.</i></p>
		          </div>
		
		          <!-- Uploaded image area-->
		          <div class="image-area mt-4"><img id="imageResult" src="#" alt="" class="img-fluid rounded shadow-sm mx-auto d-block"></div>
				  <button type="submit" class="btn btn-primary">Update Data</button>
				 </form>
			</div>
		</div>
		<div class="col-md-4">
			<div class="row">
				<div class="card my-3 p-3">
					<h3>Hello,</h3>
					<h1>${userData.getName()}</h1>
					<hr>
					<p>${todayDate }</p>
				</div>
			</div>
			<div class="row">
				<div class="card my-3 p-3">
					<h5>Change Password</h5>
					<form action="./ChangePassword" method="post">
						<div class="mb-3">
						    <label for="inputCurrentPassword" class="form-label">Current Password</label>
						    <input type="hidden" name="email" value="${userData.getEmail()}" />
						    <input type="password" class="form-control" name="currentPassword" id="inputCurrentPassword" required>
						</div>
						<div class="mb-3">
						    <label for="inputNewPassword" class="form-label">New Password</label>
						    <input type="password" class="form-control" name="newPassword" id="inputNewPassword" required>
						</div>
						<div class="mb-3">
						    <label for="reinputNewPassword" class="form-label">Re-input New Password</label>
						    <input type="password" class="form-control" name="reinput_newPassword" id="reinputNewPassword" required>
						</div>
						<button type="submit" class="btn btn-danger">Change Password</button>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="card my-3 p-3">
					<h5>Delete Account</h5>
					<form action="./DeleteUser" method="post">
						<div class="mb-3">
						    <label for="inputNewPassword" class="form-label">Password</label>
						    <input type="hidden" name="id" value="${userData.getId()}" />
						    <input type="password" class="form-control" name="password" id="inputNewPassword" required>
						</div>
						<div class="mb-3">
						    <label for="reinputNewPassword" class="form-label">Re-input Password</label>
						    <input type="password" class="form-control" name="reinput_Password" id="reinputPassword" required>
						</div>
						<button type="submit" class="btn btn-danger">Delete My Account</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#imageResult')
                .attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

$(function () {
    $('#upload').on('change', function () {
        readURL(input);
    });
});

/*  ==========================================
    SHOW UPLOADED IMAGE NAME
* ========================================== */
var input = document.getElementById( 'upload' );
var infoArea = document.getElementById( 'upload-label' );

input.addEventListener( 'change', showFileName );
function showFileName( event ) {
  var input = event.srcElement;
  var fileName = input.files[0].name;
  infoArea.textContent = 'File name: ' + fileName;
}
</script>