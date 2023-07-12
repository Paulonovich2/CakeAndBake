package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class ProductoPedido extends AppCompatActivity {

    Button btn_guardar;
    ImageButton btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_pedido);

        btn_guardar = findViewById(R.id.buttonGuardarPedido);
        btn_guardar.setOnClickListener(v -> {
            Intent objeto = new Intent(this, ConfirmarPedido.class);
            startActivity(objeto);
        });

        btnMenu = findViewById(R.id.btn_Menu);
        btnMenu.setOnClickListener(v -> {
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
        });
    }
}