<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="left">
    <h2><spring:message
            code="lang.leftMenuTitle" text="Menu" /></a></h2>
	<a href="<c:url value="/machine/list"/>"><spring:message
			code="lang.listMachines" text="List of machines" /></a>
</div>