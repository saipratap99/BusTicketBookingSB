<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Booking</title>
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
			<div class="bookingForm w-80 mx-auto mt-3">
				<form class="row gy-2 gx-3 align-items-center justify-content-center" action="/bookings/get" method="get">
					  <div class="col-auto">
					    <label class="visually-hidden" for="autoSizingInput">Departure location</label>
					    <input list="locations" value="" class="form-control" id="autoSizingInput" name="depLocation" placeholder="Select Departure location">
					    <jsp:include page="/locations/list.jsp">
			                   <jsp:param name="locations" value="${locations}"/>
			               </jsp:include>
					  </div>
					  <div class="col-auto">
					    <label class="visually-hidden" for="autoSizingInput">Arrival location</label>
					    <input list="locations" value="" class="form-control" id="autoSizingInput" name="arrLocation" placeholder="Select Arrival location">
					    <jsp:include page="/locations/list.jsp">
			                   <jsp:param name="locations" value="${locations}"/>
			               </jsp:include>
					  </div>
					  <div class="col-auto">
					    <label class="visually-hidden" for="autoSizingInputGroup">Choose date</label>
					    <input type="date" class="form-control" id="autoSizingInputGroup" name="date">
					  </div>
				
					  <div class="col-auto">
					    <button type="submit" class="btn btn-primary">Submit</button>
					  </div>
					</form>
			</div>
		</div>
	</div>
</body>
</html>