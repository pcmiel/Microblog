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
	<div class="text-info">
		<h1>Follow</h1>
	</div>
	<div class="span5 pull-left ">
		<table class="table table-hover table-condensed" id="following">
			<thead>
				<div class="text-success">
					<h4>Following</h4>
				</div>
			</thead>
			<tbody>
				<c:forEach var="user" items="${following}">
					<tr>
						<td>
							<div class="pull-left">
								<strong><c:out value="${user.username}" /></strong>
							</div>

							<div class="pull-right">
								<form method="GET" action="unfollow">
									<input type="hidden" id="usertest" name="username"
										value="<c:out value="${user.username}"/>" />
									<button type="submit" class="btn btn-danger">
										<i class="icon-remove"></i>
									</button>

								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="span5 pull-right">
		<table class="table table-hover table-condensed" id="following">
			<thead>
				<div class="text-success">
					<h4>Not following</h4>
				</div>
			</thead>
			<tbody>
				<c:forEach var="user" items="${unfollowing}">
					<tr>
						<td><div class="pull-left">
								<strong><c:out value="${user.username}" /></strong>
							</div>
							<div class="pull-right">
								<form method="GET" action="followNew">
									<input type="hidden" id="usertest" name="username"
										value="<c:out value="${user.username}"/>" />
									<button type="submit" class="btn btn-success">
										<i class="icon-ok"></i>
									</button>
								</form>
							</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>