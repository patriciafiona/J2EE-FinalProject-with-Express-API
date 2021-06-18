<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="row">
	<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
	  <div class="carousel-indicators">
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
	  </div>
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src='<c:url value="/assets/img/home_banner_01.png"/>' class="d-block w-100" alt="...">
	    </div>
	    <div class="carousel-item">
	      <img src='<c:url value="/assets/img/home_banner_02.png"/>' class="d-block w-100" alt="...">
	    </div>
	  </div>
	  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Previous</span>
	  </button>
	  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Next</span>
	  </button>
	</div>
	
	<div class="container my-5 center">
		<h3 class="text-center">NEW ARRIVALS</h3>
		<h1 class="text-center mt-2 mb-4"><b>BE THE FIRST TO GET THE BEST.</b></h1>
		<div class="row justify-content-md-center">
		    <div class="col col-lg-2">
		      	<a href="${context}/CustomerProductByCategory?category=men" class="btn btn-outline-black">SHOP MEN</a>
		    </div>
		    <div class="col-md-auto">
		      	<a href="${context}/CustomerProductByCategory?category=women" class="btn btn-outline-black">SHOP WOMEN</a>
		    </div>
		    <div class="col col-lg-2">
		      	<a href="${context}/CustomerProductByCategory?category=kids" class="btn btn-outline-black">SHOP KIDS</a>
		    </div>
		</div>
		<div class="row">
			<img src='<c:url value="/assets/img/end_of_season_banner.png"/>' class="img-fluid text-center mt-5 mb-3 px-5" alt="...">
		</div>
		<div class="row justify-content-md-center">
			<div class="col col-lg-2">
		      	<a href="#" class="btn btn-outline-black">SHOP NOW</a>
		    </div>
		</div>
	</div>
	
	<div class="row my-0 p-0 ">
		<div class="col-md-6 p-0">
			<a href="#" class="nounderline black">
				<div class="linkHover">
					<div class="containLinks">
						<img src='<c:url value="/assets/img/home_category_01.png"/>' class="img-fluid text-center" alt="...">
					</div>
					<div class="overlay">
				    	<div class="text"><b>See Now</b></div>
				  	</div>
				</div>
				<h4 class="text-center my-3"><b>LIGHT + AIRY</b></h4>
				<p class="text-center">UA HOVR SONIC 4</p>
			</a>
		</div>
		<div class="col-md-6 p-0">
			<a href="#" class="nounderline black">
				<div class="linkHover">
					<div class="containLinks">
						<img src='<c:url value="/assets/img/home_category_02.png"/>' class="img-fluid text-center" alt="...">
					</div>
					<div class="overlay">
				    	<div class="text"><b>See Now</b></div>
				  	</div>
				</div>
				
				<h4 class="text-center my-3"><b>25 YEARS OF INNOVATION</b></h4>
				<p class="text-center">ANNIVERSARY COLLECTION</p>
			</a>
		</div>
	</div>
	<div class="row my-0 p-0 mb-5">
		<div class="col-md-4 p-0">
			<a href="#" class="nounderline black">
				<div class="linkHover">
					<div class="containLinks">
						<img src='<c:url value="/assets/img/home_category_03.png"/>' class="img-fluid text-center" alt="...">
					</div>
					<div class="overlay">
				    	<div class="text"><b>See Now</b></div>
				  	</div>
				</div>
				<h4 class="text-center my-3"><b>PROJECT ROCK COLLECTION</b></h4>
			</a>
		</div>
		<div class="col-md-4 p-0">
			<a href="#" class="nounderline black">
				<div class="linkHover">
					<div class="containLinks">
						<img src='<c:url value="/assets/img/home_category_04.png"/>' class="img-fluid text-center" alt="...">
					</div>
					<div class="overlay">
				    	<div class="text"><b>See Now</b></div>
				  	</div>
				</div>
				<h4 class="text-center my-3"><b>CURRY FLOW 8</b></h4>
			</a>
		</div>
		<div class="col-md-4 p-0">
			<a href="#" class="nounderline black">
				<div class="linkHover">
					<div class="containLinks">
						<img src='<c:url value="/assets/img/home_category_05.png"/>' class="img-fluid text-center" alt="...">
					</div>
					<div class="overlay">
				    	<div class="text"><b>See Now</b></div>
				  	</div>
				</div>
				<h4 class="text-center my-3"><b>UA COOLSWITCH</b></h4>
			</a>
		</div>
	</div>
</div>