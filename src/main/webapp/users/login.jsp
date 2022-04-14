<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="User Login"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="${title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
		<jsp:include page="/layouts/message.jsp"></jsp:include>
		<jsp:include page="/layouts/navbar.jsp"></jsp:include> 
		<div class="content">
			<div class="userForm w-50 mx-auto">
				<form action="/users/login" method="post">
				<fieldset>
					<div>
						<legend class="text-center text-primary">Login</legend>
						<p class="text-center">
						   New User? <a href="/users/new">SignUp Here</a>
						</p>
					</div>
					  <div class="mb-3">
					    <label for="email" class="form-label">Email address</label>
					    <input type="email" class="form-control" id="email" name="email">
					  </div>
					  <div class="mb-3">
					    <label for="password" class="form-label">Password</label>
					    <input type="password" class="form-control" name="password" id="password">
					  </div>
				   <button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</fieldset>
				  
			</div>
		</div>
	</div>
</body>
</html>