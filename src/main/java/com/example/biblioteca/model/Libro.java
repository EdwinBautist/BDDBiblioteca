package com.example.biblioteca.model;

public class Libro {
    private int id_libro;
    private String titulo;
    private int no_paginas;
    private int anio_publicacion;
    private int  ejemplares;
    private String edicion;
    private String descripcion;
    private String portada;

    Libro (){}
    Libro( int id_libro, String titulo, int no_paginas, int anio_publicacion, int  ejemplares, String edicion, String descripcion, String portada){
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.no_paginas = no_paginas;
        this.anio_publicacion = anio_publicacion;
        this.ejemplares = ejemplares;
        this.edicion = edicion;
        this.descripcion = descripcion;
        this.portada = portada;
    }
}
