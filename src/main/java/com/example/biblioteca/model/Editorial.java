package com.example.biblioteca.model;

public class Editorial {
    private int id_editorial;
    private String nombre_editorial;


    Editorial(){}
    Editorial(int id_editorial, String nombre_editorial){
        this.id_editorial = id_editorial;
        this.nombre_editorial = nombre_editorial;
    }
    //gets
    public int getId_editorial() {
        return id_editorial;
    }
    public String getNombre_editorial() {
        return nombre_editorial;
    }
    //sets

    public void setId_editorial(int id_editorial) {
        this.id_editorial = id_editorial;
    }
    public void setNombre_editorial(String nombre_editorial) {
        this.nombre_editorial = nombre_editorial;
    }
}
