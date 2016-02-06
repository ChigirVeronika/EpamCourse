<%--
  Created by IntelliJ IDEA.
  User: Вероника
  Date: 05.02.2016
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <c:forEach var="i" items = "${dish}">
        <tr>
            <td>${i}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
