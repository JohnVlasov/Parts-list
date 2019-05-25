<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <style>
        <%@include file="/WEB-INF/css/mainparts.css"%>
    </style>
    <title> Parts </title>
</head>


<body>
<div class="container">
    <div class="flex1">
        <header>
            Каталог комплектующих
        </header>
        <div class="titles">
            <div class="col0">id</div>
            <div class="col1">Нименование</div>
            <div class="col2">Необх-cть</div>
            <div class="col3">Кол-во</div>
            <div class="col4">Действие</div>
        </div>

        <c:forEach var="part" items="${partList}">

            <div class="parts">
                <div class="col0">${part.id}</div>
                <div class="col1">${part.name}</div>
                <div class="col2">
                    <c:if test="${part.need}">
                        Да
                    </c:if>
                    <c:if test="${!part.need}">
                        Нет
                    </c:if>
                </div>
                <div class="col3">${part.quantity}</div>
                <div class="col4">
                    <a href="/edit/${part.id}">edit</a>
                    <a href="/delete/${part.id}">delete</a>
                </div>
            </div>
        </c:forEach>
     </div>


    <div class="pc">
        <div class="pages">
            <div class="str">Страницы</div>
            <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
                <c:url value="/" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <div class="str"><a href="${url}">${i.index} </a></div>
            </c:forEach>
        </div>
        <div class="computers">
            Можно собрать ${countOfComputers} компьютеров
        </div>
    </div>

    <div class="filter">
        <div class="f1">
            Поиск
            <form method="POST" action="/search" enctype="application/x-www-form-urlencoded">
                <input autofocus type="search" ; class="search" ; name="searchName" ; placeholder="Введите строку для поиска" ;
                       value="${searchName}" ; size="40" ; style="height:25px" ;
                       formenctype="application/x-www-form-urlencoded">
                <input type="submit" class="btnSearch" value="Найти" style="width:45px;height:25px;">
            </form>
        </div>

        <div class="f2">
            Фильтр
            <form method="POST" action="/select">
                <select onchange="this.form.submit()" name="needSelect" class="select" style="height:25px">
                    <option value="all" ${needSelect == "all" ? "selected" : ""}>Все</option>
                    <option value="need" ${needSelect == "need" ? "selected" : ""}>Только необходимые</option>
                    <option value="notNeed" ${needSelect == "notNeed" ? "selected" : ""}>Только опциональные</option>
                </select>
            </form>
        </div>
    </div>

    <div class="add">
        <form method="get" action="/add">
            <button type="submit" class="btn" style="width:160px; height:25px;"> Добавить новую деталь</button>
        </form>
    </div>


    <footer>
        Текущая страница ${page}
    </footer>

</div>
</body>
</html>