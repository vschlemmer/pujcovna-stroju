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
        <spring:message code="lang.listLoans" text="List of Loans" />
    </h2>

    <c:if test="${not empty loan}">
        <p>${loan.id}</p>
        <p>${loan.customer}</p>
        <p>${loan.loanTime}</p>
	<p>${loan.returnTime}</p>
        <p>${loan.loanState.typeLabel}</p>
    </c:if>

    <c:if test="${empty loan}">
        <div class="error">
            <spring:message code="lang.loanNotFound" text="Loan not found" />
            ID: '${id}'
        </div>
    </c:if>

</div>
<jsp:include page="parts/footer.jsp"></jsp:include>