<%--<%@ page import="java.util.Map" %>--%>
<%--<%@ page import="java.util.UUID" %>--%>
<%--<%@ page import="com.tennisscoreboard.service.scorecalculation.MatchManager" %>--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Tennis Match Score</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>Tennis Match Score</h1>--%>

<%--<%--%>
<%--    UUID matchId = (UUID) request.getSession().getAttribute("matchId");--%>
<%--    String player1Name = (String) request.getAttribute("player1Name");--%>
<%--    String player2Name = (String) request.getAttribute("player2Name");--%>
<%--    MatchManager matchManager = (MatchManager) request.getSession().getAttribute("matchManager");--%>
<%--%>--%>

<%--<% if (matchId != null) { %>--%>
<%--<h2>Match Details</h2>--%>
<%--<p>Player 1: <%= player1Name %></p>--%>
<%--<p>Player 2: <%= player2Name %></p>--%>
<%--<% } %>--%>

<%--<h2>Current Score</h2>--%>
<%--<% if (matchManager != null) { %>--%>
<%--<p>Player 1: <%= player1Name %> - MatchScore: <%= matchManager.getMatchScore() %> Set Score: <%= matchManager.getSetScore() %> GameScore: <%= matchManager.getGameScore() %> TieBreakScore: <%= matchManager.getTieBreakScore() %> </p>--%>
<%--<p>Player 2: <%= player2Name %> - MatchScore: <%= matchManager.getMatchScore() %> Set Score: <%= matchManager.getSetScore() %> GameScore: <%= matchManager.getGameScore() %> TieBreakScore: <%= matchManager.getTieBreakScore() %> </p>--%>
<%--<% } %>--%>

<%--<form action="increaseScore?matchId=<%= matchId %>" method="post">--%>
<%--    <input type="hidden" name="matchId" value="<%= request.getSession().getAttribute("matchId") %>">--%>
<%--    <button type="submit" name="action" value="player1">Player 1 Won</button>--%>
<%--</form>--%>

<%--<form action="increaseScore?matchId=<%= matchId %>" method="post">--%>
<%--    <input type="hidden" name="matchId" value="<%= request.getSession().getAttribute("matchId") %>">--%>
<%--    <button type="submit" name="action" value="player2">Player 2 Won</button>--%>
<%--</form>--%>

<%--</body>--%>
<%--</html>--%>



<%@ page import="java.util.Map" %>
<%@ page import="java.util.UUID" %>
<%@ page import="com.tennisscoreboard.service.scorecalculation.MatchManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tennis Match Score</title>
    <style>
        table {
            width: 50%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
<h1>Tennis Match Score</h1>

<%
    UUID matchId = (UUID) request.getSession().getAttribute("matchId");
    String player1Name = (String) request.getAttribute("player1Name");
    String player2Name = (String) request.getAttribute("player2Name");
    MatchManager matchManager = (MatchManager) request.getSession().getAttribute(matchId.toString());
%>

<% if (matchId != null) { %>
<h2>Match Details</h2>
<p>Player 1: <%= player1Name %></p>
<p>Player 2: <%= player2Name %></p>
<% } %>

<h2>Current Score</h2>
<% if (matchManager != null) { %>
<table>
    <tr>
        <th></th>
        <th>Match Score</th>
        <th>Set Score</th>
        <th>Game Score</th>
        <th>Tie Break Score</th>
    </tr>
    <tr>
        <td><%= player1Name %></td>
        <td><%= matchManager.getPlayerMatchScore(player1Name) %></td>
        <td><%= matchManager.getPlayerSetScore(player1Name) %></td>
        <td><%= matchManager.getPlayerGameScore(player1Name) %></td>
        <td><%= matchManager.getPlayerTieBreakScore(player1Name) %></td>
    </tr>
    <tr>
        <td><%= player2Name %></td>
        <td><%= matchManager.getPlayerMatchScore(player2Name) %></td>
        <td><%= matchManager.getPlayerSetScore(player2Name) %></td>
        <td><%= matchManager.getPlayerGameScore(player2Name) %></td>
        <td><%= matchManager.getPlayerTieBreakScore(player2Name) %></td>
    </tr>
</table>
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