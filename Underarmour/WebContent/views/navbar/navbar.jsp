<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-black">
  <div class="container-fluid">
    <a class="navbar-brand" href="${context }/CustomerHome">
    	<img src='<c:url value="/assets/img/underarmour_logo_white.png"/>' class="ml-3 z-depth-2" height="30" alt="">
    	<span>Under Armor</span>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      	<li class="nav-item active">
	        <a class="nav-link" href="${context }/CustomerHome" >Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item dropdown">
	        <a id="dropdown-toggle-men" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          Men
	        </a>
	        <div id="dropdown-menu-men" class="dropdown-menu" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item" href="${context}/CustomerTops?category=Men&tag=Tops">Tops</a>
	          <a class="dropdown-item" href="${context}/CustomerBottoms?category=Men&tag=Bottoms">Bottoms</a>
	          <a class="dropdown-item" href="${context}/CustomerShoes?category=Men&tag=Shoes">Shoes</a>
	        </div>
	      </li>
	      <li class="nav-item dropdown">
	        <a id="dropdown-toggle-women" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          Women
	        </a>
	        <div id="dropdown-menu-women" class="dropdown-menu" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item" href="${context}/CustomerTops?category=Women&tag=Tops">Tops</a>
	          <a class="dropdown-item" href="${context}/CustomerBottoms?category=Women&tag=Bottoms">Bottoms</a>
	          <a class="dropdown-item" href="${context}/CustomerShoes?category=Women&tag=Shoes">Shoes</a>
	        </div>
	      </li>
	      <li class="nav-item dropdown">
	        <a id="dropdown-toggle-kids" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          Kids
	        </a>
	        <div id="dropdown-menu-kids" class="dropdown-menu" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item" href="${context}/CustomerTops?category=Kids&tag=Tops">Tops</a>
	          <a class="dropdown-item" href="${context}/CustomerBottoms?category=Kids&tag=Bottoms">Bottoms</a>
	          <a class="dropdown-item" href="${context}/CustomerShoes?category=Kids&tag=Shoes">Shoes</a>
	        </div>
	      </li>
      </ul>
      <div class="d-flex">
      	<a href="${context}/CustomerShoppingCart" class="mx-3 text-white">
      		<i class="fa fa-lg fa-shopping-bag" aria-hidden="true"></i>
      	</a>
        <img src='<c:url value="/assets/img/id.png"/>' class="z-depth-2" height="20" alt="">
        <c:choose>
			<c:when test="${empty email}">
        		<a href="${context}/CustomerLogin" class="nounderline white mx-3">Log in</a>
        	</c:when>
        	<c:otherwise>
        		<a href="${context}/Logout" class="nounderline white mx-3">Logout</a>
        		<a href="${context}/CustomerProfile" class="nounderline white mx-3"><i class="fa fa-user-circle" aria-hidden="true"></i></a>
        	</c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>
</nav>