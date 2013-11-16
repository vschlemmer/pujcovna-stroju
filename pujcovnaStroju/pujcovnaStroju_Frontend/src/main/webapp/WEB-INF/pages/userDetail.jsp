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
        <p>${user.id}</p>
        <p>${user.firstName}</p>
        <p>${user.lastName}</p>
        <p>${user.type.typeLabel}</p>
    </c:if>

    <c:if test="${empty user}">
        <div class="error">
            <spring:message code="lang.userNotFound" text="User not found" />
            ID: '${id}'
        </div>
    </c:if>

</div>
<jsp:include page="parts/footer.jsp"></jsp:include>