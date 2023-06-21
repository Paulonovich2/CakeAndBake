package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ConfirmarPedido extends AppCompatActivity {

    Button boton_pagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        boton_pagar = findViewById(R.id.btn_pagar);
        boton_pagar.setOnClickListener(v -> {
            Intent objeto = new Intent(this, PedidoRegistrado.class);
            startActivity(objeto);
        });
    }
}