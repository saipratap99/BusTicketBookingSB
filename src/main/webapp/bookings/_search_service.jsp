<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="bookingForm w-80 mx-auto mt-3">
	<form class="row gy-2 gx-3 align-items-center justify-content-center" action="/bookings/get" method="get">
		  <div class="col-auto">
		    <label class="visually-hidden" for="autoSizingInput">Departure location</label>
		    <input list="locations" value="${depLocation}" class="form-control" id="autoSizingInput" name="depLocation" placeholder="Select Departure location">
		    <jsp:include page="/locations/list.jsp">
                 <jsp:param name="locations" value="${locations}"/>
            </jsp:include>
		  </div>
		  <div class="col-auto">
		    <label class="visually-hidden" for="autoSizingInput">Arrival location</label>
		    <input list="locations" value="${arrLocation}" class="form-control" id="autoSizingInput" name="arrLocation" placeholder="Select Arrival location">
		    <jsp:include page="/locations/list.jsp">
                   <jsp:param name="locations" value="${locations}"/>
             </jsp:include>
		  </div>
		  <div class="col-auto">
		    <label class="visually-hidden" for="autoSizingInputGroup">Choose date</label>
		    <input type="date" class="form-control" id="autoSizingInputGroup" name="date" value="${date}">
		  </div>
	
		  <div class="col-auto">
		    <button type="submit" class="btn btn-primary">Submit</button>
		  </div>
		</form>
</div>