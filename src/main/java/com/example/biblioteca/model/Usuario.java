package com.example.biblioteca.model;

public class Usuario {
    private String matricula;
    private String rol;
    private String nombre;
    private String contrasena;
    private String direccion;
    private String telefono;
    private String correo;

    //constructores->
    Usuario (){}

    Usuario (String matricula,String rol,String nombre,String contrasena,String direccion,String telefono,String correo){
        this.matricula = matricula;
        this.rol = rol;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }
}
