package com.example.planmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class CrearTareaActivity extends AppCompatActivity {

    private EditText etTareaTitulo, etTareaDetalles, etNombreProfesor, etFechaEntrega, etCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea);

        etTareaTitulo = findViewById(R.id.et_titulo_tarea);
        etTareaDetalles = findViewById(R.id.et_detalles);
        etNombreProfesor = findViewById(R.id.et_nombre_profesor);
        etFechaEntrega = findViewById(R.id.et_fecha_entrega);
        etCategoria = findViewById(R.id.et_categoria);
    }

    //metodo boton de dar atras en layout CrearTarea
    public void AtrasCrearTarea(View view){
        Intent atras_crear_tarea = new Intent(this, MenuActivty.class);
        startActivity(atras_crear_tarea);
    }

    public void clickGuardarTarea(View view){

        String titulo = etTareaTitulo.getText().toString();
        String detalles_tarea = etTareaDetalles.getText().toString();
        String categoria = etCategoria.getText().toString();
        String nombre_profesor = etNombreProfesor.getText().toString();
        String fecha_entrega = etFechaEntrega.getText().toString();


        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setTitulo(titulo);
        nuevaTarea.setDetalles_tarea(detalles_tarea);
        nuevaTarea.setCategoria(categoria);
        nuevaTarea.setNombre_profesor(nombre_profesor);
        nuevaTarea.setFecha_entrega(fecha_entrega);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("tareas").add(nuevaTarea);

        Toast.makeText(this, "SE CREÓ LA TAREA", Toast.LENGTH_SHORT).show();
        finish();
    }
}
