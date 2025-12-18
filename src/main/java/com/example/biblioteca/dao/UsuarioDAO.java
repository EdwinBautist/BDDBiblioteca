package com.example.biblioteca.dao;

import com.example.biblioteca.config.CredencialesBD;
import com.example.biblioteca.config.config;
import com.example.biblioteca.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private config dbConfig = new config();

    // Método para validar Login usando MATRICULA y CONTRASENA
    public Usuario validar(String matricula, String password, CredencialesBD credenciales) {
        Usuario u = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // IMPORTANTE: Verifica que los nombres de columnas en tu BD coincidan con estos
        String sql = "SELECT * FROM usuario WHERE matricula = ? AND contrasena = ?";

        try {
            con = dbConfig.conexion(credenciales);
            ps = con.prepareStatement(sql);

            // Asignamos los valores a los ? del SQL
            ps.setString(1, matricula);
            ps.setString(2, password);

            rs = ps.executeQuery();

            // Si encontramos un registro, llenamos el objeto Usuario
            if (rs.next()) {
                u = new Usuario();
                // Usamos los setters de tu clase Modelo
                u.setMatricula(rs.getString("matricula"));
                u.setRol(rs.getString("rol"));
                u.setNombre(rs.getString("nombre"));
                u.setContrasena(rs.getString("contrasena"));
                u.setDireccion(rs.getString("direccion"));
                u.setTelefono(rs.getString("telefono"));
                u.setCorreo(rs.getString("correo"));
            }

        } catch (Exception e) {
            System.out.println("Error en UsuarioDAO: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos para liberar memoria
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (ps != null) ps.close(); } catch (SQLException e) {}
            try { if (con != null) con.close(); } catch (SQLException e) {}
        }

        return u; // Retorna el usuario lleno si éxito, o null si falló
    }
}