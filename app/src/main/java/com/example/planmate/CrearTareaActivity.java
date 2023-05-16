package com.example.planmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

public class CrearTareaActivity extends AppCompatActivity {

    private EditText etTareaTitulo, etTareaDetalles, etNombreProfesor, etFechaEntrega, etCategoria, etMateria;
    private Spinner spinnerCategoria, spinnerMateria;
    private Button btnGuardarTarea;
    private List<String> materias;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea);

        etTareaTitulo = findViewById(R.id.et_titulo_tarea);
        etTareaDetalles = findViewById(R.id.et_detalles);
        etNombreProfesor = findViewById(R.id.et_nombre_profesor);
        etFechaEntrega = findViewById(R.id.et_fecha_entrega);
        etCategoria = findViewById(R.id.etCategoria);
        etMateria = findViewById(R.id.etMateria);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        spinnerMateria = findViewById(R.id.spinnerMateria);
        btnGuardarTarea = findViewById(R.id.btnGuardarTarea);

        // Configurar el adaptador y las opciones del Spinner de Dificultad
        ArrayAdapter<CharSequence> dificultadAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.categorias,
                android.R.layout.simple_spinner_item
        );
        dificultadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(dificultadAdapter);

        etCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etCategoria.setVisibility(View.GONE);
                spinnerCategoria.setVisibility(View.VISIBLE);
            }
        });

        // Manejar la selección del Spinner
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categoriaSeleccionada = parent.getItemAtPosition(position).toString();
                etCategoria.setText(categoriaSeleccionada);
                etCategoria.setVisibility(View.VISIBLE);
                spinnerCategoria.setVisibility(View.GONE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                etCategoria.setVisibility(View.VISIBLE);
                spinnerCategoria.setVisibility(View.GONE);

            }
        });


        firestore = FirebaseFirestore.getInstance();



        btnGuardarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarTarea();
            }
        });

        ArrayAdapter<String> materiaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        materiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMateria.setAdapter(materiaAdapter);

        obtenerMateriasDesdeFirebase(materiaAdapter);

        // Obtener los datos de la tarea del Intent
        String tareaId = getIntent().getStringExtra("tareaId");
        String titulo = getIntent().getStringExtra("titulo");
        String detalles = getIntent().getStringExtra("detalles");
        String profesor = getIntent().getStringExtra("profesor");
        String fechaEntrega = getIntent().getStringExtra("fechaEntrega");
        String categoria = getIntent().getStringExtra("categoria");
        String materia = getIntent().getStringExtra("materia");

        // Llenar los campos de la interfaz con los datos de la tarea
        etTareaTitulo.setText(titulo);
        etTareaDetalles.setText(detalles);
        etNombreProfesor.setText(profesor);
        etFechaEntrega.setText(fechaEntrega);
        etCategoria.setText(categoria);
        etMateria.setText(materia);
    }

    private void obtenerMateriasDesdeFirebase(ArrayAdapter<String> materiaAdapter) {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("materias").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<String> materias = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Materia materia = documentSnapshot.toObject(Materia.class);
                            String tituloMateria = materia.getNombreMateria();
                            materias.add(tituloMateria);
                        }
                        materiaAdapter.clear();
                        materiaAdapter.addAll(materias);
                        materiaAdapter.notifyDataSetChanged();

                        // Establecer las opciones del Spinner "Materia"
                        spinnerMateria.setAdapter(materiaAdapter);

                        etMateria.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                etMateria.setVisibility(View.GONE);
                                spinnerMateria.setVisibility(View.VISIBLE);
                            }
                        });

                        // Manejar la selección del Spinner
                        spinnerMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String materiaSeleccionada = parent.getItemAtPosition(position).toString();
                                // Puedes hacer algo con la categoría seleccionada si lo necesitas
                                etMateria.setText(materiaSeleccionada);
                                etMateria.setVisibility(View.VISIBLE);
                                spinnerMateria.setVisibility(View.GONE);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                // Aquí  el que no se seleccione ninguna categoría

                                etMateria.setVisibility(View.VISIBLE);
                                spinnerMateria.setVisibility(View.GONE);

                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CrearTareaActivity.this, "Error al obtener las materias", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void guardarTarea() {
        String titulo = etTareaTitulo.getText().toString();
        //String dificultad = etCategoria.getText().toString();
        String detallesTarea = etTareaDetalles.getText().toString();
        String nombreProfesor = etNombreProfesor.getText().toString();
        String fechaEntrega = etFechaEntrega.getText().toString();
        String dificultad = spinnerCategoria.getSelectedItem().toString();
        String materia = spinnerMateria.getSelectedItem().toString();

        Intent intent = getIntent();
        boolean editarTarea = intent.getBooleanExtra("editarTarea", false);
        String tareaId = obtenerTareaId();

        if (editarTarea && tareaId != null) {
            // Actualizar la tarea existente en Firebase Firestore
            //String tareaId = getIntent().getStringExtra("tareaId");
            firestore.collection("tareas").document(tareaId)
                    .update("titulo", titulo, "detalles", detallesTarea, "profesor", nombreProfesor,
                            "fechaEntrega", fechaEntrega, "categoria", dificultad, "materia", materia)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(CrearTareaActivity.this, "Tarea actualizada correctamente", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CrearTareaActivity.this, "Error al actualizar la tarea", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // Crear una nueva tarea en Firebase Firestore
            firestore.collection("tareas")
                    .add(new Tarea(titulo, detallesTarea, dificultad, nombreProfesor, fechaEntrega, materia))
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(CrearTareaActivity.this, "Tarea guardada correctamente", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CrearTareaActivity.this, "Error al guardar la tarea", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


    public void AtrasCrearTarea(View view) {
        Intent atrasCrearTarea = new Intent(this, MenuActivty.class);
        startActivity(atrasCrearTarea);
    }

    private String obtenerTareaId() {
        // se obtiene el ID de la tarea actual desde el Intent
        String tareaId = getIntent().getStringExtra("tareaId");
        return tareaId;
    }


}
