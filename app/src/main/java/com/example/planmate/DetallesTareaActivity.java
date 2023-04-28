package com.example.planmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DetallesTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_tarea);
    }

    //metodo boton ventana emergente para materia
    public void MateriaEmergente(View view){
        Intent materiaemergente = new Intent(this, MateriaDetallesActivity.class);
        startActivity(materiaemergente);
    }

    //metodo boton ventana emergente para comentarios
    public void ComentarioEmergente(View view){
        Intent comentarioemergente = new Intent(this, ComentariosDetallesActivity.class);
        startActivity(comentarioemergente);
    }

    //metodo boton editar tarea
    public void EditarTarea(View view){
        Intent editartarea = new Intent(this, CrearTareaActivity.class);
        startActivity(editartarea);
    }

    //metodo boton de dar atras en layout Detalle Tareas
    public void AtrasDetalleTareas(View view){
        Intent atras_detalle_tareas = new Intent(this, ListaTareasActivity.class);
        startActivity(atras_detalle_tareas);
    }
}
