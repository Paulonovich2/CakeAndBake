package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button mi_perfil1;
    Button mi_pedido1;
    Button contactanos1;
    Button inicio1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        inicio1 = findViewById(R.id.btnInicio);
        inicio1.setOnClickListener(v -> {
            Intent objeto = new Intent(this, Principal.class);
            startActivity(objeto);
        });
        mi_perfil1 = findViewById(R.id.btnPerfil);
        mi_perfil1.setOnClickListener(v -> {
            Intent objeto = new Intent(this, Perfil.class);
            startActivity(objeto);
        });
        mi_pedido1 = findViewById(R.id.btnMisPedidos);
        mi_pedido1.setOnClickListener(v -> {
            Intent objeto = new Intent(this, Pedido.class);
            startActivity(objeto);
        });
        contactanos1 = findViewById(R.id.btnContactenos);
        contactanos1.setOnClickListener(v -> {
            Intent objeto = new Intent(this, Contactenos.class);
            startActivity(objeto);
        });
    }
}
