package com.example.biblioteca.dao;

import com.example.biblioteca.config.CredencialesBD;
import com.example.biblioteca.config.config;
import com.example.biblioteca.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    private config dbConfig = new config();
    // Ajusta las credenciales si es necesario
    private CredencialesBD credenciales = new CredencialesBD("10.123.179.6", "BIBLIOTECA", "euler", "euler2718");

    // ==========================================
    // 1. MÉTODO LISTAR (Lectura con JOINs)
    // ==========================================
    public List<Libro> listar() {
        List<Libro> lista = new ArrayList<>();

        // USAMOS GROUP_CONCAT para unir múltiples autores/géneros en una sola cadena de texto
        String sql = "SELECT l.id_libro, l.titulo, l.anio_publicacion, l.ejemplares, l.portada, " +
                "GROUP_CONCAT(DISTINCT a.nombre_autor SEPARATOR ', ') as autores_concat, " +     // <--- MAGIA AQUÍ
                "GROUP_CONCAT(DISTINCT e.nombre_editorial SEPARATOR ', ') as editoriales_concat, " +
                "GROUP_CONCAT(DISTINCT g.nombre_genero SEPARATOR ', ') as generos_concat " +
                "FROM libro l " +
                "LEFT JOIN libro_autor la ON l.id_libro = la.id_libro " +
                "LEFT JOIN autor a ON la.id_autor = a.id_autor " +
                "LEFT JOIN libro_editorial le ON l.id_libro = le.id_libro " +
                "LEFT JOIN editorial e ON le.id_editorial = e.id_editorial " +
                "LEFT JOIN libro_genero lg ON l.id_libro = lg.id_libro " +
                "LEFT JOIN genero g ON lg.id_genero = g.id_genero " +
                "GROUP BY l.id_libro";

        try (Connection con = dbConfig.conexion(credenciales);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Libro l = new Libro();
                l.setId_libro(rs.getInt("id_libro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAnio_publicacion(rs.getInt("anio_publicacion"));
                l.setEjemplares(rs.getInt("ejemplares"));

                // Aquí guardamos "Autor 1, Autor 2" dentro del String de Java
                l.setNombreAutor(rs.getString("autores_concat"));
                l.setNombreEditorial(rs.getString("editoriales_concat"));
                l.setNombreGenero(rs.getString("generos_concat"));

                lista.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ==========================================
    // 2. MÉTODO INSERTAR (Transacción Compleja)
    // ==========================================
    public boolean insertar(Libro l) {
        Connection con = null;
        PreparedStatement psLibro = null;

        // SQL para insertar en la tabla principal 'libro'
        String sqlLibro = "INSERT INTO libro (titulo, no_paginas, anio_publicacion, ejemplares, edicion, descripcion, portada) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = dbConfig.conexion(credenciales);

            // A. INICIAR TRANSACCIÓN (Apagamos autoguardado)
            con.setAutoCommit(false);

            // B. GESTIONAR CATÁLOGOS (Busca o Crea y devuelve el ID)
            int idAutor = gestionarCatalogo(con, "autor", "nombre_autor", l.getNombreAutor());
            int idEditorial = gestionarCatalogo(con, "editorial", "nombre_editorial", l.getNombreEditorial());
            int idGenero = gestionarCatalogo(con, "genero", "nombre_genero", l.getNombreGenero());

            // C. INSERTAR EL LIBRO
            psLibro = con.prepareStatement(sqlLibro, Statement.RETURN_GENERATED_KEYS);
            psLibro.setString(1, l.getTitulo());
            psLibro.setInt(2, l.getNo_paginas());
            psLibro.setInt(3, l.getAnio_publicacion());
            psLibro.setInt(4, l.getEjemplares());
            psLibro.setString(5, l.getEdicion());
            psLibro.setString(6, l.getDescripcion());
            psLibro.setString(7, "default.jpg"); // Portada por defecto

            int filasAfectadas = psLibro.executeUpdate();

            if (filasAfectadas > 0) {
                // Obtener el ID generado por la BD
                ResultSet generatedKeys = psLibro.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idLibro = generatedKeys.getInt(1);

                    // D. INSERTAR RELACIONES EN TABLAS INTERMEDIAS
                    crearRelacion(con, "libro_autor", "id_libro", "id_autor", idLibro, idAutor);
                    crearRelacion(con, "libro_editorial", "id_libro", "id_editorial", idLibro, idEditorial);
                    crearRelacion(con, "libro_genero", "id_libro", "id_genero", idLibro, idGenero);

                    // E. CONFIRMAR TODO (COMMIT)
                    con.commit();
                    return true;
                }
            }

            // Si algo falló antes del commit
            con.rollback();
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (SQLException ex) {}
            return false;
        } finally {
            try { if (con != null) { con.setAutoCommit(true); con.close(); } } catch (SQLException e) {}
        }
    }

    // ==========================================
    // 4. MÉTODO ELIMINAR
    // ==========================================
    public void eliminar(int idLibro) {
        String sqlRel1 = "DELETE FROM libro_autor WHERE id_libro = ?";
        String sqlRel2 = "DELETE FROM libro_editorial WHERE id_libro = ?";
        String sqlRel3 = "DELETE FROM libro_genero WHERE id_libro = ?";
        String sqlLibro = "DELETE FROM libro WHERE id_libro = ?";

        Connection con = null;
        try {
            con = dbConfig.conexion(credenciales);
            con.setAutoCommit(false); // Transacción para borrar todo o nada

            // 1. Borrar relaciones primero
            try(PreparedStatement ps = con.prepareStatement(sqlRel1)) { ps.setInt(1, idLibro); ps.executeUpdate(); }
            try(PreparedStatement ps = con.prepareStatement(sqlRel2)) { ps.setInt(1, idLibro); ps.executeUpdate(); }
            try(PreparedStatement ps = con.prepareStatement(sqlRel3)) { ps.setInt(1, idLibro); ps.executeUpdate(); }

            // 2. Borrar el libro
            try(PreparedStatement ps = con.prepareStatement(sqlLibro)) {
                ps.setInt(1, idLibro);
                ps.executeUpdate();
            }

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try { if(con!=null) con.rollback(); } catch (SQLException ex){}
        } finally {
            try { if(con!=null) {con.setAutoCommit(true); con.close();} } catch (Exception e){}
        }
    }

    // ==========================================
    // 3. MÉTODOS PRIVADOS DE AYUDA (Helpers)
    // ==========================================

    // Busca si un dato existe (ej: "Terror"). Si existe devuelve su ID, si no, lo crea y devuelve el nuevo ID.
    private int gestionarCatalogo(Connection con, String tabla, String columnaNombre, String valor) throws SQLException {
        // Validación anti-nulos
        if(valor == null) valor = "Desconocido";

        // A. Buscar
        String sqlBuscar = "SELECT " + "id_" + tabla + " FROM " + tabla + " WHERE " + columnaNombre + " = ?";
        try (PreparedStatement ps = con.prepareStatement(sqlBuscar)) {
            ps.setString(1, valor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Ya existe
            }
        }

        // B. Insertar
        String sqlInsertar = "INSERT INTO " + tabla + " (" + columnaNombre + ") VALUES (?)";
        try (PreparedStatement ps = con.prepareStatement(sqlInsertar, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, valor);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Nuevo ID
            }
        }
        throw new SQLException("No se pudo obtener ID para " + tabla);
    }

    // Inserta en las tablas puente (libro_autor, etc)
    private void crearRelacion(Connection con, String tabla, String colLibro, String colOtro, int idLibro, int idOtro) throws SQLException {
        String sql = "INSERT INTO " + tabla + " (" + colLibro + ", " + colOtro + ") VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idLibro);
            ps.setInt(2, idOtro);
            ps.executeUpdate();
        }
    }
}