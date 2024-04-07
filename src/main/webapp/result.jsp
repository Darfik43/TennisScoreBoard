<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Tennis Match Result</title>
  <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<p> <a href="${pageContext.request.contextPath}/pastMatches" class= "button">Scoreboard</a> </p>
<h1>Tennis Match Result</h1>

<h2>Match Details</h2>
<p>Player 1: <%= request.getAttribute("player1Name") %></p>
<p>Player 2: <%= request.getAttribute("player2Name") %></p>

<h2>Winner</h2>
<p> <%= request.getAttribute("winnerName") %></p>

<h2>Final Score</h2>
<table>
  <tr>
    <td><%= request.getAttribute("player1Name") %></td>
    <td><%= request.getAttribute("player1MatchScore") %></td>
  </tr>
  <tr>
    <td><%= request.getAttribute("player2Name") %></td>
    <td><%= request.getAttribute("player2MatchScore") %></td>
  </tr>
</table>

</body>
</html>
