<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization.restaurant" var="lang"/>

<div class="inner">
    <form>
        <span>&copy; <a href = "https://www.linkedin.com/in/%D0%B2%D0%B5%D1%80%D0%BE%D0%BD%D0%B8%D0%BA%D0%B0-%D1%87%D0%B8%D0%B3%D0%B8%D1%80-4a178a105"><fmt:message key="footer.name" bundle="${lang}"/></a> 2016 </span>
        <div class="form-group">
            <label for="option-1">
                <input type="radio" id="option-1" onchange="submit()" name="language" value="en" ${language == 'en' ? 'checked' : ''}>
                <span>EN</span>
            </label>
            <label  for="option-2">
                <input type="radio" id="option-2" onchange="submit()" name="language" value="ru" ${language == 'ru' ? 'checked' : ''}>
                <span>RU</span>
            </label>
        </div>
    </form>
</div>