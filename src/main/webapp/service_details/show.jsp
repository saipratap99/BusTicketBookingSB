<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="Show Service Details"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="${title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
		<jsp:include page="/layouts/message.jsp"></jsp:include>
		<jsp:include page="/layouts/navbar.jsp"></jsp:include>
		<div class="content">
			<div class="serviceDetailsForm w-50 mx-auto">
				    <fieldset>
                        <legend class="text-center text-primary">Service Details</legend>
                        <div class="grid-row">
                            <div class="mb-3 grid-col">
                                <label for="serviceNumber" class="form-label">Service Number</label>
                                <input type="text" class="form-control" id="serviceNumber" name="serviceNumber" value="${serviceDetails.getServiceNumber()}" disabled="disabled">
                            </div>
                            <div class="mb-3 grid-col">
                                <label for="serviceType" class="form-label">Service Type</label>
                                <input type="text" class="form-control" id="serviceType" name="serviceType" value="${serviceDetails.getServiceType()}" disabled="disabled">
                            </div>
                        </div>
                        <div class="grid-row departure">      
                            <div class="mb-3 grid-col location">
                                <label for="departureLocation" class="form-label">Departure Location</label>
                                <input list="locations" class="col-sm-6 custom-select custom-select-sm form-control" name="depLocation" placeholder="Select location" value="${serviceDetails.getDepartureLocation().getLocationName()}" disabled="disabled">
                            </div>
                            
                                            
                        </div>
                        <div class="grid-row arrival">
                            
                            <!-- 
                            <div class="mb-3 grid-col date">
                                <label for="arrivalDate" class="form-label">Arrival Date</label>
                                <input type="date" class="form-control" id="arrivalDate" name="arrivalDate">
                            </div>
                            
                            <div class="mb-3 grid-col time">
                                <label for="arrivalTime" class="form-label">Arrival Time</label>
                                <input type="time" class="form-control" id="arrivalTime" name="arrTime">
                            </div>
                            
                            -->
                            <div class="mb-3 grid-col location">
                                <label for="arrivalLocation" class="form-label">Arrival Location</label>
                                <input list="locations" class="col-sm-6 custom-select custom-select-sm form-control" name="arrLocation" placeholder="Select location" value="${serviceDetails.getArrivalLocation().getLocationName()}" disabled="disabled">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="distance" class="form-label">Distance</label>
                            <input type="number" class="form-control" id="distance" name="distance" value="${serviceDetails.getDistance()}" disabled="disabled">
                        </div>			  
                    </fieldset>
                
			</div>
		</div>
	</div>
</body>
</html>