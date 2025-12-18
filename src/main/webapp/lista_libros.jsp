<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List, com.example.biblioteca.model.Libro" %>

<html>
<head><title>Inventario</title></head>
<body>
<h1>Gestión de Libros</h1>
<a href="libros?accion=nuevo">Agregar Nuevo Libro</a>
<a href="panel_bibliotecario.jsp">Volver al Panel</a>
<br><br>

<table border="1">
    <tr>
        <th>ISBN</th><th>Título</th><th>Autor</th><th>Stock</th><th>Acciones</th>
    </tr>
    <%
        // Código Java tradicional para listar (por si no tienes JSTL configurado)
        List<Libro> lista = (List<Libro>) request.getAttribute("listaLibros");
        if(lista != null) {
            for(Libro l : lista) {
    %>
    <tr>
        <td><%= l.getIsbn() %></td>
        <td><%= l.getTitulo() %></td>
        <td><%= l.getAutor() %></td>
        <td><%= l.getStock() %></td>
        <td>
            <a href="libros?accion=editar&id=<%= l.getIsbn() %>">Editar</a> |
            <a href="libros?accion=eliminar&id=<%= l.getIsbn() %>" onclick="return confirm('¿Seguro?');">Borrar</a>
        </td>
    </tr>
    <%      }
    } %>
</table>
</body>
</html>