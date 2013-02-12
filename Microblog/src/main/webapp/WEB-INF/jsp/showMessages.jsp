<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

</head>
<body>
	<h1>All posts</h1>

	<table>
		<tr>
			<td width="150">Author</td>
			<td width="150">Post</td>
			<td width="150">Date</td>
		</tr>
		<c:forEach items="${posts}" var="post">
			<tr>
				<td><c:out value="${post.user.username}" /></td>
				<td><c:out value="${post.news}" /></td>
				<td><fmt:formatDate type="both" value="${post.date}" /></td>
			</tr>
		</c:forEach>
	</table>
	<a href="addmessage.html"><button class="btn btn-primary">Add
			New</button></a>


</body>
</html>