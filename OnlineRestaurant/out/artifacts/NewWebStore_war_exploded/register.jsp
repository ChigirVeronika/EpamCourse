<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
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
                <form action="/main" method="POST">
                    <input type="hidden" name="command" value="register_command"/>

                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-5">
                            <input type="text" name="name" class="form-control" placeholder="<fmt:message key="register.name" bundle="${lang}"/>"/>
                        </div>
                        <div class="col-md-4">
                            <c:if test="${name_message}!=nul">
                                <h4><span class="label label-danger">
                                    <c:out value="$name_message"/>
                                </span></h4>
                            </c:if>
                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-5">
                            <input type="text" name="surname" class="form-control" placeholder="<fmt:message key="register.surname" bundle="${lang}"/>"/>
                        </div>
                        <div class="col-md-4">
                            <c:if test="${surname_message}!=nul">
                                <h4><span class="label label-danger">
                                    <c:out value="$surname_message"/>
                                </span></h4>
                            </c:if>
                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-5">
                            <input type="text" name="email" class="form-control" placeholder="<fmt:message key="register.email" bundle="${lang}"/>"/>
                        </div>
                        <div class="col-md-4">
                            <c:if test="${email_message}!=nul">
                                <h4><span class="label label-danger">
                                    <c:out value="$email_message"/>
                                </span></h4>
                            </c:if>
                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-5">
                            <input type="text" name="pay_card_id" class="form-control" placeholder="<fmt:message key="register.pay_card" bundle="${lang}"/>"/>
                        </div>
                        <div class="col-md-4">
                            <c:if test="${pay_card_message}!=nul">
                                <h4><span class="label label-danger">
                                    <c:out value="$pay_card_message"/>
                                </span></h4>
                            </c:if>
                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-5">
                            <input type="text" name="login" class="form-control" placeholder="<fmt:message key="register.login" bundle="${lang}"/>"/>
                        </div>
                        <div class="col-md-4">
                            <c:if test="${login_message}!=nul">
                                <h4><span class="label label-danger">
                                    <c:out value="$login_message"/>
                                </span></h4>
                            </c:if>
                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-5">
                            <input type="password" name="password" class="form-control" placeholder="<fmt:message key="register.password" bundle="${lang}"/>"/>
                        </div>
                        <div class="col-md-4">
                            <c:if test="${password_message}!=nul">
                                <h4><span class="label label-danger">
                                    <c:out value="$password_message"/>
                                </span></h4>
                            </c:if>
                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-lg-4"></div>
                        <div class="col-md-3">
                            <input type="submit" value="<fmt:message key="register.signup" bundle="${lang}"/>" class="btn btn-default btn-block"/>
                        </div>
                    </div>
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

