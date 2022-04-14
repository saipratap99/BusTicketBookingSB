<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
    
<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="Buses"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="${title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
		<jsp:include page="/layouts/message.jsp"></jsp:include>
		<jsp:include page="/layouts/navbar.jsp"></jsp:include>
		<div class="content w-80 mx-auto">
			<jsp:include page="/bookings/_search_service.jsp">
				<jsp:param name="depLocation" value="${depLocation}"/>
				<jsp:param name="arrLocation" value="${arrLocation}"/>
				<jsp:param name="date" value="${date}"/>
			</jsp:include>
			<div class="result mt-4">
				<c:forEach items="${schedules}" var="schedule">
					<div class="bus ">
						<h4><strong>${schedule.getBusDetails().getBusName()}</strong></h4>
						<div class="d-flex justify-content-between">
							<div class="col">
								<strong>Departure time</strong>: ${schedule.getDepartureTime()}
							</div>			
							<div class="col">
								<strong>Duaration</strong>: ${Math.floor(schedule.getDuration()/60)} hrs ${schedule.getDuration()%60} mns
							</div>			
							<div class="col">
								<strong>Arrival time</strong>: 
							</div>						
							<div>
								<strong> Price: </strong> ${schedule.getBasePrice()} 
							</div>
							<div>
								<a class="btn btn-primary">Select Seats</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>