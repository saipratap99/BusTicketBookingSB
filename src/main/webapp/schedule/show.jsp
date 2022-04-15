<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="Show Schedule"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="${title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
		<jsp:include page="/layouts/message.jsp"></jsp:include>
		<jsp:include page="/layouts/navbar.jsp"></jsp:include>	
		<h3 class="text-center text-primary">Schedule</h3>
		<div class="content w-50 mx-auto">
			<fieldset>
	           <div class="mb-3">
	               <label for="serviceDetails" class="form-label">Service Details</label>
	               <input list="serviceDetails" value="${currServiceDetails}" class="col-sm-6 custom-select custom-select-sm form-control" name="service" placeholder="Select Service" disabled="disabled">
	          </div>
	
	           <div class="mb-3">
	               <label for="busDetails" class="form-label">Bus Details</label>
	               <input list="busDetails" value="${currBusDetails}" class="col-sm-6 custom-select custom-select-sm form-control" name="bus" placeholder="Select bus" disabled>
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
	               <input list="weeks" value="${currWeekDay}" class="col-sm-6 custom-select custom-select-sm form-control" name="week" placeholder="Select week" disabled="disabled">
	            </div>
	                
	                 <div class="grid-row">
	                     <div class="mb-3 grid-col">
	                         <label for="departureTime" class="form-label">Departure Time</label>
	                         <input type="time" class="form-control" id="departureTime" name="depTime" value="${currDep}" disabled="disabled">
	               </div>    
	           		
	               <div class="mb-3 grid-col">
	                   <label for="tripDuration" class="form-label">Duration</label>
	                   <input type="time" class="form-control" id="tripDuration" name="tripDuration" value="${currDuration}" disabled="disabled">
	               </div>          
	           </div>
	           
	           <div class="mb-3">
	                   <label for="basePrice" class="form-label">Base price</label>
	                   <input type="number" class="form-control" id="basePrice" name="basePrice" value="${currBasePrice}" disabled="disabled">
	            </div>
	           
	       </fieldset>
	
		</div>
	</div>
</body>
</html>