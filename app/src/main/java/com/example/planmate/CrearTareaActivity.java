package com.example.planmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CrearTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea);
    }

    //metodo boton de dar atras en layout CrearTarea
    public void AtrasCrearTarea(View view){
        Intent atras_crear_tarea = new Intent(this, MenuActivty.class);
        startActivity(atras_crear_tarea);
    }
}
