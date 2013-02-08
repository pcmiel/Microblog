<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form  method='POST' action="register" commandName="user">
	<table>
				<tr>
					<td><form:label path="username">Title</form:label></td>
					<td><form:input path="username" /></td>
				</tr>
				<tr>
					<td><form:label path="password">News</form:label></td>
					<td><form:input type="passwod" path="password" /></td>
				</tr>
				<tr>
					<td><form:label path="confirmPassword">News</form:label></td>
					<td><form:input type="passwod" path="confirmPassword" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Add Message" /></td>
				</tr>
			</table>
			<form:errors path="*"/>
	</form:form>
</body>
</html>