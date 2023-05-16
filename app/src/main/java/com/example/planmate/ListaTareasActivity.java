package com.example.planmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListaTareasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas);
    }

    //metodo boton al dar clic a una tarea
    public void DetallesTarea(View view){
        Intent detallestarea = new Intent(this, DetallesTareaActivity.class);
        startActivity(detallestarea);
    }


    //metodo boton de dar atras en layout Lista Tareas
    public void AtrasListaTareas(View view){
        Intent atras_lista_tareas = new Intent(this, MenuActivty.class);
        startActivity(atras_lista_tareas);
    }
}