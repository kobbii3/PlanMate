package com.example.planmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ComentariosDetallesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios_detalles);

    }

    //metodo boton de dar atras en layout Comentarios Detalle
    public void AtrasComentarioDetalle(View view){
        Intent atras_comentario_detalle = new Intent(this, DetallesTareaActivity.class);
        startActivity(atras_comentario_detalle);
    }
}