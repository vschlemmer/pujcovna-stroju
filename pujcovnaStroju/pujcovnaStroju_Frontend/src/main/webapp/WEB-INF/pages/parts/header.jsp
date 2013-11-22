<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
<script type="text/javascript" src="<c:url value="/scripts/common.js" />"></script>

<link type="text/css" rel="stylesheet" href="<c:url value="/css/jquery.simple-dtpicker.css" />" />
<script type="text/javascript" src="<c:url value="/scripts/jquery-1.10.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/scripts/jquery.simple-dtpicker.js" />"></script>
<script type="text/javascript">
    $(document).ready(function () {
	$(".updateLoanButton").click(function (event) {
	    event.preventDefault();
	    var currentUrl = "<c:url value="/loan/updateForm" />";
	    if ($(this).attr("href") != null) currentUrl += "/"+$(this).attr("href");
	    $.ajax({type: "POST", url: currentUrl,
		success: function(response){
		    $('.loanWrapper').html(response);
		},
		error: function(e){
		    alert('Error: ' + e);
		},
		complete: function () {
		    $("td").click(function () {
			if ($(this).children("input.datePicker").length==0) return;
			$.ajax({type: "GET", url: "<c:url value="/machine/listByParams" />", data: "from="+$("input[name='loanTime']").val()+"&till="+$("input[name='returnTime']").val(),
			    success: function(response){
				$('#machines').html(response);
			    },
			    error: function(e){
				alert('Error: ' + e);
			    }
			});
		    });
		    $.ajax({type: "GET", url: "<c:url value="/machine/listByParams" />", data: "from="+$("input[name='loanTime']").val()+"&till="+$("input[name='returnTime']").val(),
			    success: function(response){
				$('#machines').html(response);
			    },
			    error: function(e){
				alert('Error: ' + e);
			    }
			});
		    $(".datePicker").appendDtpicker();
		}
	    });
	});
	$(".viewLoanButton").click(function (event) {
	    event.preventDefault();
	    $.ajax({type: "POST", url: "<c:url value="/loan/detail" />/"+$(this).attr("href"),
		success: function(response){
		    $('.loanWrapper').html(response);
		},
		error: function(e){
		    alert('Error: ' + e);
		}
	    });
	});
    });
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="${pageTitle}" text="${pageTitle}"/></title>
</head>
<body>
<div class="container">
<div class="header">
<spring:message code="${pageHeader}" text="${pageHeader}"/>
</div>
