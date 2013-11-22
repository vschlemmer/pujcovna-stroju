<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <c:if test="${not empty loan}">
	
	<h2>
		<spring:message code="lang.detailLoanTitle" text="Loan Information" />
	</h2>

	<table id="standardTable">
		<tr>
			<td><spring:message code="lang.loanID" text="ID" /></td>
			<td>${loan.id}</td>
		</tr>
		<tr>
			<td><spring:message code="lang.customer" text="Customer" /></td>
			<td><a href="<c:url value="/user/detail/${loan.customer.id}"/>">${loan.customer.lastName} ${loan.customer.firstName} (${loan.customer.id})</a></td>
		</tr>
		<tr>
		<tr>
			<td><spring:message code="lang.loanTime" text="Loan Time" /></td>
			<td>${loan.loanTime}</td>
		</tr>
		<tr>
			<td><spring:message code="lang.returnTime" text="Return Time" /></td>
			<td>${loan.returnTime}</td>
		</tr>
		<tr>
			<td><spring:message code="lang.loanState" text="Loan State" /></td>
			<td>${loan.loanState.typeLabel}</td>
		</tr>
	</table>
	<c:if test="${not empty loan.machines}">
		<table id="standardTable">
			<thead>
				<tr>
					<th>ID</th>
					<th><spring:message code="lang.machineLabel" text="Label" /></th>
					<th><spring:message code="lang.machineType" text="Type" /></th>
					<th><spring:message code="lang.machineDescription" text="Description" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${loan.machines}" var="machine">
					<tr>
						<td>${machine.id}</td>
						<td><a href="<c:url value="/machine/detail/${machine.id}"/>">${machine.label}</a></td>
						<td>${machine.type.typeLabel}</td>
						<td>${machine.description}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th>ID</th>
					<th><spring:message code="lang.machineLabel" text="Label" /></th>
					<th><spring:message code="lang.machineType" text="Type" /></th>
					<th><spring:message code="lang.machineDescription" text="Description" /></th>
				</tr>
			</tfoot>

		</table>
	</c:if>
	<c:if test="${empty loan.machines}">
		<div class="info">
			<p>
				<spring:message code="lang.loanNoMachines" text="No Machines" />
			</p>

		</div>
	</c:if>
    </c:if>

    <c:if test="${empty loan}">
        <div class="error">
            <spring:message code="lang.loanNotFound" text="Loan not found" />
            ID: '${id}'
        </div>
    </c:if>