<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Simple</title>
</head>
<body>
<h2>Prueba de Login</h2>

<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="login" method="post">
    Usuario: <input type="text" name="usuario"><br><br>
    Password: <input type="password" name="password"><br><br>
    <button type="submit">Entrar</button>
</form>
</body>
</html>