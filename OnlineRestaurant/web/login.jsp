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
                <form action="/main" method="POST">
                    <input type="hidden" name="command" value="login_command"/>
                    <c:if test="${message ne null}">
                        <div class="row"><h4><span class="label label-danger">
							<c:out value='${message}'/>
						</span></h4></div>
                        <br />
                    </c:if>
                    <c:if test="${sessionMessage ne null}">
                        <div class="row"><h4><span class="label label-danger">
							<c:out value='${sessionMessage}'/>
						</span></h4></div>
                        <br />
                    </c:if>

                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-5">
                            <input type="text" name="login" class="form-control" placeholder="<fmt:message key="login.login" bundle="${lang}"/>"/>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-5">
                            <input type="password" name="password" class="form-control" placeholder="<fmt:message key="login.password" bundle="${lang}"/>"/>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-2">
                            <input type="submit" value="<fmt:message key="login.signin" bundle="${lang}"/>" class="btn btn-default btn-block"/>
                        </div>
                        <div class="col-md-3">
                            <a href="register.jsp" class="btn btn-default btn-block"><fmt:message key="login.signup" bundle="${lang}"/></a>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                    <br>
                </form>
            </div>

            <div class="trendfoot">
                <jsp:include page="layout/footer.jsp" />
            </div>

        </div>
    </div>
</div>
</body>

</html>
