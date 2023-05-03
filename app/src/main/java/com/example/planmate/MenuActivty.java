package com.example.planmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activty);
    }

    //metodo boton ver tareas pendientes
    public void VerTareas(View view){
        Intent vertareas = new Intent(this, MainActivity.class);
        startActivity(vertareas);
    }

    //metodo boton para crear tarea
    public void CrearTarea(View view){
        Intent creartarea = new Intent(this, CrearTareaActivity.class);
        startActivity(creartarea);
    }


    //metodo boton para asignar materia
    public void AsignarMateria(View view){
        Intent asignarmateria = new Intent(this, AsignarMareriaActivity.class);
        startActivity(asignarmateria);
    }
}
