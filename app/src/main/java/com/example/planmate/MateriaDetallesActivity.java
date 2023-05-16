package com.example.planmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MateriaDetallesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_detalles);
    }

    //metodo boton de dar atras en layout Materias Detalle
    public void AtrasMateriaDetalle(View view){
        Intent atras_materia_detalle = new Intent(this, DetallesTareaActivity.class);
        startActivity(atras_materia_detalle);
    }
}