<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${adminTitle}</title>
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
    String adminTitle = (String) request.getAttribute("adminTitle");
    String orders = (String) request.getAttribute("orders");
    String showAllOrders = (String) request.getAttribute("showMyOrders");
    String logOut = (String) request.getAttribute("logOut");
%>
<form action="showallorders" method="post">
    <button>${showAllOrders}</button>
</form>

${orders}

<form name="logout" action="logout" method="post">
    <button>${logOut}</button>
</form>
</body>
</html>
