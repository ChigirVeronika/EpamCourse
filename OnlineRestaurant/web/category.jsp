<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/taglib/taglib.tld" %>

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
                <div class = "container-fluid">
                    <div class="row">
                        <div class="col-md-4"></div>
                        <div class="col-md-8">
                            <h1 class="cover-heading">
                                <c:out value="${name}"/>
                            </h1>
                        </div>
                    </div>

                    <div class = "row">
                        <div class="col-sm-4">
                            <mytag:categories/>
                        </div>
                        <div class="col-sm-8">
                            <mytag:category/>
                        </div>
                    </div>

                    <c:if test="${user!=null && user.role == 'ADMIN'}">
                        <jsp:include page="add_category_modal.jsp"/>
                        <jsp:include page="edit_category_modal.jsp"/>

                    <div class="row">
                        <div class="col-md-4"></div>
                        <div class="col-md-8">
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addProduct">
                                <fmt:message key="dish.add.button" bundle="${lang}"/>
                            </button>
                        </div>
                    </div>
                    <div class="modal fade" id="addProduct" role="dialog">
                        <form name="AddDish" action="/main" method="POST" onsubmit="addDishFormValidation()">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>

                                    <div class="modal-body">
                                        <input type="text" name="name" class="form-control" placeholder="<fmt:message key="dish.add.name" bundle="${lang}"/>"/>
                                        <span class="lable label-danger"  id="dishName-msg"></span>
                                        <br>
                                        <input type="text" name="price" class="form-control" placeholder="<fmt:message key="dish.add.price" bundle="${lang}"/>"/>
                                        <span class="lable label-danger"  id="dishPrice-msg"></span>
                                        <br>
                                        <input type="text" name="quantity" class="form-control" placeholder="<fmt:message key="dish.add.quantity" bundle="${lang}"/>"/>
                                        <span class="lable label-danger"  id="dishQuantity-msg"></span>
                                        <br>
                                        <input type="text" name="image" class="form-control" placeholder="<fmt:message key="dish.add.image" bundle="${lang}"/>"/>
                                        <span class="lable label-danger"  id="dishImage-msg"></span>
                                        <br>
                                        <textarea class="form-control" name="ingredients" id="ingredients-message-text" placeholder="<fmt:message key="dish.add.ingredients" bundle="${lang}"/>"></textarea>
                                        <span class="lable label-danger"  id="dishIngredients-msg"></span>
                                        <br>
                                        <textarea class="form-control" name="description" id="description-message-text" placeholder="<fmt:message key="dish.add.description" bundle="${lang}"/>"></textarea>
                                        <span class="lable label-danger"  id="dishDescription-msg"></span>
                                        <input type = "hidden" name = "command" value="add_dish_command"/>
                                        <input type = "hidden" name = "category" value="${name}"/>
                                        <button type="submit" class="btn btn-default"><fmt:message key="dish.add.save" bundle="${lang}"/></button>
                                    </div>
                                </div>

                            </div>
                        </form>
                    </div>
                    </c:if>

                </div>
            </div>
            <br><br>
            <div class="trendfoot">
                <jsp:include page="layout/footer.jsp"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
