<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

<style type="text/css">
/* Override some defaults */
.pre {
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.avatar {
	width: 48px;
	height: 48px;
}
</style>
</head>
<body>
	<div>
		<div class="pull-left">
			<div class="text-info">
				<h1>News</h1>
			</div>
		</div>
		<div class="pull-right">
			<a href="newMessage"><button class="btn btn-primary">Add
					New</button></a>
		</div>
		<table class="table table-hover">
			<tbody>
				<c:forEach items="${posts}" var="post">
					<tr>
						<td class="span1"><div class="avatar">
								<img src="/Microblog/resources/img/newpost.png"
									class="img-rounded">
							</div></td>
						<td class="span8">

							<div class="content">
								<div class="clearfix">
									<div class="pull-left text-success">
										<strong> <c:out value="${post.user.username}" />
										</strong>
									</div>
									<div class="pull-right muted">
										<fmt:formatDate type="both" value="${post.date}" />
									</div>
								</div>

								<div class="">
									<span class=""><pre class="pre"><c:out value="${post.news}" /></pre></span>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>