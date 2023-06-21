package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button boton_iniciar_sesion;
    Button boton_registro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton_iniciar_sesion = findViewById(R.id.btn_iniciar_sesion);
        boton_iniciar_sesion.setOnClickListener(v -> {
            Intent objeto = new Intent(this, IniciarSesion.class);
            startActivity(objeto);
        });
        boton_registro = findViewById(R.id.btn_registrar);
        boton_registro.setOnClickListener(v -> {
            Intent objeto = new Intent(this, Registrarse.class);
            startActivity(objeto);
        });



    }
}