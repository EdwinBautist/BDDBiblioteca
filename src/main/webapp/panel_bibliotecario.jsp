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

<div style="margin-bottom: 20px;">
    <a href="libros?accion=nuevo" style="padding: 10px; background-color: #28a745; color: white; text-decoration: none; border-radius: 5px;">+ Agregar Nuevo Libro</a>
</div>

<div class="search-box">
    <form action="consulta-libros" method="GET">
        <label>Buscar Libros:</label><br>
        <input type="text" name="busqueda" placeholder="Ej: Harry, Java, a..." required>
        <button type="submit">🔍 Buscar</button>
    </form>
</div>
<h3>Catálogo de Libros</h3>
<div style="display: flex; flex-wrap: wrap; gap: 20px;">
    <%
        // Obtenemos la lista que el servlet debería enviar
        java.util.List<com.example.biblioteca.model.Libro> lista = (java.util.List) request.getAttribute("listaLibros");
        if (lista != null) {
            for (com.example.biblioteca.model.Libro libro : lista) {
    %>
    <div style="border: 1px solid #ccc; padding: 15px; width: 200px; background: white; border-radius: 8px; box-shadow: 2px 2px 5px rgba(0,0,0,0.1);">
        <img src="img/<%= libro.getPortada() != null ? libro.getPortada() : "default.jpg" %>" alt="Portada" style="width: 100%; height: 150px; object-fit: cover; border-radius: 4px;">
        <h4 style="margin: 10px 0 5px 0;"><%= libro.getTitulo() %></h4>
        <p style="font-size: 0.9em; color: #555;">Autor: <%= libro.getNombreAutor() %></p>
        <p style="font-size: 0.8em;">Stock: <%= libro.getEjemplares() %></p>

        <div style="margin-top: 10px; display: flex; justify-content: space-between;">
            <a href="libros?accion=editar&id=<%= libro.getId_libro() %>" style="color: #007bff; text-decoration: none; font-size: 0.9em;">Modificar</a>
            <a href="libros?accion=eliminar&id=<%= libro.getId_libro() %>" onclick="return confirm('¿Seguro que deseas eliminarlo?')" style="color: #dc3545; text-decoration: none; font-size: 0.9em;">Eliminar</a>
        </div>
    </div>
    <%
        }
    } else {
    %>
    <p>No hay libros cargados o debes <a href="libros">refrescar la lista aquí</a>.</p>
    <%
        }
    %>

</div>
<br><br>
<hr>
<a href="registro.jsp">
    <button type="button" style="background-color: #28a745; color: white; padding: 10px 20px; border: none; cursor: pointer;">
        Crear Nueva Cuenta
    </button>
</a>
<br><hr>
<ul>
    <li><a href="#">Gestionar préstamos</a></li>
    <li><a href="index.jsp">Cerrar Sesión</a></li>
</ul>
</body>
</html>