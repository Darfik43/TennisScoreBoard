<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<c:if test="${not empty currentMatch}">
    <table border="1">
        <tr>
            <th>Player</th>
            <th>Set Score</th>
            <th>Game Score</th>
            <th>Tiebreak Score</th>
            <th>Actions</th>
        </tr>
        <tr>
            <td>${currentMatch.getPlayer1().getName()}</td>
            <td>${currentMatch.getScore().get(currentMatch.getPlayer1().getName())}</td>
            <td>${currentMatch.getGameScore().get(currentMatch.getPlayer1().getName())}</td>
            <td>${currentMatch.getTieBreakScore().get(currentMatch.getPlayer1().getName())}</td>
            <td><a href="${request.getContextPath()}/increaseScore?matchId=${currentMatch.getId()}&playerId=${currentMatch.getPlayer1().getId()}">Increase Score</a></td>
        </tr>
        <tr>
            <td>${currentMatch.getPlayer2().getName()}</td>
            <td>${currentMatch.getScore().get(currentMatch.getPlayer2().getName())}</td>
            <td>${currentMatch.getGameScore().get(currentMatch.getPlayer2().getName())}</td>
            <td>${currentMatch.getTieBreakScore().get(currentMatch.getPlayer2().getName())}</td>
            <td><a href="${request.getContextPath()}/increaseScore?matchId=${currentMatch.getId()}&playerId=${currentMatch.getPlayer2().getId()}">Increase Score</a></td>
        </tr>
    </table>
</c:if>

</body>
</html>
