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
	<div>
		<div class="pull-left">
			<div class="text-info">
				<h1>Add news</h1>
			</div>
		</div>
		<form:form method="POST" action="newMessage" commandName="post">

			<div>
				<form:textarea path="news" class="span12" rows="4" />
			</div>			
			<div class="pull-left">
				<input type="submit" class="btn btn-large btn-success" value="Add" />
			</div>
			<div class="pull-right">
				<form:errors path="news" class="alert-error" />
			</div>
		</form:form>
		
	</div>
</body>
</html>