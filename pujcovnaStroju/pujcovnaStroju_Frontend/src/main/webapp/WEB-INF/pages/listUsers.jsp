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
<c:if test="${not empty existingUsers}">
        <h3><spring:message code="lang.filters" text="Filters" /></h3>
        <form:form method="GET" id="filterUserForm" name="filterUserForm"
                   action="filter">
            <table>
                <tr>
                    <td><spring:message code="lang.firstName" text="First Name" /></td>
                    <td><form:input cssClass="inputField" path="firstName" /></td>
                </tr>
                <tr>
                    <td><spring:message code="lang.lastName" text="Last Name" /></td>
                    <td><form:input cssClass="inputField" path="lastName" /></td>
                </tr>
                <tr>
                    <td><spring:message code="lang.type" text="Type" /></td>
                    <td>
                        <form:select path="type">
                            <form:option value="--no type--" />
                            <form:options items="${types}" />
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button class="button" type="submit" name="submit"
                                value="Filter">
                            <spring:message code="lang.filter" text="Filter" />
                        </button>
                    </td>
                    <td>
                        <button class="button" type="submit" name="void" 
                                value="Void filter">
                            <spring:message code="lang.voidFilter" text="Void filter" />
                        </button>
                    </td>
                </tr>
            </table>
        </form:form>
        <br /><br />
    </c:if>
    <c:if test="${empty users}">
        <spring:message code="lang.noUsers" text="Actions" /><br />
    </c:if>
    
    <c:if test="${not empty users}">
        <table id="standardTable">
            <thead>
                <tr>
                    <th><spring:message code="lang.id" text="ID" /></th>
                    <th><spring:message code="lang.firstName" text="First Name" /></th>
                    <th><spring:message code="lang.lastName" text="Last Name" /></th>
                    <th><spring:message code="lang.type" text="Type" /></th>
                    <th><spring:message code="lang.actions" text="Actions" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.type.typeLabel}</td>
                        <td><a href="<c:url value="/user/detail/${user.id}"/>">
                                <img alt="detail" src="<c:url value="/images/search.png"/>"></a>
                            <a href="<c:url value="/user/delete/${user.id}"/>">
                                <img alt="delete" src="<c:url value="/images/delete.png" />"></a>
                            <a href="<c:url value="/user/update/${user.id}"/>">
                                <img alt="update" src="<c:url value="/images/update.png" />"></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <th><spring:message code="lang.id" text="ID" /></th>
                    <th><spring:message code="lang.firstName" text="First Name" /></th>
                    <th><spring:message code="lang.lastName" text="Last Name" /></th>
                    <th><spring:message code="lang.type" text="Type" /></th>
                    <th><spring:message code="lang.actions" text="Actions" /></th>
                </tr>
            </tfoot>
        </table>            
    </c:if>    
    <div class="buttonPosition">
        <button type="button" id="newUserButton" class="button"
                onclick="javascript:showPart('addUserWrapper');hidePart('newUserButton')">
                <spring:message code="lang.buttonAddUser" text="New User" />
        </button>
    </div>
        
    <ul id="addUserWrapper" class="offscreen">
        <form:form method="post" id="addUserForm" name="addUserForm"
                action="add">
            <table>
                <tr>
                    <td><form:label path="firstName">
                        <spring:message code="lang.firstName" text="First Name" />
                        </form:label></td>
                    <td><form:input cssClass="inputField" path="firstName" /></td>
                    <td><label id="userFormFirstNameWarning"
                            class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
                <tr>
                    <td><form:label path="lastName">
                        <spring:message code="lang.lastName" text="Last Name" />
                        </form:label></td>
                    <td><form:input cssClass="inputField" path="lastName" /></td>
                    <td><label id="userFormLastNameWarning"
                            class="offscreen warningMessage">
                        <spring:message code="lang.mandatory" text="Mandatory Field" />
                        </label></td>
                </tr>
                <tr>
                    <td><form:label path="type">
                        <spring:message code="lang.type" text="Type" />
                        </form:label></td>
                    <td><form:select path="type" items="${types}" /></td>
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
    <c:if test="${deleteStatus}">
        <div class="success" id="userSuccessWindow">
            <p>
                <spring:message code="lang.userDeleteSuccess"
                                text="User successfully deleted" />
            </p>
        </div>
    </c:if>

    <c:if test="${deleteStatus == 'false'}">
        <div class="error" id="userErrorWindow">
            <p>
                <spring:message code="lang.userDeleteeFailure"
                                text="Error occurred during deleting user" />
            </p>
            <p>${errorMessage}</p>
        </div>
    </c:if>
        
</div>

<jsp:include page="parts/footer.jsp"></jsp:include>