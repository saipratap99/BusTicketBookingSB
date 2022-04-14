<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="New Schedule"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="${title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
		<jsp:include page="/layouts/message.jsp"></jsp:include>
		<jsp:include page="/layouts/navbar.jsp"></jsp:include>
		<div class="content">
			<div class="serviceDetailsForm w-50 mx-auto">
				<form action="/schedule/create" method="post">
                    <fieldset>
                        <legend class="text-center text-primary">Schedule</legend>
                        
                        <div class="mb-3">
                            <label for="serviceDetails" class="form-label">Service Details</label>
                            <input list="serviceDetails" value="" class="col-sm-6 custom-select custom-select-sm form-control" name="service" placeholder="Select Service">
                            <jsp:include page="/service_details/list.jsp">
                                <jsp:param name="services" value="${services}"/>
                            </jsp:include>
                       </div>

                        <div class="mb-3">
                            <label for="busDetails" class="form-label">Bus Details</label>
                            <input list="busDetails" value="" class="col-sm-6 custom-select custom-select-sm form-control" name="bus" placeholder="Select bus">
                            <jsp:include page="/bus_details/list.jsp">
                                <jsp:param name="buses" value="${buses}"/>
                            </jsp:include>
                       </div>
                            
                       <!--  
                        <div class="grid-row">
                             <div class="mb-3 grid-col date">
                                <label for="departureDate" class="form-label">Departure Date</label>
                                <input type="date" class="form-control" id="departureDate" name="departureDate">
                            </div>
                            <div class="mb-3 grid-col date">
                                <label for="arrivalDate" class="form-label">Departure Date</label>
                                <input type="date" class="form-control" id="arrivalDate" name="arrivalDate">
                            </div>                            
                        </div>
                        
                        -->
                        
                        <div class="mb-3">
                            <label for="week" class="form-label">Week Day</label>
                            <input list="weeks" value="" class="col-sm-6 custom-select custom-select-sm form-control" name="week" placeholder="Select week">
                            <datalist id="weeks">
								<option value="1">Sunday</option>
								<option value="2">Monday</option>
								<option value="3">Tuesday</option>
								<option value="4">Wednesday</option>
								<option value="5">Thursday</option>
								<option value="6">Friday</option>
								<option value="7">Saturday</option>
							</datalist>
                        </div>
                       
                        <div class="grid-row">
                            <div class="mb-3 grid-col">
                                <label for="departureTime" class="form-label">Departure Time</label>
                                <input type="time" class="form-control" id="departureTime" name="depTime">
                            </div>    
                        
                            <div class="mb-3 grid-col">
                                <label for="tripDuration" class="form-label">Duration</label>
                                <input type="time" class="form-control" id="tripDuration" name="tripDuration">
                            </div>          
                        </div>
                        
                        <div class="mb-3">
                                <label for="basePrice" class="form-label">Base price</label>
                                <input type="number" class="form-control" id="basePrice" name="basePrice">
                         </div>
                        
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </fieldset>
                </form>
			</div>
		</div>
	</div>
</body>
</html>