package com.example.planmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AsignarMareriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_mareria);
    }


    //metodo boton de dar atras en layout Asignar Materias
    public void AtrasAsignarMateria(View view){
        Intent atras_asignar_materia = new Intent(this, MenuActivty.class);
        startActivity(atras_asignar_materia);
    }
}
