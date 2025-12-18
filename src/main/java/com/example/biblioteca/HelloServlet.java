package com.example.biblioteca;

import java.io.*;
import java.sql.*; // Importante para Connection y SQLException

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            out.println("<h2 style='color:green'>¡Bienvenido !"+ usuariologueado.getNombre() + "!</h2>");
            out.println("<p>Rol Detectado: <b>" + usuariologueado + "</b></p>");
            out.println("<p>Matricula: " + usuariologueado.getMatricula() + "</p>");

        } else {
            out.println("<h2 style='color:red'>Acceso Denegado</h2>");
            out.println("<p> Matrícula o contraseña incorrecta. </p>");
        }

        out.println("<a href='index.jsp'>Volver</a>");


// --- AGREGA ESTO PARA DEPURAR ---
        System.out.println("========================================");
        System.out.println("INTENTO DE LOGIN:");
        System.out.println("1. Matrícula recibida del form: '" + matriculaForm + "'");
        System.out.println("2. Contraseña recibida del form: '" + passForm + "'");
        System.out.println("========================================");
// --------------------------------
        out.println("</body></html>");
    }
}