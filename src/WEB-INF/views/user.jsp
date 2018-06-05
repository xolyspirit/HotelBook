<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${userTitle}</title>
</head>
<body>
<style type="text/css">
    TABLE {
        width: 300px; /* Ширина таблицы */
        border-collapse: collapse; /* Убираем двойные линии между ячейками */
    }
    TD, TH {
        padding: 3px; /* Поля вокруг содержимого таблицы */
        border: 1px solid black; /* Параметры рамки */
    }
    TH {
        background: #b0e0e6; /* Цвет фона */
    }
</style>

<%
    String userTitle = (String) request.getAttribute("userTitle");
    String answer = (String) request.getAttribute("answer");
    String orders = (String) session.getAttribute("orders");
    String answer2 = (String) request.getAttribute("answer2");
    String room = (String) request.getAttribute("room");
    String arrivalDate = (String) request.getAttribute("arrivalDate");
    String days = (String) request.getAttribute("days");
    String order = (String) request.getAttribute("order");
    String showAllMyOrders = (String) request.getAttribute("showAllMyOrders");
    String show = (String) request.getAttribute("show");
    String logOut = (String) request.getAttribute("logOut");
    String greeting = (String) request.getAttribute("greeting");
%>
<H3>${greeting}</H3>
<form name= "order" action="order" method="POST">
    <p><strong>${room}:</strong><br>
        <input type="text" name="room"><br>
    <p><strong>${arrivalDate}:</strong><br>
        <input type= "date" name="date"><br>
    <p><strong>${days}:</strong><br>
        <input type= "text" name="days"><br>
        <button>${order}</button>
</form>

<h3>
    ${answer}
</h3>

<form name= "showmyorders" action="showmyorders" method="POST">
    <p><strong>${showAllMyOrders}</strong><br>
        <button>${show}</button>
</form>

${orders}

<form name="logout" action="logout" method="post">
    <button>${logOut}</button>
</form>


</body>
</html>
