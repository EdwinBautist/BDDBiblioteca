<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.biblioteca.model.Usuario" %>
<%
    // Verificamos si hay alguien logueado (Seguridad básica)
    Usuario u = (Usuario) session.getAttribute("usuario");
    if(u == null || (
            !u.getRol().trim().equalsIgnoreCase("admin") &&
                    !u.getRol().trim().equalsIgnoreCase("bibliotecario")
    )) {

        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Panel Bibliotecario</title></head>
<body style="background-color: #f0f8ff;"> <h1>Zona Administrativa</h1>
<h2>Bienvenido, <%= u.getNombre() %></h2>
<hr>
<ul>
    <li><a href="#">Registrar nuevo libro</a></li>
    <li><a href="#">Gestionar préstamos</a></li>
    <li><a href="#">Ver reportes</a></li>
</ul>
<br>
<a href="index.jsp">Cerrar Sesión</a>
</body>
</html>