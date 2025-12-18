package com.example.biblioteca.dao;

import com.example.biblioteca.config.CredencialesBD;
import com.example.biblioteca.config.config;
import com.example.biblioteca.model.Libro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibroConsultaDAO {

    public List<Libro> obtenerTodosLosLibros() {
        List<Libro> lista = new ArrayList<>();
        Connection con = null;

        try {
            // 1. Configuramos los datos de conexión DIRECTAMENTE aquí para evitar errores
            // OJO: Confirma si tu contraseña es "1234" o "euler2718" (la que usaste en git)
            CredencialesBD credenciales = new CredencialesBD("10.123.179.6", "BIBLIOTECA", "euler", "euler2718");

            config db = new config();
            con = db.conexion(credenciales);

            if (con != null) {
                String sql = "SELECT * FROM libro";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    // Usamos el constructor vacío y los setters para evitar errores de orden
                    Libro lib = new Libro();
                    lib.setId_libro(rs.getInt("id_libro"));
                    lib.setTitulo(rs.getString("titulo"));
                    lib.setNo_paginas(rs.getInt("no_paginas"));
                    lib.setAnio_publicacion(rs.getInt("anio_publicacion"));
                    lib.setEjemplares(rs.getInt("ejemplares"));
                    lib.setEdicion(rs.getString("edicion"));
                    // Agrega más campos si los necesitas

                    lista.add(lib);
                }
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}