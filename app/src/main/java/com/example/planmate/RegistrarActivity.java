package com.example.planmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class RegistrarActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText correo;
    private EditText contrasena;
    private EditText contrasenaConfirmacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        contrasenaConfirmacion = findViewById(R.id.contrasenaConfirmacion);

    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser)
    }


    public void registrarUsuario (View view){

        if(contrasena.getText().toString().equals(contrasenaConfirmacion.getText().toString())){
                mAuth.createUserWithEmailAndPassword(correo.getText().toString().trim(), contrasena.getText().toString().trim())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext() , "Usuario creado", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(i);
                                //updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext() , "Authentication failed.", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                        }
                    });



        }else{
            Toast.makeText(this, "las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
            }


    }

    //metodo boton registrarse para ir a iniciar sesion
    public void RegistroSesionDos(View view){
        Intent registrosesiondos = new Intent(this, LoginActivity.class);
        startActivity(registrosesiondos);
    }

}