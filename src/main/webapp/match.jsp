<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tennis Match Score</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Tennis Match Score</h1>

<%
    UUID matchId = UUID.fromString(request.getParameter("matchId"));
%>

<% if (matchId != null) { %>
<h2>Match Details</h2>
<p>Player 1: <%= request.getAttribute("player1Name") %></p>
<p>Player 2: <%= request.getAttribute("player2Name") %></p>
<% } %>

<h2>Current Score</h2>
<table>
    <tr>
        <th></th>
        <th>Match Score</th>
        <th>Set Score</th>
        <th>Game Score</th>
        <th>Tie Break Score</th>
    </tr>
    <tr>
        <td><%= request.getAttribute("player1Name") %></td>
        <td><%= request.getAttribute("player1MatchScore") %></td>
        <td><%= request.getAttribute("player1SetScore") %></td>
        <td><%= request.getAttribute("player1GameScore") %></td>
        <td><%= request.getAttribute("player1TieBreakScore") %></td>
    </tr>
    <tr>
        <td><%= request.getAttribute("player2Name") %></td>
        <td><%= request.getAttribute("player2MatchScore") %></td>
        <td><%= request.getAttribute("player2SetScore") %></td>
        <td><%= request.getAttribute("player2GameScore") %></td>
        <td><%= request.getAttribute("player2TieBreakScore") %></td>
    </tr>
</table>


 <form action="increaseScore?matchId=<%= matchId %>" method="post">
    <input type="hidden" name="matchId" value="<%= request.getParameter("matchId") %>">
   <p> <button type="submit" name="action" value="player1">Player 1 Won</button> </p>
</form>

<form action="increaseScore?matchId=<%= matchId %>" method="post">
    <input type="hidden" name="matchId" value="<%= request.getParameter("matchId") %>">
   <p> <button type="submit" name="action" value="player2">Player 2 Won</button> </p>
</form>

</body>
</html>