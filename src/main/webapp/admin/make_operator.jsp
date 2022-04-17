<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="Make as Operator"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="${title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
	<jsp:include page="/layouts/message.jsp"></jsp:include>
	<jsp:include page="/layouts/navbar.jsp"></jsp:include>	
	<h3 class="text-center text-primary">Make as Operator</h3>
		<div class="content w-50 mx-auto">
			<form action="/admin/${id}/make_as_operator" method="post">
					<div class="mb-3">
					    <label for="operatorName" class="form-label">Operator Name</label>
					    <input type="text" class="form-control" id="operatorName" name="operatorName">
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>  
			</form>
		</div>
	</div>
</body>
</html>