<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        <%@include file="/WEB-INF/css/editpart.css"%>
    </style>
    <title>Edit</title>
</head>
<body>
<div class="container">
    <div class="flex1">
        <header>
            Редактирование значений
        </header>
        <form action="/edit" method="POST">
            <div class="titles">
                <div class="name">Нименование</div>
                <div class="count">Количество</div>
                <div class="need1">Необходимость</div>
            </div>
            <div class="ed">
                <input type="hidden" name="id" value="${part.id}">
                <div class="name"><input autofocus type="text" name="name" id="name" value="${part.name}" size="45" required></div>
                <div class="count"><input type="number" name="quantity" id="quantity" value="${part.quantity}" size="4"
                                          min="0" max="9999" required>
                </div>
                <div class="need1">
                    <c:if test="${part.need}">
                        <input type="checkbox" checked name="need" id="need" style="width:20px;height:20px;">
                    </c:if>
                    <c:if test="${!part.need}">
                        <input type="checkbox" name="need" id="need" style="width:20px;height:20px;">
                    </c:if>
                </div>
            </div>
            <div class="save">
                <div><input class="btn" type="submit" value=" Сохранить " style="width:150px;height:25px;"></div>
            </div>
        </form>
        <form action="/back" method="get">
            <div class="save"><input class="btn" type="submit" value=" Отмена " style="width:150px;height:25px;"></div>
        </form>

        <div class="error">
            <c:out value="${error}"/>
        </div>
    </div>
    <footer>
    </footer>
</div>

</body>
</html>