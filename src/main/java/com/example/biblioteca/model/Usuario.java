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

    public String getMatricula(){
        return matricula;
    }
    public String getRol() {
        return rol;
    }
    public String getNombre(){
        return nombre;
    }
    public String getContrasena(){
        return contrasena;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getCorreo(){
        return correo;
    }
    public String getTelefono(){
        return telefono;
    }
    //sets
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
