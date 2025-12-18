<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.biblioteca.model.Usuario" %>
<%
    // Verificamos seguridad
    Usuario u = (Usuario) session.getAttribute("usuario");
    if(u == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Biblioteca Virtual</title></head>
<body style="background-color: #f5f5dc;"> <h1>Bienvenido a tu Biblioteca</h1>
<h2>Hola, <%= u.getNombre() %></h2>
<p>Tu matrícula es: <%= u.getMatricula() %></p>
<hr>
<h3>Tus opciones:</h3>
<ul>
    <li><a href="consulta-libros">Buscar Libros</a></li>
    <li><a href="#">Mis Préstamos Activos</a></li>
</ul>
<br>
<a href="index.jsp">Cerrar Sesión</a>
</body>
</html>