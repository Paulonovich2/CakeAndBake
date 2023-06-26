package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class Principal extends AppCompatActivity {

    ImageButton btn_locacion;
    ImageButton btn_Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

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
    }
}