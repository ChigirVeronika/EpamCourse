<%--
  Created by IntelliJ IDEA.
  User: Вероника
  Date: 24.01.2016
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization.restaurant" var="lang"/>

<html lang="${language}">
    <head>
        <jsp:include page="layout/resources.jsp" />
    </head>

    <body>
        Main page of future restaurant!
        <div class="restaurant-wrapper">
            <div class="restaurant-wrapper-inner">
                <div class="cover-container">
                    <div>
                        <jsp:include page="layout/header.jsp"/>
                    </div>

                    <div>
                        <h1 class=""><fmt:message key=""/></h1>
                    </div>

                    <div class="trendfoot">
                        <jsp:include page="layout/footer.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>