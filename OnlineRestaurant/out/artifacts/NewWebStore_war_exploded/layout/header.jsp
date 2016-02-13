<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.restaurant" var="lang"/>

<div class="inner">
    <h3 class="trendhead-brand">
        <fmt:message key="header.title" bundle="${lang}"/>
    </h3>
    <nav>
        <ul class="nav trendhead-nav">
            <li><a href="../index.jsp"><fmt:message key="header.home" bundle="${lang}"/></a> </li>

            <li><a href="/main?command=dishes_command"><fmt:message key="header.catalog" bundle="${lang}"/></a></li>


            <c:if test="${user == null}">
                <li><a href="../login.jsp"><fmt:message key="header.login" bundle="${lang}"/></a></li>
            </c:if>

            <c:if test="${user != null}">
                <li><a href="/main?command=logout_command"><fmt:message key="header.logout" bundle="${lang}"/></a></li>
            </c:if>

        </ul>
    </nav>
</div>