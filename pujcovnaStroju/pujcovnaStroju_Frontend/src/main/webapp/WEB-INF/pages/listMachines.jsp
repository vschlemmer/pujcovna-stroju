<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<jsp:include page="parts/header.jsp"></jsp:include>
    <p> <spring:message code="lang.listMachines" text=""/></p>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Label</th>
				<th>Type</th>
				<th>Description</th>
				<th>Last Revision</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${machines}" var="machine">
            <tr>
			<td>${machine.id}</td>
			<td>${machine.label}</td>
			<td>${machine.type.typeLabel}</td>
			<td>${machine.description}</td>
			</tr>
		</c:forEach>
		</tbody>
		<tfoot>
            <tr>
                <th>ID</th>
                <th>Label</th>
                <th>Type</th>
                <th>Description</th>
            </tr>
        </tfoot>
		
	</table>
<jsp:include page="parts/footer.jsp"></jsp:include>