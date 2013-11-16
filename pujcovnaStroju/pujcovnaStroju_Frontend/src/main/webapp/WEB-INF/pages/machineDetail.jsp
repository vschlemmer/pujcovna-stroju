<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<jsp:include page="parts/header.jsp"></jsp:include>
<jsp:include page="parts/left_menu.jsp"></jsp:include>
<div class="content">
	<h2>
		<spring:message code="lang.listMachines" text="List of machines" />
	</h2>


	<c:if test="${not empty machine}">
		<p>${machine.id}</p>
		<p>${machine.label}</p>
		<p>${machine.type.typeLabel}</p>
		<p>${machine.description}</p>
	</c:if>

	<c:if test="${empty machine}">
		<div class="error">
			<spring:message code="lang.machineNotFound" text="Machine not found" />
			ID: '${id}'
		</div>
	</c:if>




</div>
<jsp:include page="parts/footer.jsp"></jsp:include>