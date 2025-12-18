<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Registro de Nuevo Usuario
  </title>
  <style>
    body { font-family: sans-serif; padding: 20px; background-color: #f4f4f4; }
    form { background: white; padding: 20px; max-width: 400px; margin: auto; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
    input { width: 95%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; }
    button { width: 100%; padding: 10px; background-color: #28a745; color: white; border: none; cursor: pointer; font-size: 16px; }
    button:hover { background-color: #218838; }
  </style>
</head>
<body>

<h2 style="text-align: center;">Registro de Alumno</h2>

<form action="registro-usuario" method="POST">
  <label>Matrícula (Usuario):</label>
  <input type="text" name="matricula" required placeholder="Ej: 2023001">

  <label>Nombre Completo:</label>
  <input type="text" name="nombre" required placeholder="Tu nombre">

  <label>Contraseña:</label>
  <input type="password" name="password" required>

  <label>Dirección:</label>
  <input type="text" name="direccion" required>

  <label>Teléfono:</label>
  <input type="text" name="telefono" required>

  <label>Correo:</label>
  <input type="email" name="correo" required>

  <button type="submit">Registrarme</button>
  <br><br>
  <center><a href="index.jsp">Cancelar y Volver</a></center>
</form>

</body>
</html>