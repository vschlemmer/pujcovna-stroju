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

    <c:if test="${not empty revision}">
        <p>${revision.revID}</p>
        <%--<p>${revision.machine}</p>--%>
        <p>${revision.revDate}</p>
        <p>${revision.comment}</p>
       <%-- <p>${revision.systemUser}</p>--%>
        <%--<p>${revision.type.typeLabel}</p>--%>
    </c:if>

    <c:if test="${empty revision}">
        <div class="error">
            <spring:message code="lang.revisionNotFound" text="Revision not found" />
            ID: '${id}'
        </div>
    </c:if>

</div>
<jsp:include page="parts/footer.jsp"></jsp:include>