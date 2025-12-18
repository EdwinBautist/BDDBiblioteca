<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.biblioteca.model.Libro" %>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Libros</title>
    <style>
        body { font-family: sans-serif; background-color: #f4f4f4; padding: 20px; }
        h1 { text-align: center; color: #333; }
        table { width: 100%; border-collapse: collapse; background: white; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        th, td { padding: 12px; border-bottom: 1px solid #ddd; text-align: left; }
        th { background-color: #007bff; color: white; }
        tr:hover { background-color: #f1f1f1; }
        .btn-volver { display: block; width: 200px; margin: 20px auto; padding: 10px; background: #555; color: white; text-align: center; text-decoration: none; border-radius: 5px; }
    </style>
</head>
<body>

<h1>📚 Catálogo de Libros</h1>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Título</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Recuperamos la lista que mandó el Servlet
        List<Libro> lista = (List<Libro>) request.getAttribute("listaLibros");

        if (lista != null && !lista.isEmpty()) {
            for (Libro l : lista) {
    %>
    <tr>
        <td><%= l.getId_libro() %></td>
        <td><%= l.getTitulo() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="2" style="text-align:center; color:red;">No se encontraron libros.</td>
    </tr>
    <% } %>
    </tbody>
</table>

<a href="panel_alumno.jsp" class="btn-volver">atras</a>

</body>
</html>