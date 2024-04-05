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
<form action="pastMatches" method="get">
    <label>
        <input type="text" name="playerName" placeholder="Введите имя игрока">
    </label>
    <input type="submit" value="Поиск">
</form>
<table>
    <tr>
        <th>Игрок 1</th>
        <th>Игрок 2</th>
        <th>Победитель</th>
    </tr>
    <% List<Match> matches = (List<Match>) request.getAttribute("currentPageMatches");
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

    <% int currentPage =  (Integer) (request.getAttribute("page")); %>
    <% if (currentPage > 1) { %>
    <a href="?page=<%= currentPage - 1 %>">Предыдущая страница</a>
    <% } %>

    <% if (currentPage < (Integer) (request.getAttribute("totalPages"))) { %>
    <a href="?page=<%= currentPage + 1 %>">Следующая страница</a>
    <% } %>
</table>
</body>
</html>
