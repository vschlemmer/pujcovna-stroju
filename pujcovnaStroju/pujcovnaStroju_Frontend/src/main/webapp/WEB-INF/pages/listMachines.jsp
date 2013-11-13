<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${list}</title>
</head>
<body>
	<h1>${list}</h1>
    <p> <spring:message code="lang.listMachines" /></p>
    <p>${pageContext.response.locale}</p>
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
</body>
</html>