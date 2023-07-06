package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    ImageButton btn_locacion;
    ImageButton btn_Menu;
    Button btn_productos;
    TextView lblUsuario; // Agrega un TextView en tu layout para mostrar el nombre del usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        lblUsuario = findViewById(R.id.lblUsuario); // Asigna el TextView de tu layout

        btn_locacion = findViewById(R.id.btn_locacion);
        btn_locacion.setOnClickListener(v -> {
            Intent objPrincipalLocal = new Intent(this, PrincipalLocal.class);
            startActivity(objPrincipalLocal);
        });

        btn_Menu = findViewById(R.id.btnMenu);
        btn_Menu.setOnClickListener(v -> {
            Intent objMenu = new Intent(this, Menu.class);
            startActivity(objMenu);
        });

        btn_productos = findViewById(R.id.btn_Guardar_principal);
        btn_productos.setOnClickListener(v -> {
            Intent objMenu = new Intent(this, Producto.class);
            startActivity(objMenu);
        });

        // Obtén los datos del Intent
        Intent intent = getIntent();
        if (intent != null) {
            int userId = intent.getIntExtra("u_id", 0);
            String nombres = intent.getStringExtra("u_nombres");

            // Muestra el nombre del usuario en el TextView
            lblUsuario.setText("¡Hola " + nombres + "!");
        }
    }
}