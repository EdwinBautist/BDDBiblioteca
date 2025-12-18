<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.biblioteca.model.Libro" %>

<!DOCTYPE html>
<html>
<head>
    <title>Resultados de la Búsqueda</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f0f2f5; padding: 20px; }
        .container { max-width: 1000px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }

        h1 { color: #1a1a1a; text-align: center; }

        /* Estilos de la tabla */
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background-color: #007bff; color: white; font-weight: bold; }
        tr:hover { background-color: #f1f1f1; }

        /* Estilos para mensajes y botones */
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
            // 1. RECIBIMOS LA LISTA QUE MANDÓ EL SERVLET
            // "misLibrosConsultados" debe coincidir con el nombre en el Servlet: request.setAttribute("misLibrosConsultados", lista);
            List<Libro> lista = (List<Libro>) request.getAttribute("misLibrosConsultados");

            // 2. VERIFICAMOS SI HAY DATOS
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
        <a href="panel_alumno.jsp" class="btn-volver">⬅ Volver al Menú / Nueva Búsqueda</a>
    </center>
</div>

</body>
</html>