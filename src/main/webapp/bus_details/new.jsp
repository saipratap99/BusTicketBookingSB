<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="New Bus Details"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="${title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
		<jsp:include page="/layouts/message.jsp"></jsp:include>
		<jsp:include page="/layouts/navbar.jsp"></jsp:include>
		<div class="content">
			<div class="userForm w-50 mx-auto">
				<form action="/bus_details/create" method="post">
				<fieldset>
					  <legend class="text-center text-primary">Bus Details</legend>
					  <div class="mb-3">
					    <label for="busName" class="form-label">Bus Name</label>
					    <input type="text" class="form-control" id="busName" name="busName">
					  </div>
					  <div class="grid-row">
						  <div class="mb-3">
						    <label for="busRegNumber" class="form-label">Bus Registration Number</label>
						    <input type="text" class="form-control" id="busRegNumber" name="busRegNumber">
						  </div>
						  <div class="mb-3">
						    <label for="busType" class="form-label">Bus Type</label>
						    <input list="busTypes" value="" class="col-sm-6 custom-select custom-select-sm form-control" name="busType" placeholder="Select Bus Type">
							<datalist id="busTypes">
							    <option value="Non - AC">
							    <option value="AC">
							</datalist>
						  </div>
					  </div>
					  <div class="grid-row">
						  <div class="mb-3">
						    <label for="seatingType" class="form-label">Seating Type</label>
						    <input list="seatingTypes" value="" class="col-sm-6 custom-select custom-select-sm form-control" name="seatingType" placeholder="Select Seating Type">
							<datalist id="seatingTypes">
							    <option value="Seater">
							    <option value="Pushback">
							    <option value="Sleeper">
							    <option value="Semi - Sleeper">
							</datalist>
						  </div>
						  <div class="mb-3">
						    <label for="seatCount" class="form-label">Seating Count</label>
						    <input type="number" class="form-control" id="seatCount" name="seatCount">
						  </div>
					  </div>
					  <div class="grid-row">
						  <div class="mb-3">
						    <label for="lastMaintance" class="form-label">Last Maintenance</label>
						    <input type="date" class="form-control" id="lastMaintenance" name="lastMaintance">
						  </div>
						  <div class="mb-3">
						    <label for="onService" class="form-label">On Service</label>
						    <input type="date" class="form-control" id="onService" name="onService">
						  </div>	
					  </div>
					  <button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</fieldset>
				  
			</div>
		</div>
	</div>
</body>
</html>