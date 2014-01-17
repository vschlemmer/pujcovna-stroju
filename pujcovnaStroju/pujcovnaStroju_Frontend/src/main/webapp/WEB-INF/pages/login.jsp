<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="parts/header.jsp"></jsp:include>
<jsp:include page="parts/left_menu.jsp"></jsp:include>
<div class="content">
    <h2><spring:message code="lang.loginTitle" 
                        text="Login with Username and Password" /></h2>
    <c:if test="${not empty error}">
        <div class="warningMessage">
            <spring:message code="lang.loginUnsuccesful" 
                            text="Login was not successful" />
        </div>
    </c:if>
    <form name="loginForm" action="<c:url value='j_spring_security_check' />"
		method='POST'>
        <table>
            <tr>
                <td><spring:message code="lang.username" text="Username" /></td>
                <td><input type='text' name='j_username' value=''></td>
            </tr>
            <tr>
                <td><spring:message code="lang.password" text="Password" /></td>
                <td><input type='password' name='j_password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit"
                        value="<spring:message code="lang.login" 
                            text="Login" />" /></td>
            </tr>

        </table>
    </form>
</div>
<jsp:include page="parts/footer.jsp"></jsp:include>