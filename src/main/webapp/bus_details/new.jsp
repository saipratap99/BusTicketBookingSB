<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Bus Details</title>
	<!-- Google fonts -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karla|Archivo|Roboto" />
    <!--jQuery-->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <!-- Bootstarp -->
    <!-- <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous"> -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <!-- Font Awesome -->
    <script src="https://use.fontawesome.com/bae75bb48f.js"></script>
    <!-- Google fonts -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia">
    <link rel="stylesheet" href="/css/service_details.css">
</head>
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
					  <div class="mb-3">
					  	<label for="currLocation" class="form-label">Current Location</label>
					  	<input list="locations" value="" class="col-sm-6 custom-select custom-select-sm form-control" name="location" placeholder="Select location">
						<jsp:include page="/locations/list.jsp">
					        <jsp:param name="locations" value="${locations}"/>
					    </jsp:include>
					  </div>
					  <div class="mb-3">
					  	<label for="serviceDetails" class="form-label">Service Details</label>
					  	<input list="serviceDetails" value="" class="col-sm-6 custom-select custom-select-sm form-control" name="service" placeholder="Select service details">
						<jsp:include page="/service_details/list.jsp">
					        <jsp:param name="services" value="${services}"/>
					    </jsp:include>
					  </div>
					  
					  <button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</fieldset>
				  
			</div>
		</div>
	</div>
</body>
</html>