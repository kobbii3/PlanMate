package com.example.planmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private ArrayList<Tarea> listaPrincipalTareas = new ArrayList<>();
    private RecyclerView rvListadoTareas;

    private AdaptadorPersonalizado miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Tareas Pendientes");
        View listaTareasView = getLayoutInflater().inflate(R.layout.activity_lista_tareas, null);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnTarea = listaTareasView.findViewById(R.id.bt_tarea);
        mAuth = FirebaseAuth.getInstance();

        rvListadoTareas = findViewById(R.id.rv_lista_tareas);
        miAdaptador = new AdaptadorPersonalizado(MainActivity.this, listaPrincipalTareas);
        Tarea tareaSeleccionada = new Tarea();
        miAdaptador.setOnItemClickListener(new AdaptadorPersonalizado.OnItemClickListener() {
            @Override
            public void onItemClick(Tarea tarea, int posicion) {


                Intent intent =new Intent(MainActivity.this, DetallesTareaActivity.class);
                intent.putExtra("tarea",tareaSeleccionada);
                startActivity(intent);
            }

            @Override
            public void onItemBtnTituloTarea(Tarea tarea, int posicion) {

            }
        });

        rvListadoTareas.setAdapter(miAdaptador);
        rvListadoTareas.setLayoutManager(new LinearLayoutManager(this));

    //    btnTarea.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View v) {
    //            Intent intent = new Intent(MainActivity.this, DetallesTareaActivity.class);
    //            startActivity(intent);
        //       }
        //   });

        // Agregar el diseño de lista_tareas al LinearLayout principal de MainActivity
        //  LinearLayout linearLayout = findViewById(R.id.activity_main.xml); // Reemplaza "layout_principal" con el ID correcto de tu LinearLayout principal
        //  linearLayout.addView(listaTareasView);
    }

    @Override
    protected void onResume() {
        listaPrincipalTareas.clear();
        super.onResume();
        cargarDatosTareas();
    }


    public void cargarDatosTareas(){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("tareas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    listaPrincipalTareas.clear();

                    for (DocumentSnapshot document: task.getResult()){
                        Tarea tareaAtrapado = document.toObject(Tarea.class);
                        Log.d("hosl", tareaAtrapado.toString());
                        tareaAtrapado.setId(document.getId());
                        listaPrincipalTareas.add(tareaAtrapado);
                    }

                    miAdaptador.setListadoInformacion(listaPrincipalTareas);

                }else{
                    Toast.makeText(MainActivity.this, "No se pudo conectar al servidor", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void cerrarSesion(View view) {
        mAuth.signOut();
        // Se redirige a una actividad de inicio de sesión.
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}