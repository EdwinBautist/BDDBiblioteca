package com.example.biblioteca.model;

public class Autor {
    private int id_Autor;
    private  String nombre_autor;

    Autor (){

    }
    Autor(int id_Autor, String nombre_autor) {
        this.id_Autor = id_Autor;
        this.nombre_autor = nombre_autor;
    }
    //gest
    public int getId_Autor() {
        return id_Autor;
    }
    public String getNombre_autor() {
        return nombre_autor;
    }
    //sets
    public void setId_Autor(int id_Autor) {
        this.id_Autor = id_Autor;
    }
    public void setNombre_autor(String nombre_autor) {
        this.nombre_autor = nombre_autor;
    }
}

