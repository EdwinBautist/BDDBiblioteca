<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.biblioteca.model.Usuario" %>
<%
    Usuario u = (Usuario) session.getAttribute("usuario");
    if(u == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Biblioteca Virtual</title>
    <style>
        .search-box { margin: 10px 0; }
        .search-box input[type="text"] { padding: 5px; width: 200px; }
        .search-box button { padding: 5px 10px; cursor: pointer; background-color: #007bff; color: white; border: none; }
    </style>
</head>
<body style="background-color: #f5f5dc; padding: 20px;">

<h1>Bienvenido a tu Biblioteca</h1>
<h2>Hola, <%= u.getNombre() %></h2>
<p>Tu matrícula es: <%= u.getMatricula() %></p>
<hr>

<h3>¿Qué deseas hacer hoy?</h3>

<div class="search-box">
    <form action="consulta-libros" method="GET">
        <label>Buscar Libros:</label><br>
        <input type="text" name="busqueda" placeholder="Ej: Harry, Java, a..." required>
        <button type="submit">🔍 Buscar</button>
    </form>
</div>

<ul>
    <li><a href="consulta-libros">Ver catálogo completo</a></li>
</ul>
    <li><a href="#">Mis Préstamos Activos</a></li>
</ul>

<br>
<a href="index.jsp">Cerrar Sesión</a>
</body>
</html>