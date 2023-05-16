package com.example.planmate;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Tarea implements Serializable {
    private String titulo;
    private String detalles_tarea;
    private String categoria;

    private String materia;
    private String nombre_profesor;
    private String fecha_entrega;
    private String id;

    public Tarea(String titulo, String detalles_tarea, String categoria, String materia, String nombre_profesor, String fecha_entrega) {
        this.titulo = titulo;
        this.detalles_tarea = detalles_tarea;
        this.categoria = categoria;
        this.materia = materia;
        this.nombre_profesor = nombre_profesor;
        this.fecha_entrega = fecha_entrega;
    }

    public Tarea() {

    }



    @Exclude
    public String getId() {
        return id;
    }
    @Exclude
    public void setId(String id) {
        this.id = id;
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

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }


}
