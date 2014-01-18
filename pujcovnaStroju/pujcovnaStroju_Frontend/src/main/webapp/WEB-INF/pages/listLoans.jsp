<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	 pageEncoding="UTF-8"%>
<jsp:include page="parts/header.jsp"></jsp:include>
<jsp:include page="parts/left_menu.jsp"></jsp:include>

	<div class="content">
		<h2><spring:message code="lang.listLoans" text="List of Loans" /></h2>

	<div class="loanWrapper">
	</div>
	<br />

	<c:if test="${not empty existingLoans}">
		<h3>
			<spring:message code="lang.filters" text="Filters" />
		</h3>
		<form:form method="GET" id="filterLoanForm"
			   name="filterLoanForm" action="filter">
			<table>
				<tr>
					<td><spring:message code="lang.loanTime" text="Loan Time" /> > </td>
					<td><form:input cssClass="inputField datePicker" path="loanTime" /></td>
				</tr>
				<tr>
					<td><spring:message code="lang.returnTime"
							text="Return Time" /> > </td>
					<td><form:input cssClass="inputField datePicker" path="returnTime" /></td>
				</tr>
				<tr>
					<td><spring:message code="lang.type" text="Type" /></td>
					<td><form:select path="loanState">
							<form:option value="--no type--" />
							<form:options items="${loanStates}" />
						</form:select></td>
				</tr>
				<tr>
					<td><form:label path="customer">
							<spring:message code="lang.customer" text="Customer" />
						</form:label></td>
					<td><form:select path="customer">
							<c:forEach var="customer" items="${customers}">
						<option value="${customer.id}"><c:out value="${customer.lastName} ${customer.firstName} (${customer.id})"/></option>
					</c:forEach>
				</form:select>
				</td>
				</tr>
				<tr>
					<td>
						<button class="button" type="submit" name="submit" value="Filter">
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
		<br />
	</c:if>

	<c:if test="${empty loans}">
		<spring:message code="lang.noLoans" text="Actions" /><br />
	</c:if>

	<c:if test="${not empty loans}">
		<table id="standardTable">
			<thead>
				<tr>
					<th><spring:message code="lang.id" text="ID" /></th>
					<th><spring:message code="lang.customer" text="Customer" /></th>
					<th><spring:message code="lang.machines" text="Machines" /></th>
					<th><spring:message code="lang.loanTime" text="Loan Time" /></th>
					<th><spring:message code="lang.returnTime" text="Return Time" /></th>
					<th><spring:message code="lang.loanState" text="Loan State" /></th>
					<th><spring:message code="lang.actions" text="Actions" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${loans}" var="loan">
					<tr>
						<td>${loan.id}</td>
						<td><a href="<c:url value="/user/detail/${loan.customer.id}"/>">${loan.customer.lastName} ${loan.customer.firstName} (${loan.customer.id})</a></td>
						<td>
							<ul>
								<c:forEach items="${loan.machines}" var="machine">
									<li><a href="<c:url value="/machine/detail/${machine.id}"/>">${machine.label} (${machine.id})</a></li>
									</c:forEach>
							</ul>
						</td>
						<td>${loan.loanTime}</td>
						<td>${loan.returnTime}</td>
						<td>${loan.loanState.typeLabel}</td>
						<td><a href="${loan.id}" class="viewLoanButton"> 
								<img alt="detail" src="<c:url value="/images/search.png"/>"></a>
							<a href="<c:url value="/loan/delete/${loan.id}"/>">
								<img alt="delete" src="<c:url value="/images/delete.png" />"></a>
							<a href="${loan.id}" class="updateLoanButton"> 
								<img alt="update" src="<c:url value="/images/update.png" />"></a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th><spring:message code="lang.id" text="ID" /></th>
					<th><spring:message code="lang.customer" text="Customer" /></th>
					<th><spring:message code="lang.machines" text="Machines" /></th>
					<th><spring:message code="lang.loanTime" text="Loan Time" /></th>
					<th><spring:message code="lang.returnTime" text="Return Time" /></th>
					<th><spring:message code="lang.loanState" text="Loan State" /></th>
					<th><spring:message code="lang.actions" text="Actions" /></th>
				</tr>
			</tfoot>
		</table>            
	</c:if>    
	<div class="buttonPosition">
		<button type="button" class="updateLoanButton button">
			<spring:message code="lang.buttonAddLoan" text="New Loan" />
		</button>
	</div>

	<c:if test="${storeStatus}">
		<div class="success" id="loanSuccessWindow">
			<p>
				<spring:message code="lang.loanStoreSuccess"
						text="Loan successfully stored" />
			</p>
		</div>
	</c:if>

	<c:if test="${storeStatus == 'false'}">
		<div class="error" id="loanErrorWindow">
			<p>
				<spring:message code="lang.loanStoreFailure"
						text="Error occurred during storing loan" />
			</p>
			<p><spring:message code="${errorMessage}" text="error occured"/></p>
		</div>
	</c:if>

	<c:if test="${updateStatus}">
		<div class="success" id="userSuccessWindow">
			<p>
				<spring:message code="lang.loanUpdateSuccess"
						text="Loan successfully updated" />
			</p>
		</div>
	</c:if>

	<c:if test="${updateStatus == 'false'}">
		<div class="error" id="loanErrorWindow">
			<p>
				<spring:message code="lang.loanUpdateFailure"
						text="Error occurred during updating loan" />
			</p>
			<p>${errorMessage}</p>
		</div>
	</c:if>

</div>

<jsp:include page="parts/footer.jsp"></jsp:include>
