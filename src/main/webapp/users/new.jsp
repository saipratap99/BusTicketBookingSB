<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="New User"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="#{title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
		<jsp:include page="/layouts/message.jsp"></jsp:include>
		<jsp:include page="/layouts/navbar.jsp"></jsp:include>
		<div class="content">
			<div class="userForm w-50 mx-auto">
				<form action="/users/create" method="post">
				<fieldset>
					<div>
						<legend class="text-center text-primary">Sign Up</legend>
						<p class="text-center">
						   Existing User? <a href="/users/login">Login Here</a>
						</p>
					</div>
					<div class="mb-3">
					    <label for="firstName" class="form-label">First Name</label>
					    <input type="text" class="form-control" id="firstName" name="firstName">
					  </div>
					  <div class="mb-3">
					    <label for="lastName" class="form-label">Last Name</label>
					    <input type="text" class="form-control" id="lastName" name="lastName">
					  </div>
					  <div class="mb-3">
					    <label for="email" class="form-label">Email address</label>
					    <input type="email" class="form-control" id="email" name="email">
					  </div>
					  <div class="mb-3">
					    <label for="password" class="form-label">Password</label>
					    <input type="password" class="form-control" name="password" id="password">
					  </div>
					  <div class="mb-3">
					    <label for="confirm_password" class="form-label">Confirm Password</label>
					    <input type="password" class="form-control" name="confirmPassword" id="confirm_password">
					  </div>
					  <button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</fieldset>
				  
			</div>
		</div>
	</div>
</body>
</html>