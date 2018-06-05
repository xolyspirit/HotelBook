<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${payTitle}</title>
</head>
<body>
<%
    String payTitle= (String) request.getAttribute("payTitle");
    String pay= (String) request.getAttribute("pay");
    Integer room = (Integer) request.getAttribute("room");
    Date date = (Date) request.getAttribute("date");
    Integer days = (Integer) request.getAttribute("days");
    Double cost =  (Double)(request.getAttribute("cost"));
    Integer order_id =  (Integer)(request.getAttribute("order_id"));
    String sroom= (String) request.getAttribute("sroom");
    String sdate= (String) request.getAttribute("sdate");
    String sdays= (String) request.getAttribute("sdays");
    String scost= (String) request.getAttribute("scost");
%>
<p><strong>${sroom}: ${room}</strong><br>
<p><strong>${sdate}: ${date}</strong><br>
<p><strong>${sdays}: ${days}</strong><br>
<p><strong>${scost}: ${cost}</strong><br>
<form name= "Paid" action="paid" method="POST">
    <input type="hidden" name="order_id"  value=${order_id}><br>
        <button>${pay}</button>
</form>
</body>
</html>
