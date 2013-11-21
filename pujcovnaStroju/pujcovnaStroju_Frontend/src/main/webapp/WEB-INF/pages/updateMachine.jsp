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
		<spring:message code="lang.updateMachineTitle" text="Update machine" />
	</h2>
	<c:if test="${not empty machine}">
		<form:form method="post" id="updateMachineForm" name="updateMachineForm"
			action="update">
			<table>
				<tr>
					<form:hidden path="id" value="${machine.id}" />
					<td><form:label path="label">
							<spring:message code="lang.machineLabel" text="Label" />
						</form:label></td>
					<td><form:input cssClass="inputField" path="label"
							value="${machine.label}" /></td>
					<td><label id="userFormFirstNameWarning"
						class="offscreen warningMessage"> <spring:message
								code="lang.mandatory" text="Mandatory Field" />
					</label></td>
				</tr>
				<tr>
					<td><form:label path="description">
							<spring:message code="lang.machineDescription" text="Description" />
						</form:label></td>
					<td><form:input cssClass="inputField" path="description"
							value="${machine.description}" /></td>
					<td><label id="userFormLastNameWarning"
						class="offscreen warningMessage"> <spring:message
								code="lang.mandatory" text="Mandatory Field" />
					</label></td>
				</tr>

				<tr>
					<td><form:label path="type">
							<spring:message code="lang.type" text="Type" />
						</form:label></td>
					<td><form:select path="type">
							<form:option value="${machine.type.typeLabel}" />
							<form:options items="${types}" />
						</form:select></td>
					<td><label id="userFormTypeWarning"
						class="offscreen warningMessage"> <spring:message
								code="lang.mandatory" text="Mandatory Field" />
					</label></td>
				</tr>

				<tr>
					<td colspan="2">
                                            <button class="button" type="button"
						value="Update machine"
						onclick="javascript:validateAndSubmitUpdateMachineForm()">
                                                <spring:message code="lang.updateMachine" text="Update machine" />
                                            </button>
                                        </td>
				</tr>
			</table>
		</form:form>
	</c:if>

	<c:if test="${empty machine}">
		<div class="error">
			<spring:message code="lang.machineNotFound" text="Machine not found" />
			ID: '${id}'
		</div>
	</c:if>

</div>
<jsp:include page="parts/footer.jsp"></jsp:include>