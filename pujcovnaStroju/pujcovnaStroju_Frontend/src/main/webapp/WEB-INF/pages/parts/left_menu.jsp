<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="left">
    <h2><spring:message code="lang.leftMenuTitle" text="Menu" /></h2>
    <br /><br />
    <a href="<c:url value="/machine/list"/>"><spring:message
                    code="lang.listMachines" text="List of machines" />
    </a><br/>
    <a href="<c:url value="/user/list"/>"><spring:message
                    code="lang.listUsers" text="List of users" />
    </a><br/>
    <a href="<c:url value="/revision/list"/>"><spring:message
                    code="lang.listRevisions" text="List of revisions" />
    </a><br/>
    <a href="<c:url value="/loan/list"/>"><spring:message
                    code="lang.listLoans" text="List of Loans" />
    </a>
</div>