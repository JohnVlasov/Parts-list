<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
    <style>
        <%@include file="/WEB-INF/css/editpart.css"%>
    </style>
</head>

<body>
<div class="container">
    <div class="flex1">
        <header>
            Добавление нового элемента
        </header>
        <form action="/add" method="POST">
            <div class="titles">
                <div class="name">Нименование</div>
                <div class="count">Количество</div>
                <div class="need1">Необходимость</div>
            </div>
            <div class="ed">
                <div class="name"><input autofocus type="text" name="name" id="name" size="45" required></div>
                <div class="count"><input type="number" name="quantity" id="quantity" size="4" min="0" max="9999" required></div>
                <div class="need1">
                    <input type="checkbox" name="need" id="need" style="width:20px;height:20px;">
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