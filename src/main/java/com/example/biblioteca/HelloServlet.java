package com.example.biblioteca;

import java.io.*;
import java.sql.*; // Importante para Connection y SQLException

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

// IMPORTANTE: Importa tus clases de configuración
import com.example.biblioteca.config.config;
import com.example.biblioteca.config.CredencialesBD;

// 1. CAMBIO DE URL: Lo cambiamos a "/login" para que tu formulario action="login" lo encuentre.
@WebServlet(name = "helloServlet", value = "/login")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    // Este método se usa si entras directo por la URL
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<p>Para probar el login, usa el formulario index.jsp</p>");
        out.println("</body></html>");
    }

    // 2. AQUÍ ESTÁ TU CÓDIGO (doPost maneja el formulario)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userForm = request.getParameter("usuario");
        String passForm = request.getParameter("password");

        // --- TU LÓGICA DE SELECCIÓN DE IP ---

        // CASO 1: Para trabajar en tu PC (Localhost)
        CredencialesBD datos = new CredencialesBD("localhost", "nombre_db", "euler", "1234");

        // CASO 2: Para conectar a la otra PC (Descomenta esta y comenta la de arriba)
        // CredencialesBD datos = new CredencialesBD("192.168.1.50", "nombre_db", "euler", "1234");

        // -------------------------------------

        config db = new config();

        // Pasamos el objeto 'datos' al método conexion
        Connection con = db.conexion(datos);

        out.println("<html><body>");

        if (con != null) {
            out.println("<h2 style='color:green'>¡Conexión Exitosa!</h2>");
            out.println("<p>Intentaste conectar a IP: <b>" + datos.getIp() + "</b></p>");
            out.println("<p>Usuario recibido del form: " + userForm + "</p>");

            // Aquí iría tu lógica para verificar usuario/contraseña en la BD...
            try {
                con.close(); // Buena práctica cerrar la conexión
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            out.println("<h2 style='color:red'>Error al conectar</h2>");
            out.println("<p>Verifica que la IP " + datos.getIp() + " sea correcta y el servidor esté activo.</p>");
        }

        out.println("<a href='index.jsp'>Volver</a>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}