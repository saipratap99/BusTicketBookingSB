<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="Logout"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="${title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
		<jsp:include page="/layouts/message.jsp"></jsp:include>
		<jsp:include page="/layouts/navbar.jsp"></jsp:include>
		<div class="text-center">
			<h3>Removing ${name} as operator. Are you sure?</h3> 
			<form method="post" action="/admin/${id}/remove_as_operator"  class="mt-3">
			 	<button type="submit" class="btn btn-danger">Confirm</button>
			 	<a class="btn btn-primary" href="/admin/users">Go Back</a>
			</form>
			
		</div>
		
	</div>
</body>
</html>