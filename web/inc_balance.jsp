<%--
  Created by IntelliJ IDEA.
  User: Xottab
  Date: 11.07.13
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление денег на счёт</title>
</head>
<body>
<form action=request method=POST>
    <input type="hidden" name="type" value="toMain">
    <input type=submit value="На главную">
</form>
<form action=request method=POST>
    Username:<input type=text size=20 name=param1><br>
    Sum:<input type=text size=20 name=param2><br>
    <input type="hidden" name="type" value="IncBalance">
    <input type=submit>
</form>
<%
    if(session.getAttribute("ready")==null){
        session.setAttribute("ready", false);
    }
    if ((Boolean)session.getAttribute("ready")) {
        out.println(session.getAttribute("result"));
        session.setAttribute("ready", false);
    }%>
</body>
</html>