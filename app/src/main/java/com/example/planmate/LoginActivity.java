package com.example.planmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //metodo boton inicio sesion
    public void InicioSesion(View view){
        Intent iniciosesion = new Intent(this, MenuActivty.class);
        startActivity(iniciosesion);
    }


}
