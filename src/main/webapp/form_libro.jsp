<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Libro</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px; }
        .container { max-width: 500px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        h2 { text-align: center; color: #333; }
        label { display: block; margin-top: 10px; font-weight: bold; color: #555; }
        input, textarea { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background-color: #28a745; color: white; border: none; margin-top: 20px; border-radius: 5px; cursor: pointer; font-size: 16px; }
        button:hover { background-color: #218838; }
        .back-link { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #007bff; }
    </style>
</head>
<body>

<div class="container">
    <h2>Registrar Nuevo Libro</h2>

    <form action="libros" method="post">
        <input type="hidden" name="accion" value="insertar">

        <label>Título:</label>
        <input type="text" name="titulo" required>

        <label>Autor:</label>
        <input type="text" name="autor" placeholder="Ej: Gabriel García Márquez" required>

        <label>Editorial:</label>
        <input type="text" name="editorial" placeholder="Ej: Diana" required>

        <label>Género:</label>
        <input type="text" name="genero" placeholder="Ej: Realismo Mágico" required>

        <label>Año de Publicación:</label>
        <input type="number" name="anio" value="2024" min="1000" max="2099">

        <label>Número de Páginas:</label>
        <input type="number" name="paginas" min="1">

        <label>Ejemplares (Stock):</label>
        <input type="number" name="ejemplares" value="1" min="0">

        <label>Edición:</label>
        <input type="text" name="edicion" placeholder="Ej: 1ra Edición">

        <label>Descripción:</label>
        <textarea name="descripcion" rows="4"></textarea>

        <button type="submit">Guardar Libro</button>
    </form>

    <a href="libros" class="back-link">Volver al listado</a>
</div>

</body>
</html>