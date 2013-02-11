<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<a class="btn btn-navbar" data-toggle="collapse"
	data-target=".nav-collapse"> <span class="icon-bar"></span> <span
	class="icon-bar"></span> <span class="icon-bar"></span>
</a>
<a class="brand" href="#">Microblog</a>
<!-- Start of the nav bar content -->
<div class="nav-collapse">
	<ul class="nav pull-right">
		<li><a href="register">Sign Up</a></li>
		<li><a href="login"><sec:authorize ifAllGranted="ROLE_USER">Welcome <%=request.getUserPrincipal().getName()%></sec:authorize></a></li>
		<li class="divider-vertical"></li>
		
	</ul>
</div>