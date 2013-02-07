<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SiteMesh example: <sitemesh:write property='title' /></title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css" />
<sitemesh:write property='head' />
</head>
<body>
	<div class="navbar navbar-inverse navbar-static-top">
		<div class="navbar-inner">
			<div class="container-fluid">

				<c:import url="/WEB-INF/jsp/header.jsp" />
			</div>
		</div>
	</div>
	<div class="container-fluid">

		<div class="span2">
			<div class="well sidebar-nav">
				<c:import url="/WEB-INF/jsp/menu.jsp" />
			</div>
			<!--/.well -->
		</div>
		<!--/span-->

		<div class='mainBody'>
			<sitemesh:write property='body' />
		</div>

		<div class='disclaimer'>Site disclaimer. This is an example.</div>

		<footer> <c:import url="/WEB-INF/jsp/footer.jsp" /> </footer>
	</div>
</body>
</html>
