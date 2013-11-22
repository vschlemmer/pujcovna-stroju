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
		<spring:message code="lang.detailRevisionTitle"
			text="Details of revision" />
	</h2>

	<table id="standardTable">
		<tr>
			<td><spring:message code="lang.machineID" text="ID" /></td>
			<td>${revision.revID}</td>
		</tr>
		<tr>
			<td><spring:message code="lang.revisionComment" text="Comment" /></td>
			<td>${revision.comment}</td>
		</tr>
		<tr>
		<tr>
			<td><spring:message code="lang.RevDate" text="Date" /></td>
			<td>${revision.revDate}</td>
		</tr>
		<tr>
			<td><spring:message code="lang.revisionMachineLabel"
					text="Label" /></td>
			<td><a
				href="<c:url value="/machine/detail/${revision.machine.id}"/>">${revision.machine.label}
			</a></td>
		</tr>
		<tr>
			<td><spring:message code="lang.revisionUser" text="Revisioner" /></td>
			<td><a
				href="<c:url value="/user/detail/${revision.systemUser.id}"/>">${revision.systemUser.lastName}
					${revision.systemUser.firstName} </a></td>
		</tr>

	</table>
	<c:if test="${empty revision}">
		<div class="error">
			<spring:message code="lang.revisionNotFound"
				text="Revision not found" />
			ID: '${id}'
		</div>
	</c:if>


</div>
<jsp:include page="parts/footer.jsp"></jsp:include>