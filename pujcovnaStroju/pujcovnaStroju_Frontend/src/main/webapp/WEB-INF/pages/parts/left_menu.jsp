<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="left">
    <h2 class="pageLabel"><spring:message code="lang.leftMenuTitle" text="Menu" /></h2>
    <a href="<c:url value="/machine/list"/>"><spring:message
                    code="lang.listMachines" text="List of machines" />
    </a><br/>
    <c:if test="${userType == 'ADMINISTRATOR' ||
                  userType == 'EMPLOYEE'}">
        <a href="<c:url value="/user/list"/>"><spring:message
                        code="lang.listUsers" text="List of users" />
        </a><br/>
    </c:if>
    <c:if test="${userType == 'ADMINISTRATOR' || 
                  userType == 'REVISIONER'}">
        <a href="<c:url value="/revision/list"/>"><spring:message
                        code="lang.listRevisions" text="List of revisions" />
        </a><br/>
    </c:if>
    <c:if test="${userType == 'ADMINISTRATOR' || 
                  userType == 'CUSTOMERINDIVIDUAL' ||
                  userType == 'CUSTOMERLEGAL' ||
                  userType == 'EMPLOYEE'}">
        <a href="<c:url value="/loan/list"/>"><spring:message
                        code="lang.listLoans" text="List of Loans" />
        </a><br/>
    </c:if>
    <hr/>
    <sec:authorize access="isAuthenticated()">
        <a href="<c:url value="/logout"/>"><spring:message
                        code="lang.logout" text="Logout" />
        </a><br />
	</sec:authorize>
	<sec:authorize access="hasRole('CUSTOMERLEGAL') OR hasRole('CUSTOMERINDIVIDUAL')">
		<a href="<c:url value="/user/registrate"/>"><spring:message
                        code="lang.editAccount" text="Edit Account" />
        </a>
	</sec:authorize>
    <sec:authorize access="isAnonymous()">
        <a href="<c:url value="/login"/>"><spring:message
                        code="lang.login" text="Login" />
        </a><br />
		<a href="<c:url value="/user/registrate"/>"><spring:message
                        code="lang.registrate" text="Registrate" />
        </a>
    </sec:authorize>
</div>