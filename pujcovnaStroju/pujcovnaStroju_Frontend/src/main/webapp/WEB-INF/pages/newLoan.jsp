<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<jsp:include page="parts/header.jsp"></jsp:include>
<jsp:include page="parts/left_menu.jsp"></jsp:include>
<div class="content">

	<h2 class="pageLabel">
		<spring:message code="lang.detailMachineTitle"
			text="Details of machine" />
	</h2>



	<form:form method="post" id="addLoanForm" name="addLoanForm"
		commandName="command" action="add">

		<table>
			<tr>
				<td><form:label path="customer">
						<spring:message code="lang.customer" text="Customer" />
					</form:label></td>
				<td><form:select path="customer">
						<form:options items="${customers}" itemLabel="firstName"
							itemValue="id" />
					</form:select></td>
				<td><label id="loanFormCustomerWarning"
					class="offscreen warningMessage"> <spring:message
							code="lang.mandatory" text="Mandatory Field" />
				</label></td>
			</tr>
			<tr>
				<td><form:label path="loanTime">
						<spring:message code="lang.loanTime" text="Loan Time" />
					</form:label></td>
				<td><form:input cssClass="inputField datePicker"
						path="loanTime" /></td>
				<td><label id="loanFormLoanTimeWarning"
					class="offscreen warningMessage"> <spring:message
							code="lang.mandatory" text="Mandatory Field" />
				</label></td>
			</tr>
			<tr>
				<td><form:label path="returnTime">
						<spring:message code="lang.returnTime" text="Return Time" />
					</form:label></td>
				<td><form:input cssClass="inputField datePicker"
						path="returnTime" /></td>
				<td><label id="loanFormReturnTimeWarning"
					class="offscreen warningMessage"> <spring:message
							code="lang.mandatory" text="Mandatory Field" />
				</label></td>
			</tr>
			<tr>
				<td><form:label path="loanState">
						<spring:message code="lang.loanState" text="Loan State" />
					</form:label></td>
				<td><form:select path="loanState" items="${loanStates}" /></td>
				<td><label id="loanFormLoanWarning"
					class="offscreen warningMessage"> <spring:message
							code="lang.mandatory" text="Mandatory Field" />
				</label></td>
			</tr>


            
			<tr>
				<td colspan="2"><input class="button" type="button"
					value="Add loan"
					onclick="javascript:validateAndSubmitAddLoanForm()" /></td>
			</tr>
		</table>
        
	</form:form>
${machine}
</div>
<jsp:include page="parts/footer.jsp"></jsp:include>