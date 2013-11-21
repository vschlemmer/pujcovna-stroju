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
    <br /><br /><br />
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
                        <td>${loan.customer.firstName} ${loan.customer.lastName} ${loan.customer.id}</td>
			<td>
			    <ul>
				<c:forEach items="${loan.machines}" var="machine">
				    <li><a href="<c:url value="/machine/detail/${machine.id}"/>">${machine.label}</a></li>
				</c:forEach>
			    </ul>
			</td>
                        <td>${loan.loanTime}</td>
                        <td>${loan.returnTime}</td>
			<td>${loan.loanState.typeLabel}</td>
                        <td><a href="<c:url value="/loan/detail/${loan.id}"/>">
                                <img alt="detail" src="<c:url value="/images/search.png"/>"></a>
                            <a href="<c:url value="/loan/delete/${loan.id}"/>">
                                <img alt="delete" src="<c:url value="/images/delete.png" />"></a>
                            <a
							href="<c:url value="/loan/update/${loan.id}"/>"> <img
								alt="update" src="<c:url value="/images/update.png" />"></a></td>
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
        <button type="button" id="newLoanButton" class="button"
                onclick="javascript:showPart('addLoanWrapper');hidePart('newLoanButton')">
                <spring:message code="lang.buttonAddLoan" text="New Loan" />
        </button>
    </div>
        
    <ul id="addLoanWrapper" class="offscreen">
        <form:form method="post" id="addLoanForm" name="addLoanForm"
                action="add">
            <table>
                <tr>
                    <td><form:label path="customer">
                        <spring:message code="lang.customer" text="Customer" />
                        </form:label></td>
                    <td><form:select path="customer">
			    <form:options items="${customers}" itemLabel="firstName" itemValue="id" />
			</form:select>
		    </td>
                    <td><label id="loanFormCustomerWarning"
                            class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
                <tr>
                    <td><form:label path="loanTime">
                        <spring:message code="lang.loanTime" text="Loan Time" />
                        </form:label></td>
                    <td><form:input cssClass="inputField datePicker" path="loanTime" /></td>
                    <td><label id="loanFormLoanTimeWarning"
                            class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
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
                            class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
                <tr>
                <td>
		    <a class="loadMachines" style="font-weight: bold; cursor: pointer; color: #0044aa;">Reload machines</a>
                </td>
		<td>
		    <div id="machines"></div>
		</td>
                </tr>
                
                <tr>
                    <td colspan="2"><input class="button" type="button"
                            value="Add loan"
                            onclick="javascript:validateAndSubmitAddLoanForm()" /></td>
                </tr>
            </table>

        </form:form>
    </ul>
        
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
            <p>${errorMessage}</p>
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