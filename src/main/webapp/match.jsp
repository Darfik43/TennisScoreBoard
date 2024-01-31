<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tennisscoreboard.model.Match" %>
<%@ page import="com.tennisscoreboard.model.Player" %>

<%
    Match currentMatch = (Match) request.getSession().getAttribute("currentMatch");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tennis Scoreboard</title>
</head>
<body>

<h2>Tennis Scoreboard</h2>

<%
    if (currentMatch != null && !currentMatch.isEmpty()) {
%>
<table border="1">
    <tr>
        <th>Player</th>
        <th>Set Score</th>
        <th>Game Score</th>
        <th>Tiebreak Score</th>
        <th>Actions</th>
    </tr>
    <tr>
        <td><%= currentMatch.getPlayer1().getName() %></td>
        <td><%= currentMatch.getSetScore().get(currentMatch.getPlayer1()) %></td>
        <td><%= currentMatch.getGameScore().get(currentMatch.getPlayer1()) %></td>
        <td><%= currentMatch.getTieBreakScore().get(currentMatch.getPlayer1()) %></td>
        <td><a href="/increaseScore?matchId=<%= currentMatch.getId() %>&playerId=<%= currentMatch.getPlayer1().getId() %>">Increase Score</a></td>
    </tr>
    <tr>
        <td><%= currentMatch.getPlayer2().getName() %></td>
        <td><%= currentMatch.getSetScore().get(currentMatch.getPlayer2()) %></td>
        <td><%= currentMatch.getGameScore().get(currentMatch.getPlayer2()) %></td>
        <td><%= currentMatch.getTieBreakScore().get(currentMatch.getPlayer2()) %></td>
        <td><a href="/increaseScore?matchId=<%= currentMatch.getId() %>&playerId=<%= currentMatch.getPlayer2().getId() %>">Increase Score</a></td>
    </tr>
</table>
<%
    }
%>

</body>
</html>