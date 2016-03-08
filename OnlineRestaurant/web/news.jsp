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
<div class="site-wrapper">
    <div class="site-wrapper-inner">
        <div class="cover-container">
            <div class="trendhead clearfix">
                <jsp:include page="layout/header.jsp" />
            </div>

            <div class="inner cover">
            <c:if test="${user != null && user.role == 'ADMIN'}">
            <button type="submit" class="btn btn-default" data-toggle="modal" data-target="#addNews">
                <fmt:message key="news.add" bundle="${lang}"/>
            </button>
            <div class="modal fade" id="addNews" role="dialog">
                <form name="AddNews" action="/main" method="POST" onsubmit="return addNewsFormValidation()">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <input type="text" name="name" class="form-control" placeholder="<fmt:message key="dish.add.name" bundle="${lang}"/>"/>
                                <span class="lable label-danger"  id="newsName-msg"></span>
                                <br>
                                <input type="text" name="image" class="form-control" placeholder="<fmt:message key="dish.add.image" bundle="${lang}"/>"/>
                                <span class="lable label-danger"  id="newsImage-msg"></span>
                                <br>
                                <textarea class="form-control" name="content" id="message-text" placeholder="<fmt:message key="dish.add.description" bundle="${lang}"/>"></textarea>
                                <span class="lable label-danger" id="newsContent-msg"></span>
                                <input type="hidden" name="command" value="add_news_command"/>
                                <button type="submit" class="btn btn-default"><fmt:message key="news.add" bundle="${lang}"/></button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            </c:if>

                <table border id="results">
                    <c:forEach var="one_news" items = "${newsList}">
                        <tr>
                            <td>${one_news.getName()}</td><td rowspan="3">${one_news.getContent()}</td>
                        </tr>
                        <tr>
                            <td>${one_news.getDate()}</td>
                        </tr>
                        <tr>
                            <td><img src="images/${one_news.getImage()}" height="100"/></td>
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

