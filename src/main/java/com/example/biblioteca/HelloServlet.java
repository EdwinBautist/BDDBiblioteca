package com.example.biblioteca;

import java.io.*;
import java.sql.*; // Importante para Connection y SQLException

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpSession;

// IMPORTANTE: Importa tus clases de configuración
import com.example.biblioteca.config.config;
import com.example.biblioteca.config.CredencialesBD;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.dao.UsuarioDAO;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String matriculaForm = request.getParameter("usuario");
        String passForm = request.getParameter("password");

        // Credenciales
        //CredencialesBD datos = new CredencialesBD("10.123.179.6", "BIBLIOTECA", "euler", "euler2718");
        CredencialesBD datos = new CredencialesBD("10.123.179.6", "BIBLIOTECA", "euler", "euler2718");

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuariologueado = dao.validar(matriculaForm, passForm, datos);


        config db = new config();
        // Pasamos el objeto 'datos' al método conexion
        Connection con = db.conexion(datos);

        out.println("<html><body>");

        if (usuariologueado != null) {
            // 1. CREAR SESIÓN (La "mochila" del usuario)
            // Esto permite que los datos viajen a la siguiente página
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuariologueado);

            // 2. LOGICA DE REDIRECCIÓN
            String rol = usuariologueado.getRol().trim();

            // Comparamos el rol (Asegúrate que coincida con lo que tienes en MySQL)
            if (rol.equalsIgnoreCase("admin") || rol.equalsIgnoreCase("bibliotecario")) {

                System.out.println("Redirigiendo a panel bibliotecario...");
                response.sendRedirect("panel_bibliotecario.jsp");

            } else if (rol.equalsIgnoreCase("alumno") || rol.equalsIgnoreCase("profesor")) {

                System.out.println("Redirigiendo a panel alumno...");
                response.sendRedirect("panel_alumno.jsp");

            } else {
                // Si el rol es desconocido
                out.println("Error: Rol no reconocido (" + rol + ")");
            }

        } else {
            // Si el login falla
            request.setAttribute("error", "Credenciales incorrectas");
            // Usamos forward en lugar de sendRedirect para pasar el mensaje de error
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        out.println("<a href='index.jsp'>Volver</a>");
        out.println("</body></html>");
    }
}