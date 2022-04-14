<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="navBar">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <div class="container-fluid">
		    <a class="navbar-brand" href="#">Bus Ticket Booking</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>	
		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="/">Home</a>
		        </li>
		        
		        	
		        	<c:if test="${role != null}">
   						<li class="nav-item">
				          <a class="nav-link" aria-current="page" href="/bookings/new">Booking</a>
				        </li>
   					</c:if>
		        	
		        	<c:if test="${isOperator == true || isAdmin == true}">
 
				        <li class="nav-item">
				          <a class="nav-link" aria-current="page" href="/bus_details/new">Buses</a>
				        </li>
				        <li class="nav-item">
				          <a class="nav-link" aria-current="page" href="/service_details/new">Services</a>
				        </li>
				        <li class="nav-item">
				          <a class="nav-link" aria-current="page" href="/schedule/new">Schedules</a>
				        </li>
   					</c:if>
   					
   					<c:if test="${isAdmin == true}">
   						<li class="nav-item">
				          <a class="nav-link" aria-current="page" href="/admin">Admin</a>
				        </li>
   					</c:if>
   					
   					<c:if test="${role != null}">
   						<li class="nav-item">
				          <a class="nav-link" aria-current="page" href="/users/logout">Log out</a>
				        </li>
   					</c:if>
   					<c:if test="${role == null}">
   						<li class="nav-item">
				          <a class="nav-link" aria-current="page" href="/users/new">Sign Up</a>
				        </li>
				        <li class="nav-item">
				          <a class="nav-link" aria-current="page" href="/users/login">Log in</a>
				        </li>
    				</c:if>
   				
		      </ul>
		    </div>
		  </div>
		</nav>
	</div>
	