package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Registrarse extends AppCompatActivity {

    Button registrarse_principal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        registrarse_principal  = findViewById(R.id.btn_registrarse_principal);
        registrarse_principal.setOnClickListener(v -> {
            Intent objeto = new Intent(this, Principal.class);
            startActivity(objeto);
        });
    }
}
