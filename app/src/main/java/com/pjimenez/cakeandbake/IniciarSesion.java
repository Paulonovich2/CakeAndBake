package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class IniciarSesion extends AppCompatActivity {

    Button boton_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        boton_ingresar = findViewById(R.id.btn_ingresar);
        boton_ingresar.setOnClickListener(v -> {
            Intent objeto = new Intent(this, Principal.class);
            startActivity(objeto);
        });
    }
}