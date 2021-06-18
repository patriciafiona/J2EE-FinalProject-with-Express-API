<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<%
	String uri = request.getRequestURI().toString();
	
	if(uri.indexOf("status/edit") > 0 ){
		pageContext.setAttribute("statusEditPage", "EmployeeStatusEdit");
	}
	
	if(uri.indexOf("category/edit") > 0 ){
		pageContext.setAttribute("categoryEditPage", "EmployeeCategoryEdit");
	}
	
	if(uri.indexOf("tag/edit") > 0 ){
		pageContext.setAttribute("tagsEditPage", "EmployeeTagsEdit");
	}
	
	if(uri.indexOf("user/edit") > 0 ){
		pageContext.setAttribute("usersEditPage", "EmployeeUsersEdit");
	}
	
	if(uri.indexOf("product/edit") > 0 ){
		pageContext.setAttribute("productsEditPage", "EmployeeProductsEdit");
	}
%>

<div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark-grey sticky-top" style="width: 280px;">
   <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
     <span class="fs-4">Under Armour</span>
   </a>
   <hr>
   <ul class="nav nav-pills flex-column mb-auto">
     <li class="nav-item">
       <a href="${context}/EmployeeHome" class="nav-link text-white ${pagina.endsWith('/EmployeeHome') ? 'active' : ''} " aria-current="page">
         <i class="fa fa-home" aria-hidden="true"></i>
         Home
       </a>
     </li>
     <li>
       <a href="${context}/EmployeeCategories" class="nav-link text-white 
       		${pagina.endsWith('/EmployeeCategories') ? 'active' : ''} 
       		${categoryEditPage eq 'EmployeeCategoryEdit' ? 'active' : ''}  ">
         <i class="fa fa-folder-open" aria-hidden="true"></i>
         Categories
       </a>
     </li>
     <li>
       <a href="${context}/EmployeeTags" class="nav-link text-white 
       		${pagina.endsWith('/EmployeeTags') ? 'active' : ''} 
       		${tagsEditPage eq 'EmployeeTagsEdit' ? 'active' : ''} ">
         <i class="fa fa-tags" aria-hidden="true"></i>
         Tags
       </a>
     </li>
     <li>
       <a href="${context}/EmployeeOrders" class="nav-link text-white 
       		${pagina.endsWith('/EmployeeOrders') ? 'active' : ''} ">
         <i class="fa fa-bar-chart" aria-hidden="true"></i>
         Orders
       </a>
     </li>
     <li>
       <a href="${context}/EmployeeProducts" class="nav-link text-white 
       		${pagina.endsWith('/EmployeeProducts') ? 'active' : ''} 
       		${productsEditPage eq 'EmployeeProductsEdit' ? 'active' : ''} ">
         <i class="fa fa-shopping-bag" aria-hidden="true"></i>
         Products
       </a>
     </li>
     <li>
       <a href="${context}/EmployeeUsers" class="nav-link text-white 
       		${pagina.endsWith('/EmployeeUsers') ? 'active' : ''} 
       		${usersEditPage eq 'EmployeeUsersEdit' ? 'active' : ''} ">
         <i class="fa fa-users" aria-hidden="true"></i>
         Users
       </a>
     </li>
     <li>
       <a href="${context}/EmployeeStatus" class="nav-link text-white 
       		${pagina.endsWith('/EmployeeStatus') ? 'active' : ''}
       		${statusEditPage eq 'EmployeeStatusEdit' ? 'active' : ''} ">
        <i class="fa fa-id-badge" aria-hidden="true"></i>
         Status
       </a>
     </li>
     <div class="dropdown-divider"></div>
     <li>
       <a href="${context}/Logout" class="nav-link text-white">
         <i class="fa fa-sign-out" aria-hidden="true"></i>
         Logout
       </a>
     </li>
   </ul>
 </div>