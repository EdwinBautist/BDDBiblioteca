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
        // Validar sesión
        HttpSession session = request.getSession();
        if (session.getAttribute("usuario") == null && session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        LibroConsultaDAO dao = new LibroConsultaDAO();
        List<Libro> lista;

        // 1. Recibimos el texto de la caja de búsqueda
        String busqueda = request.getParameter("busqueda");

        // 2. Lógica: ¿Escribió algo o está vacío?
        if (busqueda != null && !busqueda.trim().isEmpty()) {
            System.out.println("DEBUG: Buscando coincidencias para: " + busqueda);
            lista = dao.buscarLibros(busqueda);
        } else {
            System.out.println("DEBUG: Trayendo lista completa");
            lista = dao.obtenerTodosLosLibros();
        }

        // 3. Enviamos los datos a TU jsp de consulta
        request.setAttribute("misLibrosConsultados", lista);
        request.getRequestDispatcher("tabla_consulta_libros.jsp").forward(request, response);
    }
}