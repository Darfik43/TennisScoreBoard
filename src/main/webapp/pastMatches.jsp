<%@ page import="com.tennisscoreboard.model.Match" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Прошедшие матчи</title>
</head>
<body>
<h1>Прошедшие матчи</h1>
<table>
    <tr>
        <th>Игрок 1</th>
        <th>Игрок 2</th>
        <th>Победитель</th>
    </tr>
    <% List<Match> matches = (List<Match>) request.getAttribute("results");
        if (matches != null) {
            for (Match match : matches) { %>
    <tr>
        <td><%= match.getPlayer1().getName() %></td>
        <td><%= match.getPlayer2().getName() %></td>
        <td><%= match.getWinner().getName() %></td>
    </tr>
    <% }
    } else { %>
    <tr>
        <td colspan="4">Нет прошедших матчей</td>
    </tr>
    <% } %>
</table>
</body>
</html>
