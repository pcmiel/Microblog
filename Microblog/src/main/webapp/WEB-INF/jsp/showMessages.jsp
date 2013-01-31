
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>test</title>

</head>
<body>
	<h1>Messages</h1>

	<table>
		<tr>
			<td width="50">Id</td>
			<td width="150">Title</td>
			<td width="150">News</td>
			<td width="50">AuthorId</td>
		</tr>
		<c:forEach items="${message}" var="message">
			<tr>
				<td><c:out value="${message.id}" /></td>
				<td><c:out value="${message.title}" /></td>
				<td><c:out value="${message.news}" /></td>
				<td><c:out value="${message.authorId}" /></td>
			</tr>
		</c:forEach>
	</table>
	<a href="addmessage.html"><button class="btn btn-primary">Add
			New</button></a>


</body>
</html>