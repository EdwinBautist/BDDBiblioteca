<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.biblioteca.model.Libro" %>
<%@ page import="com.example.biblioteca.model.Usuario" %>

<%
    // 2. LÓGICA PARA DECIDIR A DÓNDE VOLVER
    Usuario u = (Usuario) session.getAttribute("usuarioLogueado"); // O "usuario", depende como lo guardaste en el Login

    // Si por alguna razón es null, probamos con la otra llave por seguridad
    if (u == null) {
        u = (Usuario) session.getAttribute("usuario");
    }

    String linkVolver = "index.jsp"; // Por defecto, si falla, al inicio
    String textoVolver = "Volver al Inicio";

    if (u != null) {
        // AQUÍ ESTÁ EL TRUCO: Comparamos el rol (ajusta "bibliotecario" a como lo tengas en tu Base de Datos)
        if (u.getRol() != null && u.getRol().equalsIgnoreCase("bibliotecario")) {
            linkVolver = "panel_bibliotecario.jsp";
            textoVolver = "Volver al Panel Bibliotecario";
        } else {
            linkVolver = "panel_alumno.jsp";
            textoVolver = "Volver al Panel Alumno";
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Resultados de la Búsqueda</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f0f2f5; padding: 20px; }
        .container { max-width: 1000px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }

        h1 { color: #1a1a1a; text-align: center; }

        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background-color: #007bff; color: white; font-weight: bold; }
        tr:hover { background-color: #f1f1f1; }

        .empty-msg { text-align: center; color: #dc3545; font-size: 1.2em; padding: 20px; }
        .btn-volver {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #6c757d;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }
        .btn-volver:hover { background-color: #5a6268; }
    </style>
</head>
<body>

<div class="container">
    <h1>📚 Libros Disponibles</h1>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Páginas</th>
            <th>Año</th>
            <th>Edición</th>
            <th>Stock</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Libro> lista = (List<Libro>) request.getAttribute("misLibrosConsultados");

            if (lista != null && !lista.isEmpty()) {
                for (Libro libro : lista) {
        %>
        <tr>
            <td><%= libro.getId_libro() %></td>
            <td style="color: #0056b3; font-weight: bold;"><%= libro.getTitulo() %></td>
            <td><%= libro.getNo_paginas() %></td>
            <td><%= libro.getAnio_publicacion() %></td>
            <td><%= libro.getEdicion() %></td>

            <% if(libro.getEjemplares() > 0) { %>
            <td style="color: green;"><%= libro.getEjemplares() %> disp.</td>
            <% } else { %>
            <td style="color: red; font-weight: bold;">Agotado</td>
            <% } %>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6" class="empty-msg">
                No se encontraron libros que coincidan con tu búsqueda. 😢
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <center>
        <a href="<%= linkVolver %>" class="btn-volver">⬅ <%= textoVolver %></a>
    </center>
</div>

</body>
</html>