<%@ page import="java.util.Map" %>
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
    com.tennisscoreboard.model.Match currentMatch = (com.tennisscoreboard.model.Match) request.getAttribute("currentMatch");
    String player1Name = (String) request.getAttribute("player1Name");
    String player2Name = (String) request.getAttribute("player2Name");
    Map<String, Integer> score = (Map<String, Integer>) request.getAttribute("score");
%>

<% if (currentMatch != null) { %>
<h2>Match Details</h2>
<p>Player 1: <%= player1Name %></p>
<p>Player 2: <%= player2Name %></p>
<% } %>

<h2>Current Score</h2>
<% if (score != null) { %>
<p>Player 1: <%= player1Name %> - <%= score.get(player1Name) %></p>
<p>Player 2: <%= player2Name %> - <%= score.get(player2Name) %></p>
<% } %>

<form action="match-score" method="post">
    <!-- <input type="hidden" name="uuid" value="<//%= currentMatch != null ? currentMatch.getUuid() : "" %>"> -->
    <button type="submit" name="action" value="player1">Player 1 Won</button>
    <button type="submit" name="action" value="player2">Player 2 Won</button>
</form>
</body>
</html>
