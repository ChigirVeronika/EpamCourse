<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.webstore" var="lang"/>

<div class="modal fade" id="edit" role="dialog">

    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
                <form action="/main" method="POST">
                    <input type="text" name="name" class="name form-control" placeholder="<fmt:message key="catalog.add.name" bundle="${lang}"/>"/>
                    <br>
                    <textarea class="description form-control" name="description" id="message-text-edit" placeholder="<fmt:message key="catalog.add.description" bundle="${lang}"/>"></textarea>
                    <input type = "hidden" name = "action" value="edit_category_command"/>
                    <input class="old_name" type="hidden" name="old_name"/>
                    <button type="submit" class="btn btn-default"><fmt:message key="dish.edit.button" bundle="${lang}"/></button>
                </form>
                <form action="/main" method="POST">
                    <input type="hidden" name="command" value="delete_category_command"/>
                    <input class="del_name" type="hidden" name="name"/>;
                    <button type="submit" class="btn btn-default"><fmt:message key="dish.del.button" bundle="${lang}"/></button>
                </form>
            </div>


        </div>

    </div>

</div>



<script type="text/javascript">
    $('#edit').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var name = button.data('name');
        var desc = button.data('description');

        var modal = $(this);

        modal.find('.name').val(name);
        modal.find('.old_name').val(name);
        modal.find('.del_name').val(name);
        modal.find('.description').val(desc);
    });
</script>