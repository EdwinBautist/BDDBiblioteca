package com.example.biblioteca.dao;

import com.example.biblioteca.config.CredencialesBD;
import com.example.biblioteca.config.config;
import com.example.biblioteca.model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement; // <--- IMPORTANTE: No olvides este import
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibroConsultaDAO {

    // Método 1: Traer TODOS (Sin cambios)
    public List<Libro> obtenerTodosLosLibros() {
        List<Libro> lista = new ArrayList<>();
        Connection con = null;
        try {
            // Usamos tus credenciales explícitas para asegurar conexión
            CredencialesBD credenciales = new CredencialesBD();
            config db = new config();
            con = db.conexion(credenciales);

            if (con != null) {
                String sql = "SELECT * FROM libro";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    lista.add(mapearLibro(rs));
                }
                con.close();
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    // Método 2: BUSCADOR (Lo nuevo)
    public List<Libro> buscarLibros(String texto) {
        List<Libro> lista = new ArrayList<>();
        Connection con = null;
        try {
            // Bueno: Toma los datos automáticamente de CredencialesBD.java
            CredencialesBD credenciales = new CredencialesBD();
            config db = new config();
            con = db.conexion(credenciales);

            if (con != null) {
                // El % permite buscar coincidencias parciales (ej: "a" encuentra "Física")
                String sql = "SELECT * FROM libro WHERE titulo LIKE ?";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + texto + "%"); // Agregamos los % aquí

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    lista.add(mapearLibro(rs));
                }
                con.close();
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    // Ayuda para no repetir código al crear el objeto
    private Libro mapearLibro(ResultSet rs) throws Exception {
        Libro lib = new Libro();
        lib.setId_libro(rs.getInt("id_libro"));
        lib.setTitulo(rs.getString("titulo"));
        lib.setNo_paginas(rs.getInt("no_paginas"));
        lib.setAnio_publicacion(rs.getInt("anio_publicacion"));
        lib.setEjemplares(rs.getInt("ejemplares"));
        lib.setEdicion(rs.getString("edicion"));
        return lib;
    }
}