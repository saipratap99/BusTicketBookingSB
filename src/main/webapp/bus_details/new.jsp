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
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/layouts/message.jsp"></jsp:include>
		<jsp:include page="/layouts/navbar.jsp"></jsp:include>
		<div class="content">
			<div class="userForm w-50 mx-auto">
				<form action="/bus_details/create" method="post">
				<fieldset>
					<div>
						<legend class="text-center text-primary">Bus Details</legend>
					</div>
					  <div class="mb-3">
					    <label for="busName" class="form-label">Bus Name</label>
					    <input type="text" class="form-control" id="busName" name="busName">
					  </div>
					  <div class="mb-3">
					    <label for="busRegNumber" class="form-label">Bus Registration Number</label>
					    <input type="text" class="form-control" id="busRegNumber" name="busRegNumber">
					  </div>
					  <div class="mb-3">
					    <label for="busType" class="form-label">Bus Type</label>
					    <input type="text" class="form-control" id="busType" name="busType">
					  </div>
					  <div class="mb-3">
					    <label for="seatingType" class="form-label">Seating Type</label>
					    <input type="text" class="form-control" id="seatingType" name="seatingType">
					  </div>
					  <div class="mb-3">
					    <label for="seatCount" class="form-label">Seating Count</label>
					    <input type="number" class="form-control" id="seatCount" name="seatCount">
					  </div>
					  <div class="mb-3">
					    <label for="lastMaintenance" class="form-label">Last Maintenance</label>
					    <input type="date" class="form-control" id="lastMaintenance" name="lastMaintenance">
					  </div>
					  <div class="mb-3">
					    <label for="onService" class="form-label">On Service</label>
					    <input type="date" class="form-control" id="onService" name="onService">
					  </div>
					  <div class="mb-3">
					  	<label for="currLocation" class="form-label">Current Location</label>
					  	<input list="locations" value="" class="col-sm-6 custom-select custom-select-sm form-control" name="currLocation" placeholder="Select location">
						<jsp:include page="/locations/list.jsp">
					        <jsp:param name="locations" value="${locations}"/>
					    </jsp:include>
					  </div>
					  <div class="mb-3">
					  	<label for="serviceDetails" class="form-label">Service Details</label>
					  	<input list="serviceDetails" value="" class="col-sm-6 custom-select custom-select-sm form-control" name="serviceDetails" placeholder="Select service details">
						<datalist id="serviceDetails">
						    <option value="Hyderabad - Vijayawada">Hyderabad - Vijayawada</option>
						    <option value="Vijayawada - Hyderabad">Vijayawada - Hyderabad</option>
						    <option value="Vishakapatnam - Hyderabad">Vishakapatnam - Hyderabad</option>
						</datalist>
					  </div>
					  
					  <button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</fieldset>
				  
			</div>
		</div>
	</div>
</body>
</html>