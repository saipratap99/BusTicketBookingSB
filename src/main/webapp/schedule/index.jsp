<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="Schedules"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="${title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
	<jsp:include page="/layouts/message.jsp"></jsp:include>
	<jsp:include page="/layouts/navbar.jsp"></jsp:include>	
	<h3 class="text-center text-primary">Schedules</h3>
		<div class="content">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">Id</th>
			      <th scope="col">Bus Id</th>
			      <th scope="col">Service Id</th>
			      <th scope="col">Week Day</th>
			      <th scope="col">Departure Time</th>
			      <th scope="col">Duration</th>
			      <th scope="col">Base Price</th>
			      <th scope="col">Update</th>
			      <th scope="col">Delete</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach items="${schedules}" var="schedule">
				    <tr>
			      	  <th scope="row"><a class="link-dark" href="/schedule/${schedule.getId()}">${schedule.getId()}</a></th>
				      <td><a class="link-dark" href="/bus_details/${schedule.getBusDetails().getId()}">${schedule.getBusDetails().getId()}</a></td>
				      <td><a class="link-dark" href="/service_details/${schedule.getServiceDetails().getId()}">${schedule.getServiceDetails().getId()}</td>
				      <td>
					      <c:choose>
					      	<c:when test="${schedule.getWeekDay() == 1}">
					      		Sunday
					      	</c:when>
					      	<c:when test="${schedule.getWeekDay() == 2}">
					      		Monday
					      	</c:when>
					      	<c:when test="${schedule.getWeekDay() == 3}">
					      		Tuesday
					      	</c:when>
					      	<c:when test="${schedule.getWeekDay() == 4}">
					      		Wednesday
					      	</c:when>
					      	<c:when test="${schedule.getWeekDay() == 5}">
					      		Thursday
					      	</c:when>
					      	<c:when test="${schedule.getWeekDay() == 6}">
					      		Friday
					      	</c:when>
					      	<c:when test="${schedule.getWeekDay() == 7}">
					      		Saturday
					      	</c:when>
					      </c:choose>
				      </td>
				      <td>${schedule.getDepartureTime()}</td>
				      <td>
				      	<fmt:formatNumber value="${Math.floor(schedule.getDuration()/60)}" minFractionDigits="0" maxFractionDigits="0"/> hrs
				      	${(schedule.getDuration() % 60)} mns 
				      </td>
				      <td>${schedule.getBasePrice()}</td>
				      <td>
				      	<a class="btn btn-warning text-light" href="/schedule/${schedule.getId()}/edit">Update</a>
				      </td>
				      <td>
				      	<form action="/schedule/${schedule.getId()}" method="DELETE">
				      		<button type="submit" class="btn btn-danger text-light">Delete</a>
				      	</form>
				      </td>		      
				    </tr>
			    </c:forEach>
			  </tbody>
			</table>
			<a class="btn btn-primary" href="/schedule/new">New Schedule</a>
		</div>
		
	</div>
	
</body>
</html>