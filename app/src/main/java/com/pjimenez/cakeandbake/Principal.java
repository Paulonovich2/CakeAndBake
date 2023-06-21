package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Principal extends AppCompatActivity {

    ImageButton menu_desplegable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        menu_desplegable = findViewById(R.id.imageButton_principal_barra);
        menu_desplegable.setOnClickListener(v -> {
            Intent objeto = new Intent(this, Menu.class);
            startActivity(objeto);
        });
    }
}

