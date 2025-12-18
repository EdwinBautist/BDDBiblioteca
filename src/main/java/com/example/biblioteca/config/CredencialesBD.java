package com.example.biblioteca.config;

public class CredencialesBD {
    // Atributos privados (Encapsulamiento)
    private String ip;
    private String nombreBD;
    private String usuario;
    private String password;

    // Constructor para crear el objeto fácilmente
    public CredencialesBD(String ip, String nombreBD, String usuario, String password) {
        this.ip = ip;
        this.nombreBD = nombreBD;
        this.usuario = usuario;
        this.password = password;
    }

    // Getters para leer los datos
    public String getIp() { return ip; }
    public String getNombreBD() { return nombreBD; }
    public String getUsuario() { return usuario; }
    public String getPassword() { return password; }
}