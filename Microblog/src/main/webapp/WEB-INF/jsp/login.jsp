<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body
	onload='document.f.j_userna<h3>Login with Username and Password (Custom Page)</h3>
 
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused : Wrong values
		</div>
	</c:if>
 me.focus();'>

	<form class="form-horizontal " id="registerHere" method='post' action="<c:url value='j_spring_security_check' />">
		<fieldset>
			<legend>Login</legend>
			<div class="control-group">
				<label class="control-label">Name</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="user_name"
						name="j_username" rel="popover"
						data-content="Enter your first login."
						data-original-title="Full Name">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Password</label>
				<div class="controls">
					<input type="password" class="input-xlarge" id="user_password"
						name="j_password" rel="popover"
						data-content="Enter your password."
						data-original-title="Password">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"></label>
				<div class="controls">
					<button type="submit" class="btn btn-success" name="submit">Login</button>
				</div>
			</div>
			
		<div class="control-group">
				<label class="control-label"></label>
				<div class="controls">
					<button type="reset" class="btn btn-success" name="reset">Reset</button>
				</div>
			</div>

		</fieldset>
	</form>

	<sec:authorize ifAllGranted="ROLE_USER">
   		Welcome <%=request.getUserPrincipal().getName()%>
		<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
		<br />
	</sec:authorize>
	<form action="addTest" method='POST'>
		<input name="submit" type="submit" value="Add test user" />
	</form>
</body>
</html>