<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<jsp:include page="parts/header.jsp"></jsp:include>
<jsp:include page="parts/left_menu.jsp"></jsp:include>
<div class="content">
	<h2>
		<spring:message code="lang.listMachines" text="List of machines" />
	</h2>
	<c:if test="${empty machines}">
		<p>
			<spring:message code="lang.noMachines" text="Actions" />
		</p>
	</c:if>
	<c:if test="${not empty existingMachines && empty machines}">
        <a href="<c:url value="/machine/list"/>"><button class="button"
                type="submit" name="void" value="Void filter">
                <spring:message code="lang.voidFilter" text="Void filter" />
            </button></a>
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
					<c:if
						test="${userType == 'ADMINISTRATOR' || 
                                  userType == 'EMPLOYEE'}">
						<th><spring:message code="lang.machineActions" text="Actions" /></th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${machines}" var="machine">
					<tr>
						<td>${machine.id}</td>
						<td><sec:authorize access="isAuthenticated()">
								<a href="<c:url value="/machine/detail/${machine.id}"/>">
							</sec:authorize> ${machine.label} <sec:authorize access="isAuthenticated()">
								</a>
							</sec:authorize></td>
						<td>${machine.type.typeLabel}</td>
						<td>${machine.description}</td>
						<c:if
							test="${userType == 'ADMINISTRATOR' || 
                                      userType == 'EMPLOYEE'}">
							<td><a href="<c:url value="/machine/detail/${machine.id}"/>">
									<img alt="detail" src="<c:url value="/images/search.png"/>">
							</a><a href="<c:url value="/machine/delete/${machine.id}"/>"> <img
									alt="delete" src="<c:url value="/images/delete.png" />"></a>
								<a href="<c:url value="/machine/update/${machine.id}"/>"> <img
									alt="update" src="<c:url value="/images/update.png" />"></a></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<form:form method="GET" id="filterMachineForm"
					name="filterMachineForm" action="filter">
					<tr>

						<th><spring:message code="lang.filters" text="Filters" /></th>
						<th><form:input cssClass="inputField" path="label" value="${selectedLabel}"/></th>

						<th><form:select path="type">
								<form:option value="--no type--"></form:option>
								<c:forEach items="${types}" var="type">
									<c:choose>
										<c:when test="${type == selectedType}">
											<option value="${type}" selected="true">${type}</option>
										</c:when>
										<c:otherwise>
											<option value="${type}">${type}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></th>
						<th><form:input cssClass="inputField" path="description" value="${selectedDescription}"/></th>
						<th>
							<button class="button" type="submit" name="submit" value="Filter">
								<spring:message code="lang.filter" text="Filter" />
							</button>
							<button class="button" type="submit" name="void"
								value="Void filter">
								<spring:message code="lang.voidFilter" text="Void filter" />
							</button>
						</th>


					</tr>
				</form:form>
			</tfoot>

		</table>
	</c:if>
	<c:if
		test="${userType == 'ADMINISTRATOR' || 
                  userType == 'EMPLOYEE'}">
		<div class="buttonPosition">
			<button type="button" id="newMachineButton" class="button"
				onclick="javascript:showPart('addMachineWrapper');
                              hidePart('newMachineButton')">
				<spring:message code="lang.buttonAddMachine" text="New Machine" />
			</button>
		</div>
	</c:if>
	<ul id="addMachineWrapper" class="offscreen">
		<form:form method="post" id="addMachineForm" name="addMachineForm"
			action="add">
			<table>
				<tr>
					<td><form:label path="label">
							<spring:message code="lang.machineLabel" text="Label" />
						</form:label></td>
					<td><form:input cssClass="inputField" path="label" /></td>
					<td><label id="machineFormIDWarning"
						class="offscreen warningMessage"><spring:message
								code="lang.mandatory" text="Mandatory Field" /></label>
				</tr>
				<tr>
					<td><form:label path="type">
							<spring:message code="lang.machineType" text="Type" />
						</form:label></td>
					<td><form:select path="type" items="${types}" /></td>
				</tr>
				<tr>
					<td><form:label path="description">
							<spring:message code="lang.machineDescription" text="Description" />
						</form:label></td>
					<td><form:input cssClass="inputField" path="description"
							id="machineFormLabel" /></td>
					<td><label id="machineFormDescriptionWarning"
						class="offscreen warningMessage"><spring:message
								code="lang.mandatory" text="Mandatory Field" /></label>
				</tr>
				<tr>
					<td colspan="2">
						<button class="button" type="button"
							onclick="javascript:validateAndSubmitMachineForm()">
							<spring:message code="lang.addMachine" text="Add machine" />
						</button>
					</td>
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
	<c:if test="${updateStatus}">
		<div class="success" id="machineSuccessWindow">
			<p>
				<spring:message code="lang.machineUpdateSuccess"
					text="Machine successfully updated" />
			</p>
		</div>
	</c:if>

	<c:if test="${updateStatus == 'false'}">
		<div class="error" id="machineErrorWindow">
			<p>
				<spring:message code="lang.machineUpdateFailure"
					text="Error occurred during machine editing" />
			</p>
			<p>${errorMessage}</p>
		</div>
	</c:if>


</div>
<jsp:include page="parts/footer.jsp"></jsp:include>