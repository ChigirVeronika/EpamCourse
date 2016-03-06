<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.restaurant" var="lang"/>

<div class="modal fade" id="add" role="dialog">
    <form id="add_category" action="/main" method="POST">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <input type="text" name="name" class="form-control" placeholder="<fmt:message key="catalog.add.name" bundle="${lang}"/>"/><span id="categoryName-msg"></span>
                    <br>
                    <textarea class="form-control" name="description" id="message-text" placeholder="<fmt:message key="catalog.add.description" bundle="${lang}"/>"></textarea><span id="categoryDescription-msg"></span>
                    <input type="hidden" name="command" value="add_category_command"/>
                    <button type="submit" class="btn btn-default" onlick="return validateForm()"><fmt:message key="catalog.add.save" bundle="${lang}"/></button>
                </div>
            </div>
        </div>
    </form>
</div>