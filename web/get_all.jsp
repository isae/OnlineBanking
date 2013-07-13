<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Xottab
  Date: 11.07.13
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Получить список всех пользователей</title>
</head>
<body>
<form action=request method=POST>
    <input type="hidden" name="type" value="toMain">
    <input type=submit value="На главную">
</form>
<form action=request method=POST>
    <input type="hidden" name="type" value="GetAll">
    <input type=submit value="Получить список пользователей">
</form>
<%

    if ((session.getAttribute("error") == null)) {
        if (session.getAttribute("ready") == null) {
            session.setAttribute("ready", false);
        }
        if ((Boolean) session.getAttribute("ready")) {
            List<String> results = (List<String>) session.getAttribute("result");
            out.println("Всего " + results.size() + " пользователей:<br>");
            for (String s : results) {
                out.println(s+"<br>");
            }
            session.setAttribute("ready", false);
            session.setAttribute("result", null);
        }
    } else {
        out.println(session.getAttribute("error"));
    }
    session.setAttribute("error", null);
%>
</body>
</html>