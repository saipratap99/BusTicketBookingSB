<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Schedule</title>
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
                                <label for="arrivalTime" class="form-label">Arrival Time</label>
                                <input type="time" class="form-control" id="arrivalTime" name="arrTime">
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