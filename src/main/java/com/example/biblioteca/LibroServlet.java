package com.example.biblioteca;

import com.example.biblioteca.dao.LibroDAO;
import com.example.biblioteca.model.Libro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

// ¡ESTA ES LA LÍNEA QUE FALTABA O ESTABA MAL!
@WebServlet(name = "LibroServlet", value = "/libros")
public class LibroServlet extends HttpServlet {

    private LibroDAO dao = new LibroDAO();

    // 1. doGet: Para VER la lista de libros o el formulario
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "nuevo":
                // Muestra el formulario vacío
                request.getRequestDispatcher("form_libro.jsp").forward(request, response);
                break;
            default:
                // Por defecto: LISTAR libros
                List<Libro> lista = dao.listar();
                request.setAttribute("listaLibros", lista);
                // IMPORTANTE: Redirigir al panel bibliotecario para que vea los libros
                request.getRequestDispatcher("panel_bibliotecario.jsp").forward(request, response);
                break;
        }
    }

    // 2. doPost: Para GUARDAR un libro nuevo (Insertar)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            // Recibimos datos del formulario
            String titulo = request.getParameter("titulo");

            // Ojo: Recibimos TEXTO para autor/editorial/genero (Opción A)
            String autor = request.getParameter("autor");
            String editorial = request.getParameter("editorial");
            String genero = request.getParameter("genero");

            // Parseamos números
            // Nota: Es buena práctica validar si vienen vacíos, aquí asumimos que el form tiene 'required'
            int paginas = Integer.parseInt(request.getParameter("paginas"));
            int anio = Integer.parseInt(request.getParameter("anio"));
            int ejemplares = Integer.parseInt(request.getParameter("ejemplares"));

            String edicion = request.getParameter("edicion");
            String descripcion = request.getParameter("descripcion");

            // Creamos el objeto
            Libro l = new Libro();
            l.setTitulo(titulo);
            l.setNombreAutor(autor);
            l.setNombreEditorial(editorial);
            l.setNombreGenero(genero);
            l.setNo_paginas(paginas);
            l.setAnio_publicacion(anio);
            l.setEjemplares(ejemplares);
            l.setEdicion(edicion);
            l.setDescripcion(descripcion);

            // Guardamos en BD
            if (dao.insertar(l)) {
                // Si todo sale bien, volvemos a la lista
                response.sendRedirect("libros");
            } else {
                // Si falla, mandamos error (podrías mejorar esto enviando un mensaje)
                response.sendRedirect("form_libro.jsp?error=true");
            }

        } catch (NumberFormatException e) {
            // Error común si un campo numérico viene vacío o con letras
            e.printStackTrace();
            response.sendRedirect("form_libro.jsp?error=formato");
        }
    }
}