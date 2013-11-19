<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<jsp:include page="parts/header.jsp"></jsp:include>
<jsp:include page="parts/left_menu.jsp"></jsp:include>
<div class="content">

	<h2 class="pageLabel">
		<spring:message code="lang.detailMachineTitle"
			text="Details of machine" />
	</h2>

	<table id="standardTable">
		<tr>
			<td><spring:message code="lang.machineID" text="ID" /></td>
			<td>${machine.id}</td>
		</tr>
		<tr>
			<td><spring:message code="lang.machineLabel" text="Label" /></td>
			<td>${machine.label}</td>
		</tr>
		<tr>
		<tr>
			<td><spring:message code="lang.machineType" text="Type" /></td>
			<td>${machine.type.typeLabel}</td>
		</tr>
		<tr>
			<td><spring:message code="lang.machineDescription"
					text="Description" /></td>
			<td>${machine.description}</td>
		</tr>
	</table>








	<c:if test="${empty machine}">
		<div class="error">
			<spring:message code="lang.machineNotFound" text="Machine not found" />
			ID: '${id}'
		</div>
	</c:if>




</div>
<jsp:include page="parts/footer.jsp"></jsp:include>