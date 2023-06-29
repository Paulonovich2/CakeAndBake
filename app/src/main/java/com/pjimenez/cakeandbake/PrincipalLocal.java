package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PrincipalLocal extends AppCompatActivity {

    ImageButton btn_delivery;
    ImageButton btn_Menu;
    Button btn_productos;

    private ListView listView;
    private String[] data = {"Local San Isidro", "Local San Miguel", "Local Ate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_local);

        btn_delivery = findViewById(R.id.btn_delivery);
        btn_delivery.setOnClickListener(v -> {
            Intent objeto = new Intent(this, Principal.class);
            startActivity(objeto);
        });

        btn_Menu = findViewById(R.id.btnMenu);
        btn_Menu.setOnClickListener(v -> {
            Intent objMenu = new Intent(this, Menu.class);
            startActivity(objMenu);
        });

        btn_productos = findViewById(R.id.btn_Guardar_local);
        btn_productos.setOnClickListener(v -> {
            Intent objMenu = new Intent(this, Producto.class);
            startActivity(objMenu);
        });

        listView = findViewById(R.id.lstUbicacionLocal);

        // Configura el adaptador del ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

        // Establece un listener de clic para los elementos del ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtén el elemento seleccionado del ListView
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Crea un Intent para abrir la otra actividad
                Intent intent = new Intent(PrincipalLocal.this, Producto.class);
                // Pasa cualquier dato adicional a la otra actividad si es necesario
                intent.putExtra("dato", selectedItem);
                startActivity(intent);
            }
        });
    }
}