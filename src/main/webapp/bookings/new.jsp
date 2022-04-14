<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="New Booking"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="${title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
		<jsp:include page="/layouts/message.jsp"></jsp:include>
		<jsp:include page="/layouts/navbar.jsp"></jsp:include>
		<div class="content w-5 mx-auto">
			<jsp:include page="/bookings/_search_service.jsp">
				<jsp:param name="depLocation" value="${depLocation}"/>
				<jsp:param name="arrLocation" value="${arrLocation}"/>
				<jsp:param name="date" value="${date}"/>
				<jsp:param name="locations" value="${locations}"/>
			</jsp:include>
		</div>
	</div>
</body>
</html>