<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.restaurant" var="lang"/>

<html lang="${language}">
<head>
    <jsp:include page="layout/resources.jsp" />
</head>

<body>
<div class="restaurant-wrapper">
    <div class="restaurant-wrapper-inner">
        <div class="cover-container">
            <div>
                <jsp:include page="layout/header.jsp"/>
            </div>

            <div>

            </div>

            <div class="trendfoot">
                <jsp:include page="layout/footer.jsp"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>

