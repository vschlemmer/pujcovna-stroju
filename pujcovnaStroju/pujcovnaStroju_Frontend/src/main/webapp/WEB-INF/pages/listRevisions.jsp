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
		<spring:message code="lang.listRevisions" text="List of Revisions" />
	</h2>
        
    <c:if test="${not empty existingRevisions}">
        <h3><spring:message code="lang.filters" text="Filters" /></h3>
        <form:form method="GET" id="filterRevisionForm" name="filterRevisionForm"
                   action="filter">
            <table>
                <tr>
                    <td><spring:message code="lang.RevDate" text="Revision Date" /> > </td>
                    <td><form:input cssClass="inputField datePicker" path="revDate" /></td>
                </tr>
                <tr>
                    <td><spring:message code="lang.comment" text="Comment" /></td>
                    <td><form:input cssClass="inputField" path="comment" /></td>
                </tr>
                <tr>
                    <td><spring:message code="lang.revisionMachineLabel" text="Machine" /></td>
                    <td><form:select path="machine">
                            <form:option value="--no machine--" />
                            <c:forEach items="${machines}" var="machine">
                                <form:option value="${machine.id}"
                                            label="${machine.label}"></form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td><spring:message code="lang.customer" text="Customer" /></td>
                    <td>
                        <form:select path="systemUser">
                            <form:option value="--no revisioner--" />
                            <c:forEach items="${users}" var="currentUser">
                                <form:option value="${currentUser.id}"
                                            label="${currentUser.firstName} ${currentUser.lastName}">
                                </form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button class="button" type="submit" name="submit"
                                value="Filter">
                            <spring:message code="lang.filter" text="Filter" />
                        </button>
                    </td>
                    <td>
                        <button class="button" type="submit" name="void" 
                                value="Void filter">
                            <spring:message code="lang.voidFilter" text="Void filter" />
                        </button>
                    </td>
                </tr>
            </table>
        </form:form>
        <br /><br />
    </c:if>
        
	<c:if test="${empty revisions}">
		<spring:message code="lang.noRevisions" text="Actions" />
		<br />
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
								${revision.machine.label}</a></td>

						<td>${revision.revDate}</td>
						<td>${revision.comment}</td>
						<td><a
							href="<c:url value="/user/detail/${revision.systemUser.id}"/>">${revision.systemUser.firstName}
								${revision.systemUser.lastName}</a></td>
						<td><a
							href="<c:url value="/revision/detail/${revision.revID}"/>"> <img
								alt="detail" src="<c:url value="/images/search.png"/>"></a> <a
							href="<c:url value="/revision/delete/${revision.revID}"/>"> <img
								alt="delete" src="<c:url value="/images/delete.png" />"></a> <a
							href="<c:url value="/revision/update/${revision.revID}"/>"> <img
								alt="update" src="<c:url value="/images/update.png" />"></a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th><spring:message code="lang.id" text="RevisionID" /></th>
					<th><spring:message code="lang.machine" text="Machine" /></th>
					<th><spring:message code="lang.revDate" text="RevDate" /></th>
					<th><spring:message code="lang.comment" text="Comment" /></th>
					<th><spring:message code="lang.systemUser" text="systemUser" /></th>
					<th><spring:message code="lang.actions" text="Actions" /></th>
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
				<td><spring:message code="lang.revisionMachineLabel" text="Machine" /></td>
					<td><form:select path="machine">
							<c:forEach items="${machines}" var="currentMachine">
								<form:option value="${currentMachine.id}"
									label="${currentMachine.label}"></form:option>
							</c:forEach>
						</form:select></td>
				</tr>
				<tr>
				<td><spring:message code="lang.customer" text="Customer" /></td>
					<td><form:select path="systemUser">
							<c:forEach items="${users}" var="currentUser">
								<form:option value="${currentUser.id}"
									label="${currentUser.firstName} ${currentUser.lastName}"></form:option>
							</c:forEach>
						</form:select></td>
				</tr>
				<%--<tr>
                    <td><form:label path="type">
                        <spring:message code="lang.type" text="Type" />
                        </form:label></td>
                    <td><form:input cssClass="inputField" path="type" /></td>
                    <td><label id="userFormTypeWarning"
                            class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>--%>
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