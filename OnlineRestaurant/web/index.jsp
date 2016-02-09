<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
                        <h1 class="cover-heading"><fmt:message key="index.welcome" bundle="${lang}"/></h1>
                        <p class="lead"><fmt:message key="lorem.short" bundle="${lang}"/></p>
                        <form action="/main" method="POST">
                            <input type="hidden" name="command" value="dishes_command"/>
                            <input type="hidden" name="filename" value="myfile"/>
                            <input type="submit" value="<fmt:message key="index.start" bundle="${lang}"/>" class="btn btn-default btn-block"/>
                        </form>
                    </div>

                    <div class="trendfoot">
                        <jsp:include page="layout/footer.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
