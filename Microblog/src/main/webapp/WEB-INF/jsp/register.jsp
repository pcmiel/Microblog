<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
		<div class="text-info">
			<h1>Join to us</h1>
	</div>
	<div class="pull-left">
		<form:form method='POST' action="register" commandName="user">

			<div class="clearfix">
				<form:input path="username" id="inputInfo" placeholder="Username" />
				<span class="help-inline"><form:errors class="alert-error"
						path="username" /></span>
			</div>
			<div class="clearfix">
				<form:input path="password" type="password" id="inputInfo"
					placeholder="Password" />
				<span class="help-inline"><form:errors class="alert-error"
						path="password" /></span>
			</div>

			<div class="clearfix">
				<form:input path="confirmPassword" type="password" id="inputInfo"
					placeholder="Confirm Password" />
				<span class="help-inline"><form:errors class="alert-error"
						path="confirmPassword" /></span>
			</div>
			<input type="submit" class="btn btn-large btn-primary" value="Register" />
		</form:form>
	</div>

</body>
</html>