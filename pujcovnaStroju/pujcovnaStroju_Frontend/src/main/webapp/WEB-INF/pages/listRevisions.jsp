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

<sec:authentication var="principal" property="principal" />

<div class="content">
	<h2>
		<spring:message code="lang.listRevisions" text="List of Revisions" />
	</h2>

	<c:if test="${empty revisions}">
		<spring:message code="lang.noRevisions" text="Actions" />
		<br />
	</c:if>

	<c:if test="${not empty existingRevisions && empty revisions}">
		<a href="<c:url value="/revision/list"/>"><button class="button"
				type="submit" name="void" value="Void filter">
				<spring:message code="lang.voidFilter" text="Void filter" />
			</button></a>
	</c:if>

	<c:if test="${not empty revisions}">
		<table id="standardTable">
			<thead>
				<tr>
					<th><spring:message code="lang.id" text="RevisionID" /></th>
					<th><spring:message code="lang.machine" text="Machine" /></th>
					<th><spring:message code="lang.revDate" text="RevDate" /></th>
					<th><spring:message code="lang.comment" text="Comment" /></th>
					<th><spring:message code="lang.systemUser" text="systemUser" /></th>
					<th><spring:message code="lang.actions" text="Actions" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${revisions}" var="revision">
					<tr>
						<td>${revision.revID}</td>
						<td><a
							href="<c:url value="/machine/detail/${revision.machine.id}"/>">
								${revision.machine.label} </a></td>
						<td>${revision.revDate}</td>
						<td>${revision.comment}</td>
						<td><sec:authorize
								access="${principal.username == revision.systemUser.username}">
								<a
									href="<c:url value="/user/detail/${revision.systemUser.id}"/>">
							</sec:authorize> ${revision.systemUser.lastName} ${revision.systemUser.firstName}
							<sec:authorize
								access="${principal.username == revision.systemUser.username}">
								</a>
							</sec:authorize></td>
						<td><a
							href="<c:url value="/revision/detail/${revision.revID}"/>"> <img
								alt="detail" src="<c:url value="/images/search.png"/>"></a> <sec:authorize
								access="${principal.username == revision.systemUser.username}">
								<a href="<c:url value="/revision/delete/${revision.revID}"/>">
									<img alt="delete" src="<c:url value="/images/delete.png" />">
								</a>
								<a href="<c:url value="/revision/update/${revision.revID}"/>">
									<img alt="update" src="<c:url value="/images/update.png" />">
								</a>
							</sec:authorize></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<form:form method="GET" id="filterRevisionForm"
						name="filterRevisionForm" action="filter">
						<th><spring:message code="lang.filters" text="Filters" /></th>
						<th><form:select path="machine">
								<form:option value="--no machine--"></form:option>
								<c:forEach items="${machines}" var="machine">
									<c:choose>
										<c:when test="${machine.id == selectedMachine}">
											<option value="${machine.id}" selected="true">${machine.label}</option>
										</c:when>
										<c:otherwise>
											<option value="${machine.id}">${machine.label }</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
							</form:select></th>


						<th><form:input cssClass="inputField datePicker"
								path="revDate" value=""/></th>
						<th><form:input cssClass="inputField" path="comment"
								value="${selectedComment}" /></th>

						<th><form:select path="machine">
								<form:option value="--no revisioner--"></form:option>
								<c:forEach items="${users}" var="user">
									<c:choose>
										<c:when test="${user.id == selectedUser}">
											<option value="${user.id}" selected="true">${user.firstName}
												${user.lastName}</option>
										</c:when>
										<c:otherwise>
											<option value="${user.id}">${user.firstName}
												${user.lastName}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
							</form:select></th>
						<th>
							<button class="button" type="submit" name="submit" value="Filter">
								<spring:message code="lang.filter" text="Filter" />
							</button>
							<button class="button" type="submit" name="void"
								value="Void filter">
								<spring:message code="lang.voidFilter" text="Void filter" />
							</button>
						</th>
					</form:form>

				</tr>
			</tfoot>
		</table>
	</c:if>
	<div class="buttonPosition">
		<button type="button" id="newRevisionButton" class="button"
			onclick="javascript:showPart('addRevisionWrapper');hidePart('newRevisionButton')">
			<spring:message code="lang.buttonAddRevision" text="New Revision" />
		</button>
	</div>

	<ul id="addRevisionWrapper" class="offscreen">
		<form:form method="post" id="addRevisionForm" name="addRevisionForm"
			action="add">
			<table>
				<tr>
					<td><form:label path="revDate">
							<spring:message code="lang.RevDate" text="Revision Date" />
						</form:label></td>
					<td><form:input cssClass="inputField datePicker"
							path="revDate" /></td>
					<td><label id="revisionFormRevDateWarning"
						class="offscreen warningMessage"> <spring:message
								code="lang.mandatory" text="Mandatory Field" />
					</label></td>
				</tr>
				<tr>
					<td><form:label path="comment">
							<spring:message code="lang.comment" text="Comment" />
						</form:label></td>
					<td><form:input cssClass="inputField" path="comment" /></td>
					<td><label id="revisionFormCommentWarning"
						class="offscreen warningMessage"> <spring:message
								code="lang.mandatory" text="Mandatory Field" />
					</label></td>
				</tr>
				<tr>
					<td><spring:message code="lang.revisionMachineLabel"
							text="Machine" /></td>
					<td><form:select path="machine">
							<c:forEach items="${machines}" var="currentMachine">
								<form:option value="${currentMachine.id}"
									label="${currentMachine.label}"></form:option>
							</c:forEach>
						</form:select></td>
				</tr>
				<!--<tr>
				<td><spring:message code="lang.customer" text="Customer" /></td>
					<td><form:select path="systemUser">
							<c:forEach items="${users}" var="currentUser">
								<form:option value="${currentUser.id}"
									label="${currentUser.firstName} ${currentUser.lastName}"></form:option>
							</c:forEach>
						</form:select></td>
				</tr>-->
				<tr>
					<td colspan="2">
						<button class="button" type="button" value="Add revision"
							onclick="javascript:validateAndSubmitAddRevisionForm()">
							<spring:message code="lang.addRevision" text="Add Revision" />
						</button>

					</td>
				</tr>
			</table>

		</form:form>
	</ul>

	<c:if test="${storeStatus}">
		<div class="success" id="revisionSuccessWindow">
			<p>
				<spring:message code="lang.revisionStoreSuccess"
					text="Revision successfully stored" />
			</p>
		</div>
	</c:if>

	<c:if test="${storeStatus == 'false'}">
		<div class="error" id="revisionErrorWindow">
			<p>
				<spring:message code="lang.revisionStoreFailure"
					text="Error occurred during storing revision" />
			</p>
			<p>${errorMessage}</p>
		</div>
	</c:if>

	<c:if test="${updateStatus}">
		<div class="success" id="revisionSuccessWindow">
			<p>
				<spring:message code="lang.revisionUpdateSuccess"
					text="Revision successfully updated" />
			</p>
		</div>
	</c:if>

	<c:if test="${updateStatus == 'false'}">
		<div class="error" id="revisionErrorWindow">
			<p>
				<spring:message code="lang.revisionUpdateFailure"
					text="Error occurred during updating revision" />
			</p>
			<p>${errorMessage}</p>
		</div>
	</c:if>
	<c:if test="${deleteStatus}">
		<div class="success" id="revisionSuccessWindow">
			<p>
				<spring:message code="lang.revisionDeleteSuccess"
					text="Revision successfully deleted" />
			</p>
		</div>
	</c:if>

	<c:if test="${deleteStatus == 'false'}">
		<div class="error" id="revisionErrorWindow">
			<p>
				<spring:message code="lang.revisionDeleteFailure"
					text="Error occurred during deleting revision" />
			</p>
			<p>${errorMessage}</p>
		</div>
	</c:if>

</div>

<jsp:include page="parts/footer.jsp"></jsp:include>