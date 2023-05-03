package com.example.planmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Tarea> listaPrincipalTareas = new ArrayList<>();
    private RecyclerView rvListadoTareas;

    private AdaptadorPersonalizado miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Tareas Pendientes");

        rvListadoTareas = findViewById(R.id.rv_lista_tareas);
        miAdaptador = new AdaptadorPersonalizado(listaPrincipalTareas);

        miAdaptador.setOnItemClickListener(new AdaptadorPersonalizado.OnItemClickListener() {
            @Override
            public void onItemClick(Tarea miTarea, int posicion) {
                Intent intent =new Intent(MainActivity.this, DetallesTareaActivity.class);
                intent.putExtra("tarea",miTarea);
                startActivity(intent);
            }

            @Override
            public void onItemBtnTituloTarea(Tarea miTarea, int posicion) {

            }
        });

        rvListadoTareas.setAdapter(miAdaptador);
        rvListadoTareas.setLayoutManager(new LinearLayoutManager(this));
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
}