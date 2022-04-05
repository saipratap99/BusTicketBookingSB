<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<datalist id="locations">
	<c:forEach items="${locations}" var="location">
	    <option value="${location.getLocationName()}">${location.getLocationName()}</option>
    </c:forEach>
</datalist>