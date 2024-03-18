<%@ page import="java.util.UUID" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Tennis Match</title>
</head>
<body>

<h1>Create Tennis Match</h1>

<form action="nmatch" method="post">
    <label for="player1Name">Player 1 Name:</label>
    <input type="text" id="player1Name" name="player1Name" required>
    <br>
    <label for="player2Name">Player 2 Name:</label>
    <input type="text" id="player2Name" name="player2Name" required>
    <br>
    <input type="hidden" name="matchId" value="<%= UUID.randomUUID() %>">
    <input type="submit" value="Start Match">
</form>


</body>
</html>
