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
        <spring:message code="lang.updateLoanTitle" text="Update loan" />
    </h2>
    <br /><br /><br />
    <c:if test="${not empty loan}">
        <form:form method="post" id="updateLoanForm" name="updateLoanForm"
                action="update">
	    <form:hidden path="id" value="${loan.id}" />
            <table>
                <tr>
                    <td><form:label path="customer">
                        <spring:message code="lang.customer" text="Customer" />
                        </form:label></td>
                    <td><form:input cssClass="inputField" path="customer" value="${loan.customer.firstName} ${loan.customer.lastName} (${loan.customer.id})" /></td>
                    <td><label id="loanFormCustomerWarning"
                            class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
                <tr>
                    <td><form:label path="loanTime">
                        <spring:message code="lang.loanTime" text="Loan Time" />
                        </form:label></td>
                    <td><form:input cssClass="inputField" path="loanTime" value="${loan.loanTime}" /></td>
                    <td><label id="loanFormLoanTimeWarning"
                            class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
		<tr>
                    <td><form:label path="returnTime">
                        <spring:message code="lang.returnTime" text="Return Time" />
                        </form:label></td>
                    <td><form:input cssClass="inputField" path="returnTime" value="${loan.returnTime}" /></td>
                    <td><label id="loanFormReturnTimeWarning"
                            class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
                <tr>
                    <td><form:label path="loanState">
                        <spring:message code="lang.loanState" text="Loan State" />
                        </form:label></td>
                    <td><form:select path="loanState" items="${loanStates}" /></td>
                    <td><label id="loanFormLoanWarning"
                            class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
                
                
                
                <tr>
                    <td colspan="2"><input class="button" type="button"
                            value="Update loan"
                            onclick="javascript:validateAndSubmitUpdateLoanForm()" /></td>
                </tr>
	    </table>
        </form:form>
    </c:if>

    <c:if test="${empty loan}">
        <div class="error">
            <spring:message code="lang.loanNotFound" text="Loan not found" />
            ID: '${id}'
        </div>
    </c:if>

</div>
<jsp:include page="parts/footer.jsp"></jsp:include>