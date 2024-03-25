<%@ page import="java.util.Map" %>
<%@ page import="java.util.UUID" %>
<%@ page import="com.tennisscoreboard.service.scorecalculation.MatchManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tennis Match Score</title>
</head>
<body>
<h1>Tennis Match Score</h1>

<%
    UUID matchId = (UUID) request.getSession().getAttribute("matchId");
    String player1Name = (String) request.getAttribute("player1Name");
    String player2Name = (String) request.getAttribute("player2Name");
    MatchManager matchManager = (MatchManager) request.getSession().getAttribute("matchManager");
%>

<% if (matchId != null) { %>
<h2>Match Details</h2>
<p>Player 1: <%= player1Name %></p>
<p>Player 2: <%= player2Name %></p>
<% } %>

<h2>Current Score</h2>
<% if (matchManager != null) { %>
<p>Player 1: <%= player1Name %> - MatchScore: <%= matchManager.getMatchScore() %> Set Score: <%= matchManager.getSetScore() %> GameScore: <%= matchManager.getGameScore() %> TieBreakScore: <%= matchManager.getTieBreakScore() %> </p>
<p>Player 2: <%= player2Name %> - MatchScore: <%= matchManager.getMatchScore() %> Set Score: <%= matchManager.getSetScore() %> GameScore: <%= matchManager.getGameScore() %> TieBreakScore: <%= matchManager.getTieBreakScore() %> </p>
<% } %>

<form action="increaseScore?matchId=<%= matchId %>" method="post">
    <input type="hidden" name="matchId" value="<%= request.getSession().getAttribute("matchId") %>">
    <button type="submit" name="action" value="player1">Player 1 Won</button>
</form>

<form action="increaseScore?matchId=<%= matchId %>" method="post">
    <input type="hidden" name="matchId" value="<%= request.getSession().getAttribute("matchId") %>">
    <button type="submit" name="action" value="player2">Player 2 Won</button>
</form>

</body>
</html>
