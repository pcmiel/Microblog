<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
</style>
</head>
<body>

	<h2>Join to us</h2>
	<form:form method='POST' action="register" commandName="user">

		<div class="clearfix">
			<form:input path="username" id="inputInfo" placeholder="Username" />
			<span class="help-inline"><form:errors path="username" /></span>
		</div>

		<div class="clearfix">
			<form:input path="password" id="inputInfo" placeholder="Password" />
			<span class="help-inline"><form:errors path="password" /></span>
		</div>

		<div class="clearfix">
			<form:input path="confirmPassword" id="inputInfo"
				placeholder="Confirm Password" />
			<span class="help-inline"><form:errors path="confirmPassword" /></span>
		</div>
		<input type="submit" class="btn btn-primary" value="Register" />
	</form:form>


</body>
</html>