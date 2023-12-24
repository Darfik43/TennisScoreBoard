<%--
  Created by IntelliJ IDEA.
  User: ddp-1
  Date: 12/23/2023
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New match</title>
</head>
<body>
    <h1>Create new match</h1>
    <form action = "${pageContext.request.contextPath}/new-match" method = "post">
        <p>
            <%= "Enter a name for the first player:" %>
            <input name = "playerOneName"/>
        </p>
        <p>
            <%= "Enter a name for the second player:" %>
            <input name = "playerTwoName">
        </p>
        <button>Submit</button>
    </form>
</body>
</html>
