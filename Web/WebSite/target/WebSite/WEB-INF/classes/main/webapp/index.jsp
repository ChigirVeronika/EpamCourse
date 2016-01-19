<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>TITLE</title>
</head>

<body>
    <h5>Visits</h5>
    <%=(request.getAttribute("current_count") == null ? "Server is busy. Come later." : request.getAttribute("current_count"))%>

    <hr>
    <form action="NumberButtonController" method="post">
        <input type="submit" name="Generate number">
    </form>
    <%=(request.getAttribute("random_number") == null ? "0" : request.getAttribute("random_number"))%>

</body>
</html>
