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
		<spring:message code="lang.listUsers" text="List of users" />
	</h2>

	<c:if test="${not empty user}">

		<table id="standardTable">
			<tr>
				<td><spring:message code="lang.machineID" text="ID" /></td>
				<td>${user.id}</td>
			</tr>
			<tr>
				<td><spring:message code="lang.firstName" text="First Name" /></td>
				<td>${user.firstName}</td>
			</tr>
			<tr>
			<tr>
				<td><spring:message code="lang.lastName" text="Last Name" /></td>
				<td>${user.lastName}</td>
			</tr>
			<tr>
				<td><spring:message code="lang.type" text="Type" /></td>
				<td>${user.type.typeLabel}</td>
			</tr>
		</table>
	</c:if>

	<c:if test="${empty user}">
		<div class="error">
			<spring:message code="lang.userNotFound" text="User not found" />
			ID: '${id}'
		</div>
	</c:if>

	<c:if test="${not empty loans}">
		<table id="standardTable">
			<thead>
				<tr>
					<th><spring:message code="lang.id" text="ID" /></th>
					<th><spring:message code="lang.machines" text="Machines" /></th>
					<th><spring:message code="lang.loanTime" text="Loan Time" /></th>
					<th><spring:message code="lang.returnTime" text="Return Time" /></th>
					<th><spring:message code="lang.loanState" text="Loan State" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${loans}" var="loan">
					<tr>
						<td>${loan.id}</td>

						<td>
							<ul>
								<c:forEach items="${loan.machines}" var="machine">
									<li><a
										href="<c:url value="/machine/detail/${machine.id}"/>">${machine.label}
											(${machine.id})</a></li>
								</c:forEach>
							</ul>
						</td>
						<td>${loan.loanTime}</td>
						<td>${loan.returnTime}</td>
						<td>${loan.loanState.typeLabel}</td>

					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th><spring:message code="lang.id" text="ID" /></th>
					<th><spring:message code="lang.machines" text="Machines" /></th>
					<th><spring:message code="lang.loanTime" text="Loan Time" /></th>
					<th><spring:message code="lang.returnTime" text="Return Time" /></th>
					<th><spring:message code="lang.loanState" text="Loan State" /></th>
				</tr>
			</tfoot>
		</table>
	</c:if>

	<c:if test="${empty loans}">
		<div class="info">
			<p>
				<spring:message code="lang.machineNoLoans" text="No loans" />
			</p>

		</div>
	</c:if>



	<c:if test="${not empty revisions}">
		<h3>
			<spring:message code="lang.listRevisionsTitle"
				text="List of revisions" />
		</h3>
		<table id="standardTable">
			<thead>
				<tr>
					<th><spring:message code="lang.id" text="ID" /></th>
					<th><spring:message code="lang.revisionMachineLabel"
							text="Machine" /></th>

					<th><spring:message code="lang.RevDate"
							text="Return date" /></th>
					<th><spring:message code="lang.comment" text="Revisons State" /></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${revisions}" var="revision">
					<tr>
						<td><a
							href="<c:url value="/revision/detail/${revision.revID}"/>">${revision.revID}</a></td>
						<td><a
							href="<c:url value="/machine/detail/${revision.machine.id}"/>">${revision.machine.label}
						</a></td>

						<td>${revision.revDate}</td>
						<td>${revision.comment}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>

					<th><spring:message code="lang.id" text="ID" /></th>
					<th><spring:message code="lang.revisionMachineLabel"
							text="Machine" /></th>

					<th><spring:message code="lang.RevDate"
							text="Return date" /></th>
					<th><spring:message code="lang.comment" text="Revisons State" /></th>
				</tr>
				
			</tfoot>

		</table>
	</c:if>
	<c:if test="${empty revisions}">
		<div class="info">
			<p>
				<spring:message code="lang.machineNoRevisions" text="No Revisions" />
			</p>

		</div>
	</c:if>


</div>
<jsp:include page="parts/footer.jsp"></jsp:include>