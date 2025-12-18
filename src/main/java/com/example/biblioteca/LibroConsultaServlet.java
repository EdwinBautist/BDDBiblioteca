package com.example.biblioteca;

import com.example.biblioteca.dao.LibroConsultaDAO;
import com.example.biblioteca.model.Libro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LibroConsultaServlet", value = "/consulta-libros")
public class LibroConsultaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Validamos sesión (Opcional, si quieres probar rápido puedes comentar estas 4 líneas)
        HttpSession session = request.getSession();
        if (session.getAttribute("usuarioLogueado") == null && session.getAttribute("usuario") == null) {
            // Si no hay nadie logueado, mandamos al login
            response.sendRedirect("index.jsp");
            return;
        }

        // 2. Llamamos a nuestro NUEVO DAO
        LibroConsultaDAO dao = new LibroConsultaDAO();
        List<Libro> lista = dao.obtenerTodosLosLibros();

        // 3. Guardamos la lista en la "mochila" (request)
        request.setAttribute("misLibrosConsultados", lista);

        // 4. Mandamos a la NUEVA página JSP
        request.getRequestDispatcher("tabla_libros.jsp").forward(request, response);
    }
}