<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Microblog</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css" />
<sitemesh:write property='head' />
</head>
<body class="decorator">
	<div class="navbar navbar-inverse navbar-static-top">
		<div class="navbar-inner">
			<div class="container-fluid">

				<c:import url="/WEB-INF/jsp/templates/appHeader.jsp" />
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class='mainBody'>
			<div class="container">
				<div class="margin">
					<div class=".dropdown-menu">
						<div class="well sidebar-nav">
							<c:import url="/WEB-INF/jsp/templates/menu.jsp" />
						</div>
					</div>
				</div>

				<div class="content">
					<div class="row-fluid">
						<sitemesh:write property='body' />
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
