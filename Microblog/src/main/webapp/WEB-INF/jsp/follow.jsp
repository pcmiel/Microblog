<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Follow</title>

</head>
<body>
	

	<h1>Currently Following</h1>
        <table id="following">
        	<thead>
        		<tr>
        			<th colspan="2">Username</th>
        		</tr>
        	</thead>
			<tbody>
				<c:forEach var="user" items="${following}">
				<tr>
					<td><c:out value="${user.username}"/></td>
					<td>
				        <form method="GET" action="unfollow">
				        	<input type="hidden" id="usertest" name="username" value="<c:out value="${user.username}"/>"/>
				        	<input type="submit" value="Unfollow"/>
				        </form>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<h1>Not Following</h1>
        <table id="following">
        	<thead>
        		<tr>
        			<th colspan="2">Username</th>
        		</tr>
        	</thead>
			<tbody>
				<c:forEach var="user" items="${unfollowing}">
				<tr>
					<td><c:out value="${user.username}"/></td>
					<td>
				        <form method="GET" action="followNew">
				        	<input type="hidden" id="usertest" name="username" value="<c:out value="${user.username}"/>"/>
				        	<input type="submit" value="Follow"/>
				        </form>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>