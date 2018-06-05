<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${indexTitle}</title>
</head>
<body>
<%
    String answer = (String) request.getAttribute("answer");
    String submit = (String) request.getAttribute("submit");
    String login = (String) request.getAttribute("login");
    String password = (String) request.getAttribute("password");
    String logIn = (String) request.getAttribute("logIn");
    String indexTitle = (String) request.getAttribute("indexTitle");
    String choose = (String) request.getAttribute("choose");

%>
<form name= "locale" action="locale" method="POST">
    <H2>${choose}</H2>
    <p><strong>English </strong><br>
    <input type="radio" id="choice1" name="language" value="en" checked ><br>
    <p><strong>Русский </strong><br>
    <input type="radio" id="choice2" name="language" value="ru"><br>
        <button>${submit}</button><br>
</form>
<form name= "Authorization" action="authorization" method="POST">
    <p><strong>${login}: </strong><br>
        <input type="text" name="login"><br>
    <p><strong>${password}:</strong><br>
        <input type="password" name="password"><br>
        <button>${logIn}</button><br>
</form>
<h3>
    ${answer}
</h3>
</body>
</html>
