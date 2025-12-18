package com.example.biblioteca;

import com.example.biblioteca.dao.UsuarioDAO;
import com.example.biblioteca.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "RegistroServlet", value = "/registro-usuario")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Recibir datos del formulario
        String matricula = request.getParameter("matricula");
        String nombre = request.getParameter("nombre");
        String pass = request.getParameter("password");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        // 2. Crear el objeto Usuario (El rol lo ponemos vacío aquí, el DAO lo pondrá como "alumno")
        // Nota: Asegúrate de tener un constructor vacío o usar los setters
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setMatricula(matricula);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setContrasena(pass);
        nuevoUsuario.setDireccion(direccion);
        nuevoUsuario.setTelefono(telefono);
        nuevoUsuario.setCorreo(correo);

        // 3. Guardar en Base de Datos
        UsuarioDAO dao = new UsuarioDAO();
        boolean exito = dao.registrarUsuario(nuevoUsuario);

        // 4. Redirigir
        if (exito) {
            // Si salió bien, mandamos al login con un mensaje invisible (o podrías poner un alert)
            response.sendRedirect("index.jsp");
        } else {
            // Si falló (ej. matrícula repetida), regresamos al registro
            response.sendRedirect("registro.jsp?error=true");
        }
    }
}