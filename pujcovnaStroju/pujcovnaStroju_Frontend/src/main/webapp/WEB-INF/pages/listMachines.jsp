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

    <c:if test="${empty machines}">
        <p><spring:message code="lang.noMachines" text="Actions" /></p>
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
					<td><a href="<c:url value="/machine/detail/${machine.id}"/>">
							<img alt="detail" src="<c:url value="/images/search.png"/>">
					</a> <a href="<c:url value="/machine/delete/${machine.id}"/>"> <img
							alt="delete" src="<c:url value="/images/delete.png" />">
					</a></td>
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

	<div class="buttonPosition">
		<button type="button" id="newMachineButton" class="button"
			onclick="javascript:showPart('addMachineWrapper');hidePart('newMachineButton')">
			<spring:message code="lang.buttonAddMachine" text="New Machine" />
		</button>
	</div>
	<ul id="addMachineWrapper" class="offscreen">
		<form:form method="post" id="addMachineForm" name="addMachineForm"
			action="add">
			<table>
				<tr>
					<td><form:label path="label">label</form:label></td>
					<td><form:input cssClass="inputField" path="label" /></td>
					<td><label id="machineFormIDWarning"
						class="offscreen warningMessage">mandatory field</label>
				</tr>
				<tr>
					<td><form:label path="type">type</form:label></td>
					<td><form:input cssClass="inputField" path="type" /></td>
				</tr>
				<tr>
					<td><form:label path="description">description</form:label></td>
					<td><form:input cssClass="inputField" path="description"
							id="machineFormLabel" /></td>
				</tr>
				<tr>
					<td colspan="2"><input class="button" type="button"
						value="Add machine"
						onclick="javascript:validateAndSubmitMachineForm()" /></td>
				</tr>
			</table>

		</form:form>
	</ul>
	<c:if test="${storeStatus}">
		<div class="success" id="machineSuccessWindow">
			<p>
				<spring:message code="lang.machineStoreSuccess"
					text="Machine successfully stored" />
			</p>
		</div>
	</c:if>

	<c:if test="${storeStatus == 'false'}">
		<div class="error" id="machineErrorWindow">
			<p>
				<spring:message code="lang.machineStoreFailure"
					text="Error occurred during machine storing" />
			</p>
			<p>${errorMessage}</p>
		</div>
	</c:if>

	<c:if test="${deleteStatus}">
		<div class="success" id="machineSuccessWindow">
			<p>
				<spring:message code="lang.machineDeleteSuccess"
					text="Machine successfully deleted" />
			</p>
		</div>
	</c:if>

	<c:if test="${deleteStatus == 'false'}">
		<div class="error" id="machineErrorWindow">
			<p>
				<spring:message code="lang.machineDeleteFailure"
					text="Error occurred during machine deleting" />
			</p>
			<p>${errorMessage}</p>
		</div>
	</c:if>


</div>
<jsp:include page="parts/footer.jsp"></jsp:include>