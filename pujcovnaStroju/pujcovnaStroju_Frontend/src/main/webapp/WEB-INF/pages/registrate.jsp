<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<jsp:include page="parts/header.jsp"></jsp:include>
<jsp:include page="parts/left_menu.jsp"></jsp:include>
<div class="content">
        <h2><spring:message code="lang.listUsers" text="List of users" /></h2>
    <ul id="addUserWrapper">
        <form:form method="post" commandName="command" id="addUserForm" name="addUserForm"
                   action="">
            <table>
                <tr>
                    <td><form:label path="firstName">
                            <spring:message code="lang.firstName" text="First Name" />
                        </form:label></td>
                    <td><form:input cssClass="inputField" path="firstName" value="${userReg.firstName}"/></td>
                    <td><label id="userFormFirstNameWarning"
                               class="offscreen warningMessage">
                            <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
                <tr>
                    <td><form:label path="lastName">
                            <spring:message code="lang.lastName" text="Last Name" />
                        </form:label></td>
                    <td><form:input cssClass="inputField" path="lastName" value="${userReg.lastName}"/></td>
                    <td><label id="userFormLastNameWarning"
                               class="offscreen warningMessage">
                            <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
                <tr>
                    <td><form:label path="username">
                            <spring:message code="lang.username" text="Username" />
                        </form:label></td>
                    <td><form:input cssClass="inputField" path="username" value="${userReg.username}"/></td>
                    <td><label id="userFormUsernameWarning"
                               class="offscreen warningMessage">
                            <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
                <tr>
                    <td><form:label path="password">
                            <spring:message code="lang.password" text="Password" />
                        </form:label></td>
                    <td><form:input cssClass="inputField" type="password"
                                path="password" /></td>
                    <td><label id="userFormPasswordWarning"
                               class="offscreen warningMessage">
                            <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
                <tr>
                    <td><form:label path="confPassword">
                            <spring:message code="lang.confPassword" text="Retype password" />
                        </form:label></td>
                    <td><form:input cssClass="inputField" type="password"
                                path="confPassword" /></td>
                    <td><label id="userFormConfPasswordWarning"
                               class="offscreen warningMessage">
                            <spring:message code="lang.passwordNotMatch" 
                                            text="Passwords do not match" />
                        </label></td>
                </tr>
                <tr>
                    <td><form:label path="type">
                            <spring:message code="lang.type" text="Type" />
                        </form:label></td>
                    <td><form:select path="type">
                            <form:option value="${userReg.type.typeLabel}" />
                            <form:options items="${types}" />
                        </form:select>  </td>
                    <td><label id="userFormTypeWarning"
                               class="offscreen warningMessage">
                            <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button class="button" type="button"
                                value="Add user"
                                onclick="javascript:validateAndSubmitAddUserForm()">
                            <spring:message code="lang.addUser" text="Add User" />
                        </button>
                    </td>
                </tr>
            </table>

        </form:form>
    </ul>

    <c:if test="${storeStatus}">
        <div class="success" id="userSuccessWindow">
            <p>
                <spring:message code="lang.userStoreSuccess"
                                text="User successfully stored" />
            </p>
        </div>
    </c:if>

    <c:if test="${storeStatus == 'false'}">
        <div class="error" id="userErrorWindow">
            <p>
                <spring:message code="lang.userStoreFailure"
                                text="Error occurred during storing user" />
            </p>
            <p>${errorMessage}</p>
        </div>
    </c:if>

    <c:if test="${updateStatus}">
        <div class="success" id="userSuccessWindow">
            <p>
                <spring:message code="lang.userUpdateSuccess"
                                text="User successfully updated" />
            </p>
        </div>
    </c:if>

    <c:if test="${updateStatus == 'false'}">
        <div class="error" id="userErrorWindow">
            <p>
                <spring:message code="lang.userUpdateFailure"
                                text="Error occurred during updating user" />
            </p>
            <p>${errorMessage}</p>
        </div>
    </c:if>

</div>

<jsp:include page="parts/footer.jsp"></jsp:include>