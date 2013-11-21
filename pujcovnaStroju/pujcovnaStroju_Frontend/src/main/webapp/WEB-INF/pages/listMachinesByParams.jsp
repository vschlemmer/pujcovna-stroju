<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<h2>
		<spring:message code="lang.listMachines" text="List of machines" />
	</h2>

	<c:if test="${empty machines}">
		<p>
			<spring:message code="lang.noMachines" text="Actions" />
		</p>
	</c:if>

	<c:if test="${not empty machines}">
		<table id="standardTable">
			<thead>
				<tr>
					<th>ID</th>
					<th><spring:message code="lang.machineLabel" text="Label" /></th>
					<th><spring:message code="lang.machineType" text="Type" /></th>
					<th><spring:message code="lang.machineDescription"
							text="Description" /></th>
					<th><spring:message code="lang.machineActions" text="Actions" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${machines}" var="machine">
					<tr>
						<td>${machine.id}</td>
						<td>${machine.label}</td>
						<td>${machine.type.typeLabel}</td>
						<td>${machine.description}</td>
						<td><input type="checkbox" name="machineList" id="machineList" value="${machine.id}" /></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th>ID</th>
					<th><spring:message code="lang.machineLabel" text="Label" /></th>
					<th><spring:message code="lang.machineType" text="Type" /></th>
					<th><spring:message code="lang.machineDescription"
							text="Description" /></th>
					<th><spring:message code="lang.machineActions" text="Actions" /></th>
				</tr>
			</tfoot>

		</table>
	</c:if>