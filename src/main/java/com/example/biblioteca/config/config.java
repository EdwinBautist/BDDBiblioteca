package com.example.biblioteca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class config {

    // SOLUCIÓN AL ERROR: Agregamos 'CredencialesBD datos' como parámetro
    public Connection conexion(CredencialesBD datos) {
        Connection con = null;

        try {
            // 1. Cargar el Driver de MySQL (Nota: es diferente al de Postgres)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. URL para MySQL
            // Estructura: jdbc:mysql://IP:3306/NOMBRE_BD?useSSL=false
            // Agregamos '?useSSL=false' para evitar advertencias comunes en desarrollo
            String url = "jdbc:mysql://" + datos.getIp() + ":3306/" + datos.getNombreBD() + "?useSSL=false&allowPublicKeyRetrieval=true";

            // 3. Establecer conexión usando los datos que recibimos del Servlet
            con = DriverManager.getConnection(url, datos.getUsuario(), datos.getPassword());

            System.out.println("--- Conexión a MySQL Exitosa ---");
            System.out.println("URL: " + url);

        } catch (ClassNotFoundException e) {
            System.out.println("Error CRÍTICO: No se encontró el Driver de MySQL.");
            System.out.println("Asegúrate de tener la dependencia en el pom.xml");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error SQL al conectar: " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }
}