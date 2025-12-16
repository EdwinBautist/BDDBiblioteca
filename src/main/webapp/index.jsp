<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>



<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Sistema de Biblioteca</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        body {
            background-color: #f4f1ef;
            display: flex;
        }

        /* ===== MENÚ LATERAL ===== */
        .sidebar {
            width: 220px;
            background: linear-gradient(#00d4d4, #00b3b3);
            color: white;
            min-height: 100vh;
        }

        .sidebar a {
            display: block;
            padding: 18px;
            text-decoration: none;
            color: white;
            font-weight: bold;
            border-bottom: 1px solid rgba(255,255,255,0.2);
        }

        .sidebar a:hover {
            background-color: rgba(0,0,0,0.1);
        }

        /* ===== CONTENIDO PRINCIPAL ===== */
        .main {
            flex: 1;
            padding: 20px;
        }

        /* ===== BARRA SUPERIOR ===== */
        .topbar {
            display: flex;
            align-items: center;
            gap: 15px;
            margin-bottom: 30px;
        }

        .search-box {
            flex: 1;
            padding: 12px;
            border-radius: 25px;
            border: none;
            outline: none;
        }

        .topbar a {
            padding: 12px 20px;
            border-radius: 25px;
            background: linear-gradient(to right, #00d4d4, #3f2b96);
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        /* ===== SECCIONES ===== */
        .section {
            background: linear-gradient(to right, #00d4d4, #3f2b96);
            padding: 20px;
            border-radius: 15px;
            margin-bottom: 25px;
        }

        .section h2 {
            text-align: center;
            color: black;
            margin-bottom: 15px;
        }

        .books {
            display: grid;
            grid-template-columns: repeat(5, 1fr);
            gap: 15px;
        }

        .book {
            width: 100%;
            height: 180px;
            background-color: #ddd;
            border-radius: 8px;
            display: flex;
            align-items: center;
            justify-content: center;
            text-decoration: none;
            color: #333;
            font-weight: bold;
        }

        .book:hover {
            background-color: #bbb;
        }

        /* ===== BOTÓN MÁS ===== */
        .more {
            display: block;
            width: 120px;
            margin: 30px auto;
            text-align: center;
            padding: 12px;
            border-radius: 25px;
            background: linear-gradient(to right, #00d4d4, #3f2b96);
            color: white;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>

<body>

<!-- ===== MENÚ LATERAL ===== -->
<div class="sidebar">
    <a href="#">Lista de libros</a>
    <a href="#">Géneros</a>
    <a href="#">Autores</a>
    <a href="#">Multas</a>
    <a href="#">Páginas</a>
    <a href="#">Preguntas</a>
</div>

<!-- ===== CONTENIDO ===== -->
<div class="main">

    <!-- BARRA SUPERIOR -->
    <div class="topbar">
        <input type="text" class="search-box" placeholder="Buscar libros...">
        <a href="#">Lo más visto</a>
        <a href="#">Login</a>
    </div>

    <!-- SECCIÓN 1 -->
    <div class="section">
        <h2>Administración</h2>
        <div class="books">
            <a href="detalleLibro1.html" class="book">
                <img
                        src="https://m.media-amazon.com/images/I/41as+WafrFL._SX342_SY445_.jpg"
                        alt="Libro Administración"
                        style="width:100%; height:100%; border-radius:8px;"
                >
            </a>


            <a href="#" class="book">Imagen 2</a>
            <a href="#" class="book">Imagen 3</a>
            <a href="#" class="book">Imagen 4</a>
            <a href="#" class="book">Imagen 5</a>
        </div>
    </div>

    <!-- SECCIÓN 2 -->
    <div class="section">
        <h2>Programación</h2>
        <div class="books">
            <a href="#" class="book">Imagen 6</a>
            <a href="#" class="book">Imagen 7</a>
            <a href="#" class="book">Imagen 8</a>
            <a href="#" class="book">Imagen 9</a>
            <a href="#" class="book">Imagen 10</a>
        </div>
    </div>

    <!-- BOTÓN MÁS -->
    <a href="#" class="more">más</a>

</div>

</body>
