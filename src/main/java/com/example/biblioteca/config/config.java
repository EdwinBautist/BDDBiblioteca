package com.example.biblioteca.config;
import java.sql.*;

public class config {

    public Connection conexion(CredencialesBD datos) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://" + datos.getIp() + ":3306/" + datos.getNombreBD()
                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

            conn = DriverManager.getConnection(url, datos.getUsuario(), datos.getPassword());

            System.out.println("Conectado a: " + datos.getIp());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Error conectando a " + datos.getIp());
        }
        return conn;
    }
}