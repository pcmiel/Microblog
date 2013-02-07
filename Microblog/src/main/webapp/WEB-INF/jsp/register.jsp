<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form class="form-horizontal" id="registerHere" method='post' action="<c:url value='j_spring_security_check' />">
		<fieldset>

			<legend>Registration</legend>

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
				<label class="control-label">Confirm Password</label>
				<div class="controls">
					<input type="password" class="input-xlarge" id="confirm_password"
						name="confirm_password" rel="popover"
						data-content="Enter your password."
						data-original-title="Password">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"></label>
				<div class="controls">
					<button type="submit" class="btn btn-success" name="submit">Create
						Account</button>
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
</body>
</html>