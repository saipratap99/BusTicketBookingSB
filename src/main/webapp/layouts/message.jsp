<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<c:if test="${msg != null}">
	<div class="position-absolute bottom-0 end-0">
		<div class="toast align-items-center text-light bg-${status} ${show}" role="alert" aria-live="assertive" aria-atomic="true">
		  <div class="d-flex">
		    <div class="toast-body">
		    	${msg}
		   </div>
		    <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
		  </div>
		</div>
	</div>
</c:if>
