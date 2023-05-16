package com.example.planmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
public class AsignarMateriaActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText editTextNombreMateria;
    private EditText editTextNombreProfesor;
    private EditText editTextCorreoProfesor;
    private EditText editTextNRCMateria;
    private Button btnRegistrarMateria;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_materia);

        // se obtiene referencia a Firestore
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Inicializar vistas
        editTextNombreMateria = findViewById(R.id.editTextNombreMateria);
        editTextNombreProfesor = findViewById(R.id.editTextNombreProfesor);
        editTextCorreoProfesor = findViewById(R.id.editTextCorreoProfesor);
        editTextNRCMateria = findViewById(R.id.editTextNRCMateria);
        btnRegistrarMateria = findViewById(R.id.btnRegistrarMateria);

        // se configura el evento de click en el botón "Registrar Materia"
        btnRegistrarMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarMateria();
            }
        });

    }

    private void registrarMateria() {
        // se obtienen los valores de los campos de texto
        String nombreMateria = editTextNombreMateria.getText().toString();
        String nombreProfesor = editTextNombreProfesor.getText().toString();
        String correoProfesor = editTextCorreoProfesor.getText().toString();
        String nrcMateria = editTextNRCMateria.getText().toString();

        // Crear un objeto Materia con los valores obtenidos
        Materia materia = new Materia(nombreMateria, nombreProfesor, correoProfesor, nrcMateria);

        // Guardar la materia en la base de datos de Firebase
        db.collection("materias")
                .add(materia)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // La materia se ha guardado exitosamente en Firestore
                        Toast.makeText(AsignarMateriaActivity.this, "Materia registrada correctamente", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Ha ocurrido un error al guardar la materia en
                        Toast.makeText(AsignarMateriaActivity.this, "Error al registrar la materia", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    //metodo boton de dar atras en layout Asignar Materias
    public void AtrasAsignarMateria(View view){
        Intent atras_asignar_materia = new Intent(this, MenuActivty.class);
        startActivity(atras_asignar_materia);
    }

    public void cerrarSesion(View view) {
        mAuth.signOut();
        // Se redirige a la actividad de inicio de sesión.
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}