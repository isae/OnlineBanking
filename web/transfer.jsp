<%--
  Created by IntelliJ IDEA.
  User: Xottab
  Date: 11.07.13
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Перевод денег между счетами</title>
</head>     <form action=request method=POST>
    <input type="hidden" name="type" value="toMain">
    <input type=submit value="На главную">
</form>
<form action=request method=POST>
    Sender:<input type=text size=20 name=param1><br>
    Reciever:<input type=text size=20 name=param2><br>
    Password:<input type=text size=20 name=param3><br>
    <input type="hidden" name="type" value="Transfer">
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
<body>

</body>
</html>