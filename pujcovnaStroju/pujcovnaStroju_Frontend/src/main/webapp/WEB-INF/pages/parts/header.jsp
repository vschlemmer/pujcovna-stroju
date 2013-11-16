<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
<script type="text/javascript" src="<c:url value="/scripts/common.js" />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="${pageTitle}" text="${pageTitle}"/></title>
</head>
<body>
<div class="container">
<div class="header">
<spring:message code="${pageHeader}" text="${pageHeader}"/>
</div>