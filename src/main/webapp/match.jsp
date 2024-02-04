        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tennis Match Score</title>
</head>
<body>
<h1>Tennis Match Score</h1>

<% com.tennisscoreboard.model.Match currentMatch = null;
    if (request.getAttribute("currentMatch") != null) {
        currentMatch = (com.tennisscoreboard.model.Match) request.getAttribute("currentMatch");
%>
<h2>Match Details</h2>
<p>Player 1: <%=currentMatch.getPlayer1().getName()%></p>
<p>Player 2: <%=currentMatch.getPlayer2().getName()%></p>
<% } %>
<%
    com.tennisscoreboard.service.scorecalculation.MatchManager matchManager = (com.tennisscoreboard.service.scorecalculation.MatchManager) request.getAttribute("score");
%>
<h2>Current Score</h2>
<p>Player 1: <%= matchManager != null ? matchManager.getPlayer1Name() + " - " + matchManager.getScore() : "" %></p>
<p>Player 1: <%= matchManager != null ? matchManager.getPlayer2Name() + " - " + matchManager.getScore() : "" %></p>

<form action="match-score" method="post">
    <!--<input type="hidden" name="uuid" value="<//%=currentMatch != null ? currentMatch.getUuid() : ""%>"> -->
    <button type="submit" name="action" value="player1">Player 1 Won</button>
    <button type="submit" name="action" value="player2">Player 2 Won</button>
</form>
</body>
</html>
