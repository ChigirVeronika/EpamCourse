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
<body onkeydown="return Disable()">
<div class="site-wrapper">
    <div class="site-wrapper-inner">
        <div class="cover-container">
            <div class="trendhead clearfix">
                <jsp:include page="layout/header.jsp" />
            </div>

            <div class="inner cover">
                <h1 class="cover-heading">
                    <br>
                    <fmt:message key="hello.welcome" bundle="${lang}"/><c:out value="${user.login}"/>
                </h1>
            </div>

            <c:if test="${user != null && user.role == 'USER'}">
                <p class="lead"><fmt:message key="common.take" bundle="${lang}"/></p>
            </c:if>

            <p class="lead">
                <a href="/main?command=menu_command" class="btn btn-lg btn-default">
                    <fmt:message key="index.start" bundle="${lang}"/>
                </a>
            </p>


        </div>
    </div>
</div>

</body>
</html>
