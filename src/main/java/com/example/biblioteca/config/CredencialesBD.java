package com.example.biblioteca.config;

public class CredencialesBD {
    private String ip;
    private String nombreBD;
    private String usuario;
    private String password;

    // --- NUEVO: Constructor VACÍO con los datos fijos ---
    // Este es el que usarás en todos tus DAOs.
    // AQUÍ es el único lugar donde cambiarás la IP en el futuro.
    public CredencialesBD() {
        this.ip = "172.16.1.164";      // <--- Tu IP actual
        this.nombreBD = "BIBLIOTECA";  // <--- Tu Base de Datos
        this.usuario = "euler";        // <--- Tu Usuario
        this.password = "euler2718";   // <--- Tu Contraseña real
    }

    // --- Constructor antiguo (con parámetros) ---
    // Lo dejamos por si alguna vez necesitas conectarte a una base distinta manualmente
    public CredencialesBD(String ip, String nombreBD, String usuario, String password) {
        this.ip = ip;
        this.nombreBD = nombreBD;
        this.usuario = usuario;
        this.password = password;
    }

    // Getters (Se quedan igual)
    public String getIp() { return ip; }
    public String getNombreBD() { return nombreBD; }
    public String getUsuario() { return usuario; }
    public String getPassword() { return password; }
}