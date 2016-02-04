<%--
  Created by IntelliJ IDEA.
  User: Вероника
  Date: 04.02.2016
  Time: 13:16
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

</body>
</html>
