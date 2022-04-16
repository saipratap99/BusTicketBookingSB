<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="Bus Details"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="${title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
	<jsp:include page="/layouts/message.jsp"></jsp:include>
	<jsp:include page="/layouts/navbar.jsp"></jsp:include>	
	<h3 class="text-center text-primary">Bus Details</h3>
		<div class="content">
			<table class="table">
			  <thead>
			  	<tr>
				 	<c:forEach items="${colHeaders}" var="colHeader">
				 		<th scope="col">${colHeader}</th>
				 	</c:forEach>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach items="${busesMap}" var="busMap">
				    <tr>
				    	<c:forEach items="${busMap.entrySet()}" var="entrySet">
				    	    <c:choose>
				    	    	<c:when test="${entrySet.getKey() == 'Bus Id'}">
				    	    		<th scope="row"><a class="link-dark" href="/bus_details/${entrySet.getValue()}">${entrySet.getValue()}</a></th>
				    	    	</c:when>
				    	    	<c:when test="${entrySet.getKey() == 'Update'}">
				    	    		<td>
						      			<a class="btn btn-warning text-light" href="${entrySet.getValue()}">Update</a>
						      		</td>
				    	    	</c:when>
				    	    	<c:when test="${entrySet.getKey() == 'Delete'}">
				    	    		<td>
					      				<form action="${entrySet.getValue()}" method="DELETE">
					      					<button type="submit" class="btn btn-danger text-light">Delete</a>
					      				</form>
					      			</td>
				    	    	</c:when>
				    	    	<c:otherwise>
				    	    		<td>${entrySet.getValue()}</td>	
				    	    	</c:otherwise>
				    	    </c:choose>
				    	</c:forEach>
			      	   	
				    </tr>
			    </c:forEach>
			  </tbody>
			</table>
			<a class="btn btn-primary" href="/bus_details/new">New Bus Details</a>
		</div>
	</div>
</body>
</html>