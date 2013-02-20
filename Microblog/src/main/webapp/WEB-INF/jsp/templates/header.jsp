<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<a class="brand" href="#">Microblog</a>
<div class="nav-collapse">
	<ul class="nav pull-right">
		<li><a href="#"><sec:authorize ifAllGranted="ROLE_USER">
					<i class="icon-user icon-white"></i>
					<strong><%=request.getUserPrincipal().getName()%></strong>
				</sec:authorize> </a></li>
		<li><a href="login">Login</a></li>
		<li><a href="register">Sign Up</a></li>
		<li class="divider-vertical"></li>
	</ul>
</div>