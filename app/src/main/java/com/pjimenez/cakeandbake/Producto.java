package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Producto extends AppCompatActivity {

    Button btn_pedido_info;

    private ListView listView;
    private String[] data = {"Tarta de fresa", "Pastel de manzana", "Pie de limon"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        List<String> elementos = new ArrayList<>();
        elementos.add("Tarta de fresa");
        elementos.add("Pastel de manzana");
        elementos.add("Pie de limon");

        listView = findViewById(R.id.lst_pasteles);

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
                Intent intent = new Intent(Producto.this, ProductoPedido.class);
                // Pasa cualquier dato adicional a la otra actividad si es necesario
                intent.putExtra("dato", selectedItem);
                startActivity(intent);
            }
        });
    }
}