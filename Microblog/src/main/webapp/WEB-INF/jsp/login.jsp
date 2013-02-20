<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload='document.f.j_username.focus();'>
	<div class="text-info">
		<h1>Login</h1>
	</div>
	<form id="loginHere" method='POST' action="<c:url value='j_spring_security_check' />">
		<div class="clearfix">
			<input type="text" id="inputInfo" name="j_username"
				placeholder="Username"> <span class="help-inline"> <c:if
					test="${not empty error}">
					<div class="alert-error">Your login attempt was not
						successful, try again.</div>
				</c:if>
			</span>
		</div>
		<div class="clearfix">
			<input type="password" id="inputInfo" name="j_password"
				placeholder="Password">
		</div>
		<input type="submit" class="btn btn-large btn-primary" value="Login" />
		<span>or</span> <a href="register"><button class="btn btn-link"
				type="button">Create new account</button></a>
	</form>
	<form action="addtest" method='POST'>
		<input name="submit" class="btn btn-warning" type="submit"
			value="Add test data" />
	</form>
</body>
</html>