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

                <table border id="results">
                    <c:forEach var="one_news" items = "${newsList}">
                        <tr>
                            <td>${one_news.name}</td><td rowspan="3">${one_news.content}</td>
                        </tr>
                        <tr>
                            <td>${one_news.date}</td>
                        </tr>
                        <tr>
                            <td><img src="images/${one_news.image}" alt="${one_news.name}" height="110" data-tooltip="DESCRIPTION"/></td>
                        </tr>
                        <br>
                    </c:forEach>
                </table>


            </div>

        </div>
    </div>
</div>

</body>
</html>

