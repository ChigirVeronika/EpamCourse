<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
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
<div class="restaurant-wrapper">
    <div class="restaurant-wrapper-inner">
        <div class="cover-container">
            <div class="trendhead clearfix">
                <jsp:include page="layout/header.jsp"/>
            </div>

            <div class="inner cover">
                <div class = "container-fluid">
                    <div class="row">
                        <div class="col-sm-4"></div>
                        <div class="col-sm-8">
                            <h1 class="cover-heading">
                                <c:out value="${dish.name}"/><br>
                            </h1>
                        </div>
                    </div>
                    <div class = "row">
                        <div class="col-sm-4"><
                            <mytag:categories/>
                        </div>
                        <div class = "col-sm-8">
                            <div class="row">
                                <div class = "col-xs-7">
                                    <img src = "images/<c:out value="${dish.image}" />" alt = "image" height="150"/>
                                    <input type="hidden" id="dish_image" value="<c:out value="${dish.image}"/>">
                                </div>
                                <div class = "col-xs-5">
                                    <p><fmt:message key="dish.name" bundle="${lang}"/>: <c:out value="${dish.name}"/> </p>
                                    <input type="hidden" id="dish_name" value="<c:out value="${dish.name}"/>">
                                    <p><fmt:message key="dish.name" bundle="${lang}"/>: <em> <c:out value="${dish.name}"/> </p>
                                    <input type="hidden" id="dish_name" value="<c:out value="${dish.name}"/>">
                                    <p><fmt:message key="dish.price" bundle="${lang}"/>: $  <c:out value="${dish.price}"/> </p>
                                    <input type="hidden" id="dish_price" value="<c:out value="${dish.price}"/>">
                                    <br/>
                                    <c:if test="${user != null && user.role == 'ADMIN'}">
                                        <div class="row">
                                            <div class="col-md-4"></div>
                                            <div class="col-md-8">
                                                <button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#editDish">
                                                    <fmt:message key="dish.edit.button" bundle="${lang}"/>
                                                </button>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${user != null && user.role == 'USER'}" >
                                        <form method = "POST" action = "/main">
                                            <input type="hidden" name="command" value="add_to_order_command" />
                                            <input type="hidden" name="dish_id" value="<c:out value="${dish.id}"/>" />
                                            <input type = "submit" value = "<fmt:message key="dish.order" bundle="${lang}"/>" name = "submit" alt = "Add To Order" class="btn btn-default"/>
                                        </form>
                                    </c:if>


                                    <br/>
                                    <br/>
                                </div>
                            </div>

                            <div class="row">
                                <h3><fmt:message key="dish.description" bundle="${lang}"/>:</h3>
                                <p> <c:out value="${dish.description	}"/></p>
                                <input type="hidden" id="dish_description" value="<c:out value="${dish.description}"/>">
                            </div>

                            <c:if test="${user != null && user.role == 'ADMIN'}">
                                <jsp:include page="add_category_modal.jsp"/>
                                <jsp:include page="edit_category_modal.jsp"/>


                                <div class = "row">

                                    <div class="modal fade" id="editDish" role="dialog">

                                        <div class="modal-dialog">

                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                </div>

                                                <div class="modal-body">
                                                    <form action="/main" method="POST">
                                                        <input type="hidden" name="dish_id" value="<c:out value="${dish.id}" />" >
                                                        <input type="text" name="name" class="name form-control" placeholder="<fmt:message key="dish.add.name" bundle="${lang}"/>"/>
                                                        <br>
                                                        <input type="text" name="category" class="brand form-control" placeholder="<fmt:message key="dish.add.category" bundle="${lang}"/>"/>
                                                        <br>
                                                        <input type="text" name="quantity" class="brand form-control" placeholder="<fmt:message key="dish.add.quantity" bundle="${lang}"/>"/>
                                                        <br>
                                                        <input type="text" name="price" class="price form-control" placeholder="<fmt:message key="dish.add.price" bundle="${lang}"/>"/>
                                                        <br>
                                                        <input type="text" name="image" class="image form-control" placeholder="<fmt:message key="dish.add.image" bundle="${lang}"/>"/>
                                                        <br>
                                                        <textarea class="description form-control" name="ingredients" id="message-text-ingredients" placeholder="<fmt:message key="dish.add.ingredients" bundle="${lang}"/>"></textarea>
                                                        <br>
                                                        <textarea class="description form-control" name="description" id="message-text-description" placeholder="<fmt:message key="dish.add.description" bundle="${lang}"/>"></textarea>
                                                        <input type = "hidden" name = "command" value="edit_dish_command"/>
                                                        <button type="submit" class="btn btn-default"><fmt:message key="dish.add.save" bundle="${lang}"/></button>
                                                    </form>

                                                    <form action="/main" method="post">
                                                        <input type="hidden" name="dish_id" value="<c:out value="${dish.id}" />" >
                                                        <input type="hidden" name="command" value="delete_dish_command"/>
                                                        <button type="submit" class="btn btn-default"><fmt:message key="dish.del.button" bundle="${lang}"/></button>
                                                    </form>
                                                </div>

                                            </div>

                                        </div>



                                        <script type="text/javascript">
                                            $('#editDish').on('show.bs.modal', function (event) {
                                                //var button = $(event.relatedTarget);
                                                var name = $("#dish_name").val();
                                                var category = $("#dish_category").val();
                                                var quantity = $("#dish_quantity").val();
                                                var price = $("#dish_price").val();
                                                var ingredients = $("#dish_ingredients").val();
                                                var description = $("#dish_description").val();
                                                var image = $("#dish_image").val();
                                                //var desc = button.data('description');

                                                var modal = $(this);

                                                modal.find('.name').val(name);
                                                modal.find('.category').val(category);
                                                modal.find('.quantity').val(quantity);
                                                modal.find('.price').val(price);
                                                modal.find('.ingredients').val(ingredients);
                                                modal.find('.description').val(description);
                                                modal.find('.image').val(image);
                                                //odal.find('.old_name').val(name);
                                                //modal.find('.del_name').val(name);
                                                //modal.find('.description').val(desc);
                                            });
                                        </script>
                                    </div>
                                </div>
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>

            <br><br><br><br>
            <div class="trendfoot">
                <jsp:include page="layout/footer.jsp"/>
            </div>
        </div>
    </div>
</div>

</body>
</html>
