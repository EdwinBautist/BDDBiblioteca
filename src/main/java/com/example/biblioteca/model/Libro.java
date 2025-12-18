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

    public Libro (){}
    public Libro( int id_libro, String titulo, int no_paginas, int anio_publicacion, int  ejemplares, String edicion, String descripcion, String portada){
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.no_paginas = no_paginas;
        this.anio_publicacion = anio_publicacion;
        this.ejemplares = ejemplares;
        this.edicion = edicion;
        this.descripcion = descripcion;
        this.portada = portada;
    }
    //gets
    public String getTitulo() {
        return titulo;
    }
    public int getId_libro() {
        return id_libro;
    }
    public int getNo_paginas(){
        return no_paginas;
    }
    public int getAnio_publicacion() {
        return anio_publicacion;
    }
    public int getEjemplares(){
        return  ejemplares;
    }
    public String getEdicion() {
        return edicion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getPortada() {
        return portada;
    }
    //sets
    public void setAnio_publicacion(int anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
    }
    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setNo_paginas(int no_paginas) {
        this.no_paginas = no_paginas;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }
    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }
    public void setPortada(String portada) {
        this.portada = portada;
    }
}
