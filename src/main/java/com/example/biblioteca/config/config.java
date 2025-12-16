package com.example.biblioteca.config;
import java.sql.*;
//Establecemos la conexión con la base de datos.
public class config {
    public Connection conexion(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.cl.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombre_db?useSSL=false", "edwi", "edwi123");
            System.out.println("Conexión exitosa!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Error al conectar con la base de datos");
        }
        return conn;
    }
}
