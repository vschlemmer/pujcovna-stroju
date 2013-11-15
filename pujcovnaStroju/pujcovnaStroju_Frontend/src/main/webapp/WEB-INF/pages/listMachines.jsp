<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<jsp:include page="parts/header.jsp"></jsp:include>
<p>
	<spring:message code="lang.listMachines" text="" />
</p>
<table>
	<thead>
		<tr>
			<th>ID</th>
			<th>Label</th>
			<th>Type</th>
			<th>Description</th>
			<th>Last Revision</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${machines}" var="machine">
			<tr>
				<td>${machine.id}</td>
				<td>${machine.label}</td>
				<td>${machine.type.typeLabel}</td>
				<td>${machine.description}</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<th>ID</th>
			<th>Label</th>
			<th>Type</th>
			<th>Description</th>
		</tr>
	</tfoot>

</table>

<button type="button" id="newMachineButton"
	onclick="javascript:showPart('addMachineWrapper');hidePart('newMachineButton')">
	<spring:message code="lang.buttonAddMachine" text="New Machine" />
</button>
<ul id="addMachineWrapper" class="offscreen">
	<form:form method="post" id="addMachineForm" name="addMachineForm"
		action="add">
		<table>
			<tr>
				<td><form:label path="label">label</form:label></td>
				<td><form:input path="label" /></td>
			</tr>
			<tr>
				<td><form:label path="type">type</form:label></td>
				<td><form:input path="type" /></td>
			</tr>
			<tr>
				<td><form:label path="description">description</form:label></td>
				<td><form:input path="description" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add machine" /></td>
			</tr>
		</table>

	</form:form>
</ul>
<jsp:include page="parts/footer.jsp"></jsp:include>