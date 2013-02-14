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

<style type="text/css">
/* Override some defaults */
.avatar {
	width: 48px;
	height: 48px;
	border: 5px;
}

html,body {
	background-color: #eee;
}

.container {
	padding-top: 40px;
	width: 600px;
}

/* The white background content wrapper */
.container>.content {
	background-color: #fff;
	padding: 20px;
	margin: 0 -20px;
	-webkit-border-radius: 10px 10px 10px 10px;
	-moz-border-radius: 10px 10px 10px 10px;
	border-radius: 10px 10px 10px 10px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
}

.login-form {
	margin-left: 65px;
}

legend {
	margin-right: -50px;
	font-weight: bold;
	color: #404040;
}
</style>
</head>
<body>
	<div class="navbar navbar-inverse navbar-static-top">
		<div class="navbar-inner">
			<div class="container-fluid">

				<c:import url="/WEB-INF/jsp/templates/header.jsp" />
			</div>
		</div>
	</div>
	<div class="container-fluid">

		<!--/span-->

		<div class='mainBody'>
			<div class="container">
				<div class="content">
					<div class="row">
						<div class="login-form">
							<sitemesh:write property='body' />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="navbar navbar-fixed-bottom">
			<footer> <c:import url="/WEB-INF/jsp/templates/footer.jsp" />
			</footer>
		</div>
	</div>
</body>
</html>
