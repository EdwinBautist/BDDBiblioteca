<form action="libros" method="post">
    <label>Título:</label>
    <input type="text" name="titulo" required><br>

    <label>Autor:</label>
    <input type="text" name="autor" placeholder="Ej: Stephen King" required><br>

    <label>Editorial:</label>
    <input type="text" name="editorial" placeholder="Ej: Debolsillo" required><br>

    <label>Género:</label>
    <input type="text" name="genero" placeholder="Ej: Terror" required><br>

    <label>Año:</label>
    <input type="number" name="anio" value="2024"><br>

    <label>Páginas:</label>
    <input type="number" name="paginas"><br>

    <label>Ejemplares (Stock):</label>
    <input type="number" name="ejemplares" value="1"><br>

    <label>Edición:</label>
    <input type="text" name="edicion"><br>

    <label>Descripción:</label>
    <textarea name="descripcion"></textarea><br>

    <button type="submit">Guardar Libro</button>
</form>