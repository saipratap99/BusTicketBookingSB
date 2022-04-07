<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<datalist id="serviceDetails">
	<c:forEach items="${services}" var="service">
	    <option value="${service}">
    </c:forEach>
</datalist>