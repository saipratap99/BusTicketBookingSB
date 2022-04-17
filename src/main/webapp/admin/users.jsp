<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<c:set var="title" scope="session" value="Users"/>
	<jsp:include page="/layouts/_head_tag.jsp">
		<jsp:param value="${title}" name="title"/>
	</jsp:include>
<body>
	<div class="container-fluid">
	<jsp:include page="/layouts/message.jsp"></jsp:include>
	<jsp:include page="/layouts/navbar.jsp"></jsp:include>	
	<h3 class="text-center text-primary">Users</h3>
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
			  	<c:forEach items="${usersMap}" var="userMap">
				    <tr>
				    	<c:forEach items="${userMap.entrySet()}" var="entrySet">
				    	    <c:choose>
				    	    	<c:when test="${entrySet.getKey() == 'User Id'}">
				    	    		<th scope="row"><a class="link-dark" href="/users/${entrySet.getValue()}">${entrySet.getValue()}</a></th>
				    	    	</c:when>
				    	    	<c:when test="${entrySet.getKey() == 'Update'}">
				    	    		<td>
				    	    			
						      			<a class="btn btn-${userMap.get("Role") == 'ROLE_OPERATOR' ? 'danger' : 'warning'} text-light" href="${entrySet.getValue()}">${ userMap.get("Role") == 'ROLE_OPERATOR' ? 'Remove as Operator' : 'Make as Operator'}</a>
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
		</div>
	</div>
</body>
</html>