package com.example.biblioteca.model;

public class Facultad {
    private int id_facultad;
    private String nombre_facultad;

    public Facultad(){}
    public Facultad(int id_facultad,String nombre_facultad){
        this.id_facultad = id_facultad;
        this.nombre_facultad = nombre_facultad;
    }
//gets
    public int getId_facultad() {
        return id_facultad;
    }
    public String getNombre_facultad() {
        return nombre_facultad;
    }
    //sets

    public void setId_facultad(int id_facultad) {
        this.id_facultad = id_facultad;
    }
    public void setNombre_facultad(String nombre_facultad) {
        this.nombre_facultad = nombre_facultad;
    }
}
