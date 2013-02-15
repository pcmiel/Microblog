<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<a class="btn btn-navbar" data-toggle="collapse"
	data-target=".nav-collapse"> <span class="icon-bar"></span> <span
	class="icon-bar"></span> <span class="icon-bar"></span>
</a>
<a class="brand" href="#">Microblog</a>
<!-- Start of the nav bar content -->
<div class="nav-collapse">
	<ul class="nav pull-right">
		<li><a href="#"><sec:authorize ifAllGranted="ROLE_USER">
					<i class="icon-user icon-white"></i>
					<strong><%=request.getUserPrincipal().getName()%></strong>
				</sec:authorize></a></li>
		</a>
		<li><a href="../logout">Log out</a></li>
		<li class="divider-vertical"></li>
	</ul>
</div>