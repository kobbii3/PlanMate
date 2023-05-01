package com.example.planmate;

public class Tarea {
    private String titulo;
    private String detalles_tarea;
    private String categoria;

    public Tarea(String titulo, String detalles_tarea, String categoria) {
        this.titulo = titulo;
        this.detalles_tarea = detalles_tarea;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalles_tarea() {
        return detalles_tarea;
    }

    public void setDetalles_tarea(String detalles_tarea) {
        this.detalles_tarea = detalles_tarea;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
