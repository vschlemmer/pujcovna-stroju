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
        <spring:message code="lang.updateUserTitle" text="Update user" />
    </h2>
    <br /><br /><br />
    <c:if test="${not empty user}">
        <form:form method="post" id="updateUserForm" name="updateUserForm"
                action="update">
            <table>
                <tr>
                    <form:hidden path="id" value="${user.id}" />
                    <td>
                        <form:label path="firstName">
                        <spring:message code="lang.firstName" text="First Name" />
                        </form:label>
                    </td>
                    <td>
                        <form:input cssClass="inputField" path="firstName" value="${user.firstName}" />
                    </td>
                    <td><label id="userFormFirstNameWarning"
                               class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="lastName">
                        <spring:message code="lang.lastName" text="Last Name" />
                        </form:label>
                    </td>
                    <td>
                        <form:input cssClass="inputField" path="lastName" value="${user.lastName}" />
                    </td>
                    <td><label id="userFormLastNameWarning"
                               class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="type">
                        <spring:message code="lang.type" text="Type" />
                        </form:label>
                    </td>
                    <td>
                        <form:input cssClass="inputField" path="type" value="${user.type.typeLabel}" />
                    </td>
                    <td><label id="userFormTypeWarning"
                               class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input class="button" type="button" value="Update user"
                            onclick="javascript:validateAndSubmitUpdateUserForm()" />
                    </td>
                </tr>
            </table>
        </form:form>
    </c:if>

    <c:if test="${empty user}">
        <div class="error">
            <spring:message code="lang.userNotFound" text="User not found" />
            ID: '${id}'
        </div>
    </c:if>

</div>
<jsp:include page="parts/footer.jsp"></jsp:include>